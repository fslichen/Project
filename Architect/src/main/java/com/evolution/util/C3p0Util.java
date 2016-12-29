package com.evolution.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class C3p0Util {
	public static Connection connection;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://522.165.2.345:4728/git", "elf", "playboy");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static List<Map<String, Object>> query(String sql) {
	    try {
	    	ResultSet resultSet = connection.createStatement().executeQuery(sql);
	    	List<Map<String, Object>> results = new ArrayList<>();
	    	ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
	    	int columnCount = resultSetMetaData.getColumnCount();
	    	while (resultSet.next()) {
	    		Map<String, Object> result = new HashMap<>();
	    		for (int i = 1; i <= columnCount; i++) {
	    			result.put(resultSetMetaData.getColumnName(i), resultSet.getObject(i));
	    		}
	    		results.add(result);
	    	}
	    	return results;
	    } catch (Exception ex) {
	    	ex.printStackTrace();
	    	return null;
	    } 
	}
}
