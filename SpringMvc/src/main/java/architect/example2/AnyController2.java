package architect.example2;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnyController2 {
	@RequestMapping(value = "/anyMethod2", method = RequestMethod.POST) 
	public void anyMethod2(HttpServletRequest request) {
		// If you are getting the parameters from HttpServletRequest, set the Params in Postman, do not send the content by Json.
		String string = request.getParameter("name");
		System.out.println(string);
	}
}
