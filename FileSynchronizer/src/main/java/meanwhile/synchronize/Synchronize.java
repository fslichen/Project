package meanwhile.synchronize;

import java.io.File;

import meanwhile.file.FileInfo;
import meanwhile.file.FileUtility;

public class Synchronize extends FileUtility {
	private static FileInfo fileInfo;
	/**
	 * Entry for synchronizeFilesAndFolders()
	 * @param alienFileInfo
	 */
	public static void synchronizeFilesAndFolders(FileInfo alienFileInfo) {
		fileInfo = alienFileInfo;
		synchronizeFilesAndFolders(new File(fileInfo.getSource()));
	}
	/**
	 * Synchronize the source path to the target path and vice versa. 
	 * @param sourcePath
	 * @param targetPath
	 */
	public static void synchronize(FileInfo alienFileInfo) {
		fileInfo = alienFileInfo;
		synchronizeFilesAndFolders(fileInfo);
		fileInfo = switchSourceAndTargetPath(fileInfo);
		synchronizeFilesAndFolders(fileInfo);
	}
	/**
	 * Synchronize all the files and folders in a recursive manner.
	 * @param sourceFolder
	 */
	public static void synchronizeFilesAndFolders(File sourceFolder) {
		File[] sourcefilesAndFolders = sourceFolder.listFiles();
		for (File sourceFileOrFolder : sourcefilesAndFolders) {
			File targetFileOrFolder = getTargetFileOrFolder(fileInfo.getSource(), fileInfo.getTarget(), sourceFileOrFolder);
			// Folder Detected
			if (sourceFileOrFolder.isDirectory()) {
				// If the target folder doesn't exist, create it. 
				if (!targetFileOrFolder.isDirectory()) {
					targetFileOrFolder.mkdir();
				}
				synchronizeFilesAndFolders(sourceFileOrFolder);
			}
			// File Detected
			else {
				// If the target file doesn't exist or the target file is out of date, synchronize it.
				if (!targetFileOrFolder.exists() || (targetFileOrFolder.exists() && fileInfo.isOverwritten() == true && sourceFileOrFolder.lastModified() > targetFileOrFolder.lastModified())) {
					copyFile(sourceFileOrFolder, targetFileOrFolder);
				}
			}
		}
	}
	/**
	 * Synchronize the folder structure in a recursive manner.
	 * @param sourceFolder
	 */
	public static void synchronizeFolders(File sourceFolder) {
		File[] sourcefilesAndFolders = sourceFolder.listFiles();
		for (File sourceFileOrFolder : sourcefilesAndFolders) {
			// Folder Detected
			if (sourceFileOrFolder.isDirectory()) {
				File targetFileOrFolder = getTargetFileOrFolder(fileInfo.getSource(), fileInfo.getTarget(), sourceFileOrFolder);
				if (!targetFileOrFolder.isDirectory()) {
					targetFileOrFolder.mkdir();
				}
				synchronizeFolders(sourceFileOrFolder);
			}
		}
	}
	/**
	 * Entry for synchronizeFolders()
	 * @param alienFileInfo
	 */
	public static void synchronizeFolders(FileInfo alienFileInfo) {
		fileInfo = alienFileInfo;
		synchronizeFolders(new File(fileInfo.getSource()));
	}
}
