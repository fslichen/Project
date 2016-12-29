package com.evolution.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.FileNameMap;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.apache.commons.io.FileUtils;
import org.apache.http.entity.ContentType;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {
	public static void copy(File file0, File file1) {
		try {
			InputStream in = new FileInputStream(file0);
			OutputStream out = new FileOutputStream(file1);
			byte[] buffer = new byte[1024];
			int length = -1;
			while ((length = in.read(buffer)) != -1) {
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void downloadFile(String url, String path, String filename) {
		try {
			File file = getFileOnline(url, filename);
			InputStream inputStream = new FileInputStream(file);
			FileUtils.copyToFile(inputStream, new File(path + "/" + filename));
		} catch (Exception e) {}
	}

	public static Integer getAudioDuration(File file) {
		try {
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
			AudioFormat format = audioInputStream.getFormat();
			long frameLength = audioInputStream.getFrameLength();
			Double durationInDecimalSeconds = Math.ceil((frameLength + 0.0) / format.getFrameRate());
			return durationInDecimalSeconds.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 30;
		} 
	}

	public static ContentType getContentType(File file) {
		return getContentType(file.getName());
	}

	public static ContentType getContentType(String filename) {
		FileNameMap mimeTypes = URLConnection.getFileNameMap();
		return ContentType.create(mimeTypes.getContentTypeFor(filename));
	}

	public static String getExtension(File file) {
		String filename = file.getName();
		return filename.substring(filename.lastIndexOf(".") + 1);
	}

	public static String getFilename(String filePathAndName) {
		return filePathAndName.substring(filePathAndName.lastIndexOf("\\") + 1);
	}

	public static File getFileOnline(String url, String filename) {
		try {
			ReadableByteChannel readableByteChannel = Channels.newChannel(new URL(url).openStream());
			File file = new File(filename);
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			fileOutputStream.getChannel().transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
			fileOutputStream.close();
			return file;
		} catch (Exception e) {
			return null;
		}
	}

	public static void grabFiles(String path) {
		try {
			File task = new File(path + "\\Task.txt");
			Scanner scanner = new Scanner(task);
			while (scanner.hasNext()) {
				String filePathAndName = scanner.next();
				File sourceFile = new File(filePathAndName);
				System.out.println(sourceFile);
				String filename = getFilename(filePathAndName);
				System.out.println(filename);
				File targetFile = new File(path + "\\" + filename);
				copy(sourceFile, targetFile);
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isAudio(File file) {
		switch (getExtension(file)) {
		case "mp3" : return true;
		case "mp4" : return true;
		case "wav" : return true;
		case "wma" : return true;
		case "amr" : return true;
		case "3gp" : return true;
		case "aa" : return true;
		case "aac" : return true;
		case "aax" : return true;
		case "act" : return true;
		case "aiff" : return true;
		case "ape" : return true;
		case "au" : return true;
		case "awb" : return true;
		case "dct" : return true;
		case "dss" : return true;
		case "dvf" : return true;
		case "flac" : return true;
		case "gsm" : return true;
		case "iklax" : return true;
		case "ivs" : return true;
		case "m4a" : return true;
		case "m4b" : return true;
		case "mmf" : return true;
		case "mpc" : return true;
		case "mogg" : return true;
		case "oga" : return true;
		case "ogg" : return true;
		case "opus" : return true;
		case "rm" : return true;
		case "ra" : return true;
		case "raw" : return true;
		case "sln" : return true;
		case "tta" : return true;
		case "vox" : return true;
		case "wv" : return true;
		case "webm" : return true;
		default : return false;
		}
	}

	public static boolean isImage(File file) {
		switch (getExtension(file)) {
		case "jpg" : return true;
		case "jpeg" : return true;
		case "png" : return true;
		case "gif" : return true;
		case "bmp" : return true;
		case "jfif" : return true;
		case "exif" : return true;
		case "tiff" : return true;
		case "ppm" : return true;
		case "pgm" : return true;
		case "pbm" : return true;
		case "pnm" : return true;
		case "webp" : return true;
		case "heif" : return true;
		case "bpg" : return true;
		case "cgm" : return true;
		case "svg" : return true;
		default : return false;
		}
	}

	public static File multipartFile2File(MultipartFile multipartFile) {
		try {
			File file = new File(multipartFile.getOriginalFilename());
			file.createNewFile(); 
			FileOutputStream fos = new FileOutputStream(file); 
			fos.write(multipartFile.getBytes());
			fos.close(); 
			return file;
		} catch(Exception e) {
			return null;
		}
	}

	public static void unzip(String zipFile, String path) {
		byte[] buffer = new byte[1024];
		try {
			File outputfolder = new File(path);
			if(!outputfolder.exists()) {
				outputfolder.mkdir();
			}
			ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(zipFile));
			ZipEntry zipEntry = zipInputStream.getNextEntry();
			path = path.replace("\\", "/");
			while (zipEntry != null) {
				String fileOrFolderRalativePath = zipEntry.getName();
				String fileOrFolderAbsolutePath = path + File.separator + fileOrFolderRalativePath;
				File file = new File(fileOrFolderAbsolutePath);
				if (fileOrFolderAbsolutePath.charAt(fileOrFolderAbsolutePath.length() - 1) == '/') {// Folder
					String[] middlePaths = fileOrFolderRalativePath.split("/");
					StringBuilder growingPath = new StringBuilder();
					for (String middlePath : middlePaths) {
						growingPath.append(middlePath + "/");
						new File(path + "/" + growingPath).mkdir();
					}
				} else {// File
					FileOutputStream fileOutputStream = new FileOutputStream(file);
					int length = 0;
					while ((length = zipInputStream.read(buffer)) > 0) {
						fileOutputStream.write(buffer, 0, length);
					}
					fileOutputStream.close();
				}
				zipEntry = zipInputStream.getNextEntry();
			}
			zipInputStream.closeEntry();
			zipInputStream.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
