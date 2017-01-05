package architect.example8;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnyApplication8 implements CommandLineRunner {
	@Autowired
	AnyEntity8 anyEntity8;
	
	public AnyApplication8() {
		System.out.println(anyEntity8);// The bean is not yet created. It's created inside the method annotated by @PostConstruct. 
	}
	
	@PostConstruct// PostConstruct is like a late constructor that is called after the actual constructor is called. 
	public void anyConstructor() {
		System.out.println(anyEntity8);// The bean is already created. 
	}
	
	public static void main(String[] args) {
		SpringApplication.run(AnyApplication8.class, args);
	}

	public void run(String... arg0) throws Exception {
	
	}
}
