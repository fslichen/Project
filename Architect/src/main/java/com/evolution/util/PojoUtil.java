package com.evolution.util;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class PojoUtil {
	public void patch(Object oldObject, Object newObject) {
		try {
			Field[] fields = oldObject.getClass().getDeclaredFields();
			for (Field field : fields) {
				try {
					field.setAccessible(true);
					Object value = field.get(newObject);
					if (value != null) {
						field.set(oldObject, value);
					}
				} catch (Exception e) {}
			}
		} catch (Exception e) {}
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T getField(Object object, String fieldName, Class<T> fieldClass) {
		try {
			if (object instanceof Map) {
				Map<String, Object> map = (Map<String, Object>) object;
				return (T) map.get(fieldName);
			} else {
				Field field = object.getClass().getDeclaredField(fieldName);
				field.setAccessible(true);
				return (T) field.get(object);
			}
		} catch (Exception e) {
			return null;
		}
	}

	public static void setField(Object object, String fieldName, Object fieldValue) {
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(object, fieldValue);
		} catch (Exception e) {}
	}
	
	public static String getField(String json, String field) {
		String string = new Gson().fromJson(json, Map.class).get(field).toString();
		try {
			float decimal = Float.valueOf(string);
			int integer = Math.round(decimal);
			if (decimal == integer) {
				return integer + "";
			}
			return decimal + "";
		} catch (Exception e) {
			return string;
		}
	}

	public static <T, V> Map<String, V> json2Map(String json, Class<T> classOfPojo, Class<V> classOfMapValue, String... exclusionArray) {
		return pojo2Map(new Gson().fromJson(json, classOfPojo), classOfMapValue, exclusionArray);
	}

	public static <T> T json2Pojo(String json, Class<T> clazz) {
		return new Gson().fromJson(json, clazz);
	}

	public static <T> T map2Pojo(Map<String, Object> map, Class<T> clazz, String... exclusionArray) {
		T t = null;
		try {
			t = clazz.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {}
		Field[] fields = t.getClass().getDeclaredFields();
		List<String> exclusions = null;
		if (exclusionArray != null) {
			exclusions = Arrays.asList(exclusionArray);
		}
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (exclusions != null && exclusions.contains(field.getName())) {
					continue;
				}
				field.set(t, map.get(field.getName()));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				System.out.println("Copy " + field.getName() + " failed.");
			}
		}
		return t;
	}

	public static <T> String pojo2Json(Object object) {
		return new Gson().toJson(object);
	}

	public static <T> Map<String, T> pojo2Map(Object object, Class<T> clazz, String... exclusionArray) {
		Field[] fields = object.getClass().getDeclaredFields();
		Map<String, T> map = new LinkedHashMap<>();
		List<String> exclusions = null;
		if (exclusionArray != null) {
			exclusions = Arrays.asList(exclusionArray);
		}
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (exclusions != null && exclusions.contains(field.getName())) {
					continue;
				}
				map.put(field.getName(), transfer(field.get(object), clazz));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				System.out.println("Copy " + field.getName() + " failed.");
			}
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	public static <T> T transfer(Object object, Class<T> clazz) {
		if ("String".equals(clazz.getSimpleName())) {
			return (T) (object + "");
		} else {
			return (T) object;
		}
	}
}
