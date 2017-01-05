package architect.example6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import architect.example6.service.AnyService6;

@Configuration
@EnableAutoConfiguration
@ComponentScan
// The three tags can be summarized as @SpringBootConfiguration.
public class AnyApplication6 implements CommandLineRunner {
	@Autowired
	AnyService6 anyService6;

	public void run(String... arg0) throws Exception {
		System.out.println(anyService6.getName());
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AnyApplication6.class, args);
	}
	
}
