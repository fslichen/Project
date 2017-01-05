package architect.example2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication// Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan.
public class AnyClass2 {
	public static void main(String[] args) {
		ApplicationContext applicationContext = 
				SpringApplication.run(AnyClass2.class, args);
		String[] beanNames = applicationContext.getBeanDefinitionNames();
		for (String beanName : beanNames) {
			System.out.println(beanName);// Spring Boot automatically inject all the corresponding beans.
		}
	}
}
