package com.evolution.util;

import java.util.LinkedHashMap;
import java.util.Map;

public class MapUtil {
	@SuppressWarnings("unchecked")
	public static <T> Map<String, T> createMap(Class<T> clazz, Object... objects) {
		Map<String, T> map = new LinkedHashMap<>();
		for (int i = 0; i < objects.length; i += 2) {
			map.put((String) objects[i], (T) objects[i + 1]);
		}
		return map;
	}
}
