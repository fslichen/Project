package com.evolution.util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class StringUtil {
	public static double getMaxSimilarity(String templateString, List<String> strings) {
		double maxSimilarity = -Double.MAX_VALUE;
		for (String string : strings) {
			double similarity = getSimilarity(templateString, string);
			if (maxSimilarity < similarity) {
				maxSimilarity = similarity;
			}
		}
		return maxSimilarity;
	}
	
	public static double getMaxSimilarity(String templateString, String... strings) {
		return getMaxSimilarity(templateString, Arrays.asList(strings));
	}
	
	public static double getSimilarity(String string0, String string1) {
		// String0
		Map<String, Integer> map0 = new HashMap<>();
		for (int i = 0; i < string0.length(); i++) {
			String character = string0.charAt(i) + "";
			Integer count = map0.get(character);
			if (count == null) {
				map0.put(character, 1);
			} else {
				map0.put(character, count + 1);
			}
		}
		double norm0 = 0;
		for (Integer count : map0.values()) {
			norm0 += Math.pow(count, 2);
		}
		norm0 = Math.pow(norm0, .5);
//		System.out.println(map0);
//		System.out.println(norm0);
		// String1
		Map<String, Integer> map1 = new HashMap<>();
		for (int i = 0; i < string1.length(); i++) {
			String character = string1.charAt(i) + "";
			Integer count = map1.get(character);
			if (count == null) {
				map1.put(character, 1);
			} else {
				map1.put(character, count + 1);
			}
		}
		double norm1 = 0;
		for (Integer count : map1.values()) {
			norm1 += Math.pow(count, 2);
		}
		norm1 = Math.pow(norm1, .5);
		// Get the similarity.
		double similarity = 0;
		for (Entry<String, Integer> entry : map0.entrySet()) {
			String key = entry.getKey();
			Integer value0 = entry.getValue();
			Integer value1 = map1.get(key);
			if (value1 == null) {
				value1 = 0;
			}
			similarity += value0 * value1;
		}
		return similarity / norm0 / norm1;
	}
	
	public static String getString(String string) {
		if (string == null) {
			return "null";
		} 
		return string;
	}
}
