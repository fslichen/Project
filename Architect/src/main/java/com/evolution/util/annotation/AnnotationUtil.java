package com.evolution.util.annotation;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;

import com.google.gson.Gson;

public class AnnotationUtil {
	public static String[] getPaths(Method method) {
		return method.getDeclaredAnnotation(Path.class).value();
	}
	
	public static Map<Class<? extends Object>, String> getJsons(Method method) {
		Jsons json = method.getDeclaredAnnotation(Jsons.class);
		Class<? extends Object>[] classes = json.classes();
		String[] paths = json.paths();
		int length = classes.length;
		if (length != paths.length) {
			return null;
		}
		Map<Class<? extends Object>, String> jsons = new LinkedHashMap<>();
		for (int i = 0; i < length; i++) {
			InputStream inputStream = AnnotationUtil.class.getClassLoader().getResourceAsStream(paths[i]);
			try {
				jsons.put(classes[i], IOUtils.toString(inputStream, "UTF-8"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsons;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getJson(Method method) {
		Json json = method.getDeclaredAnnotation(Json.class);
		Class<? extends Object> clazz = json.clazz();
		String path = json.path();
		InputStream inputStream = AnnotationUtil.class.getClassLoader().getResourceAsStream(path);
		try {
			return (T) new Gson().fromJson(IOUtils.toString(inputStream, "UTF-8"), clazz);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
