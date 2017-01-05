package architect.example3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@ComponentScan// The default scope of @ComponentScan is the package or sub-packages of AnyController3
@EnableAutoConfiguration// You can summarize @ComponentScan and @EnableAutoConfiguration as @SpringBootApplication.
@RestController// You cannot remove @RestController because @SpringBootApplication does not contain the @RestController.
public class AnyController3 {
	@Autowired
	AnyService3 anyService3;
	
	@RequestMapping(value = "/anyController3", method = RequestMethod.GET)
	public Object anyMethod() {
		return anyService3.anyMethod();
	}
	public static void main(String[] args) {
		SpringApplication.run(AnyController3.class, args);
	}

}
