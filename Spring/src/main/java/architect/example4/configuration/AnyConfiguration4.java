package architect.example4.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import architect.example4.entity.AnyEntity4;

@Configuration
public class AnyConfiguration4 {
	@Bean
	public AnyEntity4 anyEntity4() {
		return new AnyEntity4();
	}
}
