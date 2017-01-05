package architect.example1.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnyController1 {
	@RequestMapping(value = "/example1", method = RequestMethod.GET)
	public void anyMethod() {
		System.out.println("Hello World");
	}
}
