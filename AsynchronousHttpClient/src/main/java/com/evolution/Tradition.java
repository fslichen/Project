package com.evolution;

import java.util.concurrent.Future;

import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

public class Tradition {
	public static void main(String[] args) {
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		Future<Response> future = asyncHttpClient.prepareGet("http://localhost:8080/v1").execute();
		try {
			Response response = future.get();
			String responseBody = response.getResponseBody();
			System.out.println(String.format("The response body is %s", responseBody));
		} catch (Exception e) {
			e.printStackTrace();
		}
		asyncHttpClient.close();
	}
}
