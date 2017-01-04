package architect.example4;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnyController4 {
	@RequestMapping(value = "/anyMethod4", method = RequestMethod.POST)
	public void anyMethod4(@RequestBody byte[] byteArray) {
		for (int i = 0; i < byteArray.length; i++) {
			System.out.print(byteArray[i] + ",");
		}
	}
	
	@RequestMapping(value = "/anyMethod4/parameters", method = RequestMethod.POST)
	public void anyMethod4WithParameters(HttpServletRequest request, @RequestBody byte[] byteArray) {
		String anyParameter = request.getParameter("anyParameter");
		System.out.println(anyParameter);
		for (int i = 0; i < byteArray.length; i++) {
			System.out.print(byteArray[i] + ",");
		}
	}
}
