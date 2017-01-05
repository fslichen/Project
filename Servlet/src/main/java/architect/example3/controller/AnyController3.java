package architect.example3.controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnyController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream inputSteram = request.getInputStream();
		byte[] buffer = new byte[1024];
		int length = -1;
		while((length = inputSteram.read(buffer)) != -1) {
			System.out.println(Arrays.toString(buffer));
		}
	}
	
}
