package architect.example1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration// Spring will guess what configurations you need.
public class AnyController1 {
	// Visit http://localhost:8080/anyController1
	@RequestMapping(value = "/anyController1", method = RequestMethod.GET)
	public Object anyMethod() {
		return "Hello World";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AnyController1.class, args);
	}
}
