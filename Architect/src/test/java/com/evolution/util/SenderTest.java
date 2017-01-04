package com.evolution.util;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class SenderTest {
	@Test
	public void testGetField() {
		String str = Sender.getField("{\"name\" : \"Chen\"}", "name");
		System.out.println(str);
	}
	
//	@Test
	public void test() {
		Map<String, String> requestParams = new HashMap<>();
		requestParams.put("name", "Chen");
		System.out.println(Sender.getRequestParamString(requestParams));
	}
	
//	@Test
	public void testSendFileWithoutParameters() {
		File file = new File("D:\\password.txt");
		Sender.uploadFile("http://localhost:8080/anyMethod5", null, file);
	}
	
//	@Test
	public void testSendFileWithParameters() {
		File file = new File("D:\\password.txt");
		Map<String, String> parameters = new HashMap<>();
		parameters.put("anyParameter", "Chen");
		Sender.uploadFile("http://localhost:8080/anyMethod4/parameters", parameters, file);
	}
}
