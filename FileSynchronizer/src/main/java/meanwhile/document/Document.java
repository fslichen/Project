package meanwhile.document;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import meanwhile.entity.FileTree;
import meanwhile.file.FileInfo;
import meanwhile.file.FileUtility;

public class Document extends FileUtility {
	private static Writer writer;
	private static Set<String> includedExtensions;
	private static Set<String> excludedFolders;
	private static FileTree fileTree;
	public static void createDocument(File parentFolder) throws IOException {
		File[] filesAndFolders = parentFolder.listFiles();
		for (File fileOrFolder : filesAndFolders) {
			// Folder Detected
			if (fileOrFolder.isDirectory() && !excludedFolders.contains(fileOrFolder.getName())) {
				fileTree = fileTree.createChild(fileOrFolder);
				createDocument(fileOrFolder);
				fileTree = fileTree.getParent();
			}
			// File Detected
			else if (fileOrFolder.isFile()) {
				fileTree.createChild(fileOrFolder);
				String filename = fileOrFolder.getName();
				String extension = getExtension(filename);
				if (includedExtensions.contains(extension)) {
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileOrFolder), "UTF8"));
					writer.write(filename + "\n");
					String currentLine = null;
					while ((currentLine = bufferedReader.readLine()) != null) {
						writer.write(currentLine + "\n");
					}
					writer.write("\n");
					bufferedReader.close();
				}
			}
		}
	}
	public static void createDocument(FileInfo fileInfo) throws IOException {
		File document = null;
		String documentType = fileInfo.getDocumentType();
		document = new File(fileInfo.getTarget() + "\\" + documentType + new Date().getTime() + ".txt");
		document.createNewFile();
		writer = new FileWriter(document);
		setIncludedExtensionsAndExcludedFolders(documentType);
		fileTree = new FileTree();
		createDocument(new File(fileInfo.getSource()));
		FileUtility.printFileTree(fileTree);
		writer.close();
	}
	public static void setIncludedExtensionsAndExcludedFolders(String documentType) {
		excludedFolders = new HashSet<String>();
		includedExtensions = new HashSet<String>();
		if ("java".equals(documentType)) {
			excludedFolders.add(".metadata");
			excludedFolders.add(".recommenders");
			excludedFolders.add(".settings");
			excludedFolders.add("target");
			excludedFolders.add("RemoteSystemsTempFiles");
			excludedFolders.add("Servers");
			includedExtensions.add("jsp");
			includedExtensions.add("java");
			includedExtensions.add("xml");
			includedExtensions.add("properties");
			includedExtensions.add("js");
		}
	}
}
