package mock.bean.run;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.Map;

public class Run {
	private final static Map<String, String> DATA;
	static {
		DATA = new HashMap<String, String>();
		DATA.put("String", "\"anyString\"");
		DATA.put("Date", "ISODate(\"1900-01-01T01:00:00.100Z\")");
		DATA.put("Integer", "0");
		DATA.put("int", "0");
		DATA.put("Double", "0");
		DATA.put("double", "0");
		DATA.put("Boolean", "false");
		DATA.put("boolean", "false");
	}
	public static void main(String[] args) throws ClassNotFoundException {
		getJson("Doctor");
	}
	public static String getJson(String entity) throws ClassNotFoundException {
		Class clazz = null;
		clazz = Class.forName("mock.bean.entity." + entity);
		Field[] fields = clazz.getDeclaredFields();
		StringBuilder json = new StringBuilder("{");
		for (Field field : fields) {
			String type = field.getType().getSimpleName();
			boolean isList = false;
			if ("List".equals(type)) {
				ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
				Class subClass = (Class) parameterizedType.getActualTypeArguments()[0];
				type = subClass.getSimpleName();
				isList = true;
			}
			String name = field.getName();
			String data = null;
			if (DATA.containsKey(type)) {
				data = DATA.get(type);
			}
			else if (!entity.equals(type)) {
				data = getJson(type);
			}
			else {
				System.out.println("Recursive definition is not allowed in " + entity + ".");
				System.exit(0);	
			}
			if (!isList) {
				json.append("\"" + name + "\":" + data + ",");
			}
			else {
				json.append("\"" + name + "\":[" + data + "],");
			}
		}
		json.deleteCharAt(json.length() - 1); // Delete the extra comma.
		json.append("}");
		System.out.println("{\"" + entity + "\" : " + json + "}");
		return json.toString();
	}
}
