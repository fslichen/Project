package architect.example3;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnyController3 {
	@RequestMapping(value = "/anyMethod3A", method = RequestMethod.POST)
	public void anyMethod3A() {
		try {
			Thread.sleep(3000);
			System.out.println("Hello World");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/anyMethod3B", method = RequestMethod.POST)
	public void anyMethod3B() {
		try {
			Thread.sleep(3000);
			System.out.println("Goodbye Past");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/anyMethod3C", method = RequestMethod.POST)
	public void anyMethod3C(HttpServletRequest request, @RequestBody AnyEntity3 anyEntity3) {
		try {
			Thread.sleep(3000);
			String anyParameter = request.getParameter("anyParameter");
			System.out.println(anyParameter);
			System.out.println(anyEntity3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
