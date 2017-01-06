package architect.servlet;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnyController {
	@RequestMapping(value = "/servlet", method = RequestMethod.POST) 
	public void anyMethod(HttpServletRequest request) {
		// If you are getting the parameters from HttpServletRequest, do it like http://wwww.anyWebsite.com?value=anyValue.
		// You can use @RequestParam instead.
		String parameter = request.getParameter("parameter");
		System.out.println(parameter);
	}
}
