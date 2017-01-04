package meanwhile.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import meanwhile.entity.FileTree;

public class FileUtility {
	protected static final int BUFFER_SIZE = 1024;
	/**
	 * Duplicate a file. 
	 * It throws out an exception once the source file is occupied. 
	 * @param sourceFile
	 * @param targetFile
	 */
	public static void copyFile(File sourceFile, File targetFile) {
		int length = -1;
		byte[] buffer = new byte[BUFFER_SIZE];
		try {
			InputStream in = new FileInputStream(sourceFile);
			OutputStream out = new FileOutputStream(targetFile);
			while ((length = in.read(buffer)) != -1) {
				out.write(buffer, 0, length);
			}
			out.close();
			in.close();
		}
		catch (Exception e) {
			System.out.println("Copy " + sourceFile + " to " + targetFile + " Failed");
		}
	}
	/**
	 * Get the file name that does not contain the extension. 
	 * @param path
	 * @return
	 */
	public static String getName(String path) {
		if (path.contains("\\")) {
			return path.substring(path.lastIndexOf("\\") + 1, path.lastIndexOf("."));
		}
		else if (path.contains("/")) {
			return path.substring(path.lastIndexOf("/") + 1, path.lastIndexOf("."));
		}
		else {
			return null;
		}
	}
	/**
	 * Get extensions like .exe, .java
	 * @param path
	 * @return
	 */
	public static String getExtension(String path) {
		return path.substring(path.lastIndexOf(".") + 1);
	}
	/**
	 * Given the source file or folder and returns the target file or folder.
	 * @param sourceFileOrFolder
	 * @return
	 */
	public static File getTargetFileOrFolder(String sourceRootPath, String targetRootPath, File sourceFileOrFolder) {
		return new File(targetRootPath + sourceFileOrFolder.getAbsolutePath().substring(sourceRootPath.length()));
	}
	/**
	 * Get the name and the extension of a file. 
	 * @param path
	 * @return
	 */
	public static String getFilename(String path) {
		return getName(path) + "." + getExtension(path);
	}
	public static FileInfo switchSourceAndTargetPath(FileInfo fileInfo) {
		String targetPath = fileInfo.getTarget();
		fileInfo.setTarget(fileInfo.getSource());
		fileInfo.setSource(targetPath);
		return fileInfo;
	}
	public static void printFileTree(FileTree fileTree) {
		if (fileTree.getFile() != null) {
			System.out.println(fileTree.getFile().getName());
		}
		else {
			System.out.println("File Tree");
		}
		List<FileTree> children = fileTree.getChildren();
		if (children != null) {
			for (FileTree child : children) {
				printFileTree(child);
			}
		}
	}
}
