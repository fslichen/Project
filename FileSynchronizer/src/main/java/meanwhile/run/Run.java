package meanwhile.run;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import meanwhile.file.FileInfo;

public class Run {
	private static FileInfo[] fileInfos;
	private static Map<String, String> className;
	static {
		File file = new File(System.getProperty("user.dir") + "\\file-info.json");
		if (file.exists()) {
			try {
				fileInfos = new ObjectMapper().readValue(file, FileInfo[].class);
			} catch (Exception e) {
				System.out.println("Json Parse Failure");
			}
			className = new HashMap<String, String>();
			className.put("synchronizeFilesAndFolders", "meanwhile.synchronize.Synchronize");
			className.put("synchronize", "meanwhile.synchronize.Synchronize");
			className.put("synchronizeFolders", "meanwhile.synchronize.Synchronize");
			className.put("createDocument", "meanwhile.document.Document");
			className.put("searchAndCopyFiles", "meanwhile.search.Search");
		}
		else {
			System.out.println("File Not Found");
		}
	}
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (FileInfo fileInfo : fileInfos) {
			if (fileInfo.isActive()) {
				String methodName = fileInfo.getMethod();
				Class clazz = Class.forName(className.get(methodName));
				Method method = clazz.getMethod(methodName, FileInfo.class);
				method.invoke(clazz, fileInfo);
			}
		}
	}
}
