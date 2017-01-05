package architect.example9;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AnyApplication9 implements CommandLineRunner {
	@Autowired
	AnyEntity9 anyEntity9;

	@Bean
	public AnyEntity9 getAnyEntity9() {
		return new AnyEntity9(getAnotherEntity9());// You are allowed to inject one bean to anther. 
	}
	
	@Bean
	public AnotherEntity9 getAnotherEntity9() {
		return new AnotherEntity9("Chen", "M");
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AnyApplication9.class, args);
	}

	public void run(String... arg0) throws Exception {
		System.out.println(anyEntity9);
	}
}
