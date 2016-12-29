package com.evolution.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Vector;

import com.evolution.entity.FileInfo;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;

public class SshUtil {
	private Session session;
	private Channel channel;
	private ChannelSftp channelSftp;
	
	public static Map<String, String> getLocalFilesAndRelativePaths(String localBasePath) {
		localBasePath = localBasePath.replace("\\", "/");
		Map<String, String> localFilesAndRelativePaths = new LinkedHashMap<>();
		getLocalFilesAndRelativePaths(localBasePath, localFilesAndRelativePaths);
		for (Entry<String, String> entry : localFilesAndRelativePaths.entrySet()) {
			localFilesAndRelativePaths.put(entry.getKey(), entry.getValue().substring(localBasePath.length()));
		}
		return localFilesAndRelativePaths;
	}	
	
	public static void getLocalFilesAndRelativePaths(String localBasePath, Map<String, String> localFilesAndRelativePaths) {
		localBasePath = localBasePath.replace("\\", "/");
		File localBaseFolder = new File(localBasePath);
		File[] files = localBaseFolder.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				getLocalFilesAndRelativePaths(file.getAbsolutePath(), localFilesAndRelativePaths);
			} else {
				localFilesAndRelativePaths.put(file.getAbsolutePath(), localBasePath);
			}
		}
	}
	
	public static Map<String, String> getLocalFilesAndRemotePaths(String localBasePath, String remoteBasePath) {
		localBasePath = localBasePath.replace("\\", "/");
		Map<String, String> localFilesAndRemotePaths = new LinkedHashMap<>();
		getLocalFilesAndRemotePaths(localBasePath, remoteBasePath, localFilesAndRemotePaths);
		return localFilesAndRemotePaths;
	}
	
	public static void getLocalFilesAndRemotePaths(String localBasePath, String remoteBasePath, Map<String, String> localFilesAndRemotePaths) {
		File localBaseFolder = new File(localBasePath);
		File[] files = localBaseFolder.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				String path = file.getAbsolutePath().replace("\\", "/");
				String relativePath = path.substring(localBasePath.length());
				getLocalFilesAndRemotePaths(file.getAbsolutePath(), remoteBasePath + relativePath, localFilesAndRemotePaths);
			} else {
				localFilesAndRemotePaths.put(file.getAbsolutePath(), remoteBasePath);
			}
		}
	}
	
	public Boolean changeOrMakeDirectory(String remotePath) {
		try {
			try {
				this.channelSftp.cd(remotePath);
			} catch (Exception e) {
				this.channelSftp.mkdir(remotePath);
				this.channelSftp.cd(remotePath);
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void closeChannel() {
		closeChannelSftp();
		if (this.channel != null) {
			this.channel.disconnect();
		}
		this.channel = null;
	}
	
	public void closeChannelSftp() {
		if (this.channelSftp != null) {
			channelSftp.disconnect();
		}
		this.channelSftp = null;
	}
	
	public void closeSession() {
		closeChannel();
		if (this.session != null) {
			this.session.disconnect();
		}
		this.session = null;
	}

	public void createChannelSftp() {
		try {
			if (this.channelSftp != null) {
				return;
			}
			this.channel = this.session.openChannel("sftp");
			this.channel.connect();
			this.channelSftp = (ChannelSftp) this.channel;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void createSession(Object object) {
		String host = PojoUtil.getField(object, "host", String.class);
		Integer port = PojoUtil.getField(object, "port", Integer.class);
		String username = PojoUtil.getField(object, "username", String.class);
		String password = PojoUtil.getField(object, "password", String.class);
		createSession(host, port, username, password);
	}
	
	public void createSession(String host, Integer port, String username, String password) {
		try {
			if (this.session != null) {
				return;
			}
			JSch jSch = new JSch();
			Session session = jSch.getSession(username, host, port);
			session.setPassword(password);
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			this.session = session;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public InputStream download(String remoteFile) {
		try {
			createChannelSftp();
			return this.channelSftp.get(remoteFile);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("rawtypes")
	public Map<String, FileInfo> listFiles(String remoteFolder) {
		try {
			createChannelSftp();
			this.channelSftp.cd(remoteFolder);
			Vector files = this.channelSftp.ls(remoteFolder);
			Map<String, FileInfo> fileInfos = new HashMap<>();
			for (int i = 0; i < files.size(); i++) {
				LsEntry entry = (LsEntry) files.get(i);				
				FileInfo fileInfo = new FileInfo();
				fileInfo.setFilename(entry.getFilename());
				fileInfo.setLongFilename(entry.getLongname());
				SftpATTRS fileAttributes = entry.getAttrs();
				fileInfo.setModifiedTime(fileAttributes.getMTime());
				fileInfo.setSize(fileAttributes.getSize());
				fileInfos.put(fileInfo.getFilename(), fileInfo);
			}
			return fileInfos;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<String> run(List<String> commands, Integer minWaitingTime4EachCommand) {
		List<String> results = new LinkedList<>();
		for (String command : commands) {
			results.add(run(command, minWaitingTime4EachCommand));
		}
		return results;
	}

	public String run(String command, Integer minWaitingTime) {
		try {
			Channel channel = this.session.openChannel("shell");
			channel.connect();
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();// Stores the output.
			channel.setOutputStream(byteArrayOutputStream);
			OutputStream outputStream = channel.getOutputStream();
		    PrintStream shell = new PrintStream(outputStream, true);// The input stream on the server's side is output stream on the client's side.
		    shell.println(command);
		    Thread.sleep(minWaitingTime * 1000);// Wait for the server.
		    String echo = new String(byteArrayOutputStream.toByteArray());
		    return echo;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Boolean upload(String localBasePath, String remoteBasePath) {
		try {
			createChannelSftp();
			Map<String, String> localFilesAndRelativePaths = getLocalFilesAndRelativePaths(localBasePath);
			for (Entry<String, String> entry : localFilesAndRelativePaths.entrySet()) {
				File localFile = new File(entry.getKey());
				String relativePath = entry.getValue();
				if (changeOrMakeDirectory(remoteBasePath) == false) {
					Sys.print("The remote base path does not exist.");
					return false;
				}
				String[] folders = relativePath.split("/");
				for (String folder : folders) {
				    if (folder.length() <= 0) {
				    	continue;
				    }
				    changeOrMakeDirectory(folder);
				}
				this.channelSftp.put(new FileInputStream(localFile), localFile.getName());
			}
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
