package architect.example7;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AnyApplication7 implements CommandLineRunner {
	@Bean// @Bean creates a bean, and the bean is put inside the container.
	public AnyEntity7 getAnyEntity7() {
		System.out.println("The bean has been created.");
		return new AnyEntity7("Chen", "M");
	}
	
	@Autowired// You can use @Autowired to inject the bean.
	// One drawback of spring annotation is that, your code is dependent upon spring, but if you treat spring and java as an unified entity that's fine.
	AnyEntity7 anyEntity7;
	
	public static void main(String[] args) {
		SpringApplication.run(AnyApplication7.class, args);
	}

	public void run(String... arg0) throws Exception {
		System.out.println("Entity = " + anyEntity7);
	}
}
