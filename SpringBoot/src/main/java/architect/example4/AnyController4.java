package architect.example4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication// Combines @ComponentScan, @EnableAutoConfiguration, and @Configuration.
public class AnyController4 {
	@Autowired
	AnyService4 anyService4;// AnyService4 has to be under the same package of the AnyController4
	
	@RequestMapping(value = "/anyController4", method = RequestMethod.GET)
	public Object anyMethod() {
		return anyService4.anyMethod();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AnyController4.class, args);
	}
}
