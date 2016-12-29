package com.evolution.util.annotation;

import java.util.Arrays;
import java.util.Map;

import org.junit.Test;

import com.evolution.util.annotation.entity.Boy;
import com.evolution.util.annotation.entity.Girl;

public class AnnotationServiceTest {
	@Test
	@Path({"Hello", "World"})
	public void testPath() {
		try {
			System.out.println(Arrays.asList(AnnotationUtil.getPaths(this.getClass().getMethod("testPath"))));
		} catch (NoSuchMethodException | SecurityException e) {}
	}
	
	@Test
	@Jsons(classes = {Boy.class, Girl.class}, paths = {"boy.json", "girl.json"})
	public void testJsons() {
		try {
			Map<Class<? extends Object>, String> map = AnnotationUtil.getJsons(this.getClass().getMethod("testJsons"));
			System.out.println(map);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	@Json(clazz = Boy.class, path = "boy.json")
	public void testJson() {
		try {
			Boy boy = AnnotationUtil.getJson(this.getClass().getMethod("testJson"));
			System.out.println(boy);
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
	}
}
