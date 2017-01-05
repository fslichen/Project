package com.evolution;

import com.ning.http.client.AsyncCompletionHandler;
import com.ning.http.client.AsyncHttpClient;
import com.ning.http.client.Response;

public class NoBlock {
	public static void main(String[] args) {
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		asyncHttpClient.prepareGet("http://localhost:8080/v1").execute(new AsyncCompletionHandler<Response>() {
			@Override
			public Response onCompleted(Response response) throws Exception {
				System.out.println("Hello World");
				return response;
			}
			
		    @Override
		    public void onThrowable(Throwable t){
		        System.out.println(t.getMessage() + " encounters a connection problem.");
		    }
		});
		asyncHttpClient.close();
	}
}
