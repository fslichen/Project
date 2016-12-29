package com.evolution.util;

import java.io.File;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

public class FileUtilTest {
	@Test
	public void testGetContentType() {
		System.out.println(FileUtil.getContentType(new File("D:\\DonaldTrump.jpg")));
	}
	
	@Test
	public void testGetAudioDuration() {
		System.out.println(FileUtil.getAudioDuration(new File("D:\\Bird.wav")));
	}
	
	@Test
	public void testUnzip() {
		FileUtil.unzip("D:/Data.zip", "D:/Unzip");
	}
	
	@Test
	public void testGetLocalFilesAndRemotePaths() {
		Map<String, String> localFilesAndRemotePaths = new TreeMap<>();
		SshUtil.getLocalFilesAndRemotePaths("D:/Data", "/var/www/easemob-webapp", localFilesAndRemotePaths);
		System.out.println(localFilesAndRemotePaths);
	}
}
