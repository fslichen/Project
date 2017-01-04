package com.evolution.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingClientConnectionManager;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Sender {
	public static Future<String> asynchronousSend(String url) {
		return asynchronousSend(url, null, null);
	}
	
	public static Future<String> asynchronousSend(String url, Map<String, String> parameters) {
		return asynchronousSend(url, parameters, null);
	}
	
	public static Future<String> asynchronousSend(final String url, final Map<String, String> parameters, final Object data) {
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Callable<String> callable = new Callable<String>() {
			@Override
			public String call() {
				return send(url, parameters, data);
			}
		};
		Future<String> future = executor.submit(callable);
		executor.shutdown();
		return future;
	}
	
	public static Future<String> asynchronousSend(String url, Object data) {
		return asynchronousSend(url, null, data);
	}
	
	public static Future<String> asynchronousUploadFile(String url, File file) {
		return asynchronousUploadFile(url, null, file);
	}
	
	public static Future<String> asynchronousUploadFile(final String url, final Map<String, String> parameters, final File file) {
		try {
			ExecutorService executor = Executors.newSingleThreadExecutor();
			Callable<String> callable = new Callable<String>() {
				@Override
				public String call() {
					return uploadFile(url, parameters, file);
				}
			};
			return executor.submit(callable);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static List<Future<String>> asynchronousUploadFiles(String url, List<File> files) {
		return asynchronousUploadFiles(url, null, files);
	}
	
	public static List<Future<String>> asynchronousUploadFiles(String url, Map<String, String> parameters, List<File> files) {
		try {
			List<Future<String>> futures = new LinkedList<>();
			for (File file : files) {
				futures.add(asynchronousUploadFile(url, parameters, file));
			}
			return futures;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@SuppressWarnings("deprecation")
	public static HttpClient createByHacking(int port) {
		TrustStrategy trustStrategy = new TrustStrategy() {
			@Override
			public boolean isTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {
				return true;
			}
		};
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		SSLSocketFactory sslSocketFactory = null;
		try {
			sslSocketFactory = new SSLSocketFactory(trustStrategy, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		} 
		catch (KeyManagementException | UnrecoverableKeyException | NoSuchAlgorithmException | KeyStoreException e) {}
		schemeRegistry.register(new Scheme("https", port, sslSocketFactory));
		return new DefaultHttpClient(new PoolingClientConnectionManager(schemeRegistry));
	}

	@SuppressWarnings("unchecked")
	public static <T> T createHttp(Class<T> clazz, String url, Map<String, String> headers, Map<String, String> requestParams) {
		try {
			T t = null;
			url += getRequestParamString(requestParams);
			if (clazz.getName() == HttpGet.class.getName()) {
				HttpGet httpGet = new HttpGet(url);
				t = (T) httpGet;
			} else if (clazz.getName() == HttpPost.class.getName()) {
				HttpPost httpPost = new HttpPost(url);
				t = (T) httpPost;
			} else if (clazz.getName() == HttpPatch.class.getName()) {
				HttpPatch httpPatch = new HttpPatch(url);
				t = (T) httpPatch;
			} else if (clazz.getName() == HttpDelete.class.getName()) {
				HttpDelete httpDelete = new HttpDelete(url);
				t = (T) httpDelete;
			} 
			if (headers != null) {
				Method setHeader = t.getClass().getMethod("setHeader", String.class, String.class);
				for (Entry<String, String> entry : headers.entrySet()) {
					setHeader.invoke(t, entry.getKey(), entry.getValue());
				}
			}
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String delete(String url, Map<String, String> headers, Map<String, String> requestParams) {
		try {
			return toString(HttpClientBuilder.create().build().execute(createHttp(HttpDelete.class, url, headers, requestParams)).getEntity().getContent());
		} catch (Exception e) {
			return null;
		}
	}

	public static String encodeUrl(String url) {
		try {
			List<String> illegalCharacters = Arrays.asList("{", "}", ",", "[", "]", "\"", " ");
			for (String illegalCharacter : illegalCharacters) {
				url = url.replace(illegalCharacter, URLEncoder.encode(illegalCharacter, "UTF-8"));
			}
			return url;
		} catch (UnsupportedEncodingException e) {
			return null;
		}
	}

	public static <T> T fromJson(String json, Class<T> clazz) {
		return new Gson().fromJson(json, clazz);
	}

	public static <T> List<T> fromJsons(String json, Class<T> clazz) {
		return new Gson().fromJson(json, new TypeToken<LinkedList<T>>(){}.getType());
	}
	
	public static String get(String url, Map<String, String> headers, Map<String, String> requestParams) {
		try {
			return toString(HttpClientBuilder.create().build().execute(createHttp(HttpGet.class, url, headers, requestParams)).getEntity().getContent());
		} catch (Exception e) {
			return null;
		}
	}
	
	public static String getField(String json, String fieldName) {
		int firstIndex = json.indexOf(fieldName);
		int lastIndex = json.lastIndexOf(fieldName);
		if (firstIndex != lastIndex) {// There are more than one fields under the given field name.
			return null;
		}
		int colonIndex = 0;
		for (int i = firstIndex; i < json.length(); i++) {
			if (json.charAt(i) == ':') {
				colonIndex = i;
				break;
			}
		}
		boolean isFirstIndex = true;
		for (int i = colonIndex + 1; i < json.length(); i++) {
			if (json.charAt(i) == '"') {
				if (isFirstIndex) {
					firstIndex = i;
					isFirstIndex = false;
				} else {
					lastIndex = i;
					break;
				}
			}
		}
		if (firstIndex + 1 < lastIndex) {
			return json.substring(firstIndex + 1, lastIndex);
		} 
		return "";
	}
	
	public static String getFutureReturnValue(Future<String> future) {
		try {
			return future.get();
		} catch (Exception e) {
			return null;
		}
	}

	public static List<String> getFutureReturnValues(List<Future<String>> futures) {
		List<String> returnValues = new LinkedList<>();
		for (Future<String> future : futures) {
			returnValues.add(getFutureReturnValue(future));
		}
		return returnValues;
	}

	@Deprecated
	public static String getRequestParameters(Map<String, String> parameters) {
		if (parameters != null) {
			StringBuilder url = new StringBuilder("?");
			for (Entry<String, String> entry : parameters.entrySet()) {
				url.append(entry.getKey() + "=" + entry.getValue() + "&");
			}
			url.deleteCharAt(url.length() - 1);
			return url.toString();
		} else {
			return "";
		}
	}

	@Deprecated
	public static String getRequestParameters(String... keysAndValues) {
		if (keysAndValues.length % 2 != 0) {
			return null;
		}
		StringBuilder url = new StringBuilder("?");
		for (int i = 0; i < keysAndValues.length; i += 2) {
			url.append(keysAndValues[i] + "=" + keysAndValues[i + 1] + "&");
		}
		url.deleteCharAt(url.length() - 1);
		return url.toString();
	}

	public static String getRequestParamString(Map<String, String> requestParams) {
		URIBuilder builder = new URIBuilder();
		if (requestParams == null) {
			return "";
		}
		for (Entry<String, String> entry : requestParams.entrySet()) {
			builder.setParameter(entry.getKey(), entry.getValue());
		}
		try {
			return builder.build().toString();
		} catch (URISyntaxException e) {
			return "";
		}
	}

	public static String getRequestParamString(String... keysAndValues) {
		URIBuilder builder = new URIBuilder();
		if (keysAndValues.length % 2 != 0) {
			return "";
		}
		for (int i = 0; i < keysAndValues.length; i += 2) {
			builder.setParameter(keysAndValues[i], keysAndValues[i + 1]);
		}
		try {
			return builder.build().toString();
		} catch (URISyntaxException e) {
			return "";
		}
	}

	public static String patch(String url, Object object, Map<String, String> headers, Map<String, String> requestParams) {
		try {
			HttpPatch httpPatch = createHttp(HttpPatch.class, url, headers, requestParams);
			Gson gson = new Gson();
			StringEntity stringEntity = new StringEntity(gson.toJson(object), "UTF-8");
			stringEntity.setContentType("application/json");
			httpPatch.setEntity(stringEntity);
			return toString(HttpClientBuilder.create().build().execute(httpPatch).getEntity().getContent());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String plugInPathVariables(String url, String... keysOrValues) {
		for (int i = 0; i < keysOrValues.length; i += 2) {
			url = url.replace("{" + keysOrValues[i] + "}", keysOrValues[i + 1]);
		}
		return url;
	}
	
	public static String post(String url, Object object, Map<String, String> headers, Map<String, String> requestParams) {
		try {
			HttpPost httpPost = createHttp(HttpPost.class, url, headers, requestParams);
			Gson gson = new Gson();
			StringEntity stringEntity = new StringEntity(gson.toJson(object), "UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
			return toString(HttpClientBuilder.create().build().execute(httpPost).getEntity().getContent());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String send(String url) {
		return send(url, null, null);
	}

	public static String send(String url, Map<String, String> parameters) {
		return send(url, parameters, null);
	}

	public static String send(String url, Map<String, String> parameters, Object data) {
		try {
			StringEntity stringEntity = new StringEntity(new Gson().toJson(data), "UTF-8");
			stringEntity.setContentType("application/json");
			HttpPost httpPost = new HttpPost(encodeUrl(url + getRequestParameters(parameters)));
			httpPost.setEntity(stringEntity);
			return toString(HttpClientBuilder.create().build().execute(httpPost).getEntity().getContent());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String send(String url, Object data) {
		return send(url, null, data);
	}

	public static <T> List<T> sendAndReceiveMany(String url, Class<T> clazz) {
		return fromJsons(send(url, null, null), clazz);
	}

	public static <T> List<T> sendAndReceiveMany(String url, Map<String, String> parameters, Class<T> clazz) {
		return fromJsons(send(url, parameters, null), clazz);
	}

	public static <T> List<T> sendAndReceiveMany(String url, Map<String, String> parameters, Object data, Class<T> clazz) {
		return fromJsons(send(url, parameters, data), clazz);
	}

	public static <T> List<T> sendAndReceiveMany(String url, Object data, Class<T> clazz) {
		return sendAndReceiveMany(url, null, data, clazz);
	}

	public static <T> T sendAndReceiveOne(String url, Class<T> clazz) {
		return fromJson(send(url, null, null), clazz);
	}

	public static <T> T sendAndReceiveOne(String url, Map<String, String> parameters, Class<T> clazz) {
		return fromJson(send(url, parameters, null), clazz);
	}

	public static <T> T sendAndReceiveOne(String url, Map<String, String> parameters, Object data, Class<T> clazz) {
		return fromJson(send(url, parameters, data), clazz);
	}

	public static <T> T sendAndReceiveOne(String url, Object data, Class<T> clazz) {
		return fromJson(send(url, null, data), clazz);
	}
	
	public static String toString(InputStream inputStream) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		while ((line = bufferedReader.readLine()) != null) {
			stringBuilder.append(line);
		}
		return stringBuilder.toString();
	}
	
	public static String uploadFile(String url, File file) {
		return uploadFile(url, null, file);
	}
	
	public static String uploadFile(String url, Map<String, String> parameters, File file) {
		try {
			return uploadFile(url, parameters, file.getName(), new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String uploadFile(String url, Map<String, String> parameters, String filename, InputStream inputStream) {
		try {
			HttpPost httpPost = new HttpPost(encodeUrl(url + getRequestParameters(parameters)));
			httpPost.setEntity(MultipartEntityBuilder.create().addTextBody("fileName", filename, ContentType.TEXT_PLAIN).addBinaryBody("file", inputStream, FileUtil.getContentType(filename), filename).build());
			return toString(HttpClients.createDefault().execute(httpPost).getEntity().getContent());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
