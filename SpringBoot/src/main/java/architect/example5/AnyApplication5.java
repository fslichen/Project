package architect.example5;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import architect.example5.service.AnyService5;

@SpringBootApplication// Combines @Configuration, @EnableAutoConfiguration, and @ComponentScan
// The scope of @ComponentScan is the package and the sub-packages of AnyApplication5. 
// For instance, it cannot scan the dependencies of example4, but it can scan all the dependencies of example5
public class AnyApplication5 implements CommandLineRunner {
	@Autowired// @Autowired is still needed in this case. 
	AnyService5 anyService5;
	
	public void run(String... arg0) throws Exception {// It will print all the result on the commander line.
		System.out.println(this.anyService5.anyMethod());
	}
	 
	public static void main(String[] args) {
		SpringApplication.run(AnyApplication5.class, args);
	}
	
}
