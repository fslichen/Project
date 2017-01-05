package architect.example4.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import architect.example4.entity.AnyEntity4;

public class AnyController4 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		InputStream inputStream = request.getInputStream();
		AnyEntity4 anyEntity4 = new ObjectMapper().readValue(inputStream, AnyEntity4.class);
		System.out.println(anyEntity4);
	}
	
}
