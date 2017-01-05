package architect.example2.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AnyController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		// Local
		System.out.println("Server IP = " + request.getLocalAddr());
		System.out.println("Server Host = " + request.getLocalName());
		System.out.println("Server Port = " + request.getLocalPort());
		// Remote
		System.out.println("Client IP = " + request.getRemoteAddr());
		System.out.println("Client Host = " + request.getRemoteHost());
		System.out.println("Client Port = " + request.getRemotePort());
		// Protocol
		System.out.println("Protocol = " + request.getProtocol());
	}
	
}
