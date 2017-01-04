package meanwhile.search;

import java.io.File;
import java.io.IOException;
import java.util.List;

import meanwhile.file.FileInfo;
import meanwhile.file.FileUtility;

public class Search extends FileUtility {
	public static void searchAndCopyFiles(FileInfo fileInfo) throws IOException {
		List<String> filePathAndNames = fileInfo.getFiles();
		String targetPath = fileInfo.getTarget();
		for (String filePathAndName : filePathAndNames) {
			File sourceFile = new File(filePathAndName);
			File targetFile = new File(targetPath + "\\" + getFilename(filePathAndName));
			copyFile(sourceFile, targetFile);
		}
	}
}
