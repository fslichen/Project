package com.evolution.util;

import java.util.Map;

import org.junit.Test;

import com.evolution.entity.AnotherEntity;
import com.evolution.entity.AnyEntity;

public class PojoUtilTest {
	@Test
	public void test() {
		AnyEntity anyEntity = new AnyEntity();
		anyEntity.setName("Chen");
		anyEntity.setGender("M");
		AnotherEntity anotherEntity = new AnotherEntity();
		anotherEntity.setPhone("217-819-9008");
		anyEntity.setAnotherEntity(anotherEntity);
		Map<String, Object> map = PojoUtil.pojo2Map(anyEntity, Object.class);
		System.out.println(map);
		AnyEntity anyEntity0 = PojoUtil.map2Pojo(map, AnyEntity.class, "gender");
		System.out.println(anyEntity0);
	}
	
	@Test
	public void test0() {
		Map<String, String> map = PojoUtil.json2Map("{\"name\" : \"Chen\"}", AnyEntity.class, String.class, "gender", "anotherEntity");
		System.out.println(map);
	}
}
