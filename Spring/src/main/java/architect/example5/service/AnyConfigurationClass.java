package architect.example5.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class AnyConfigurationClass {
	@Bean
	public AnyClass getAnyClass() {
		return new AnyClass() {
			public String anyMethod() {
				return "Hello World";
			}
		};
	}
}
