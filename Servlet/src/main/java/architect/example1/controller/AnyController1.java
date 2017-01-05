package architect.example1.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnyController1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init() {
		System.out.println("The servlet is initialized.");
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("The is the get method.");
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("The is the post method.");
	}
	
	public void destroy() {
		System.out.println("The servlet has been destroyed.");
	}
}
