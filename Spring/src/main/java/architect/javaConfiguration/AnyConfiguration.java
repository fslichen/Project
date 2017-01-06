package architect.javaConfiguration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AnyConfiguration {
	@Bean// Same as <bean/> in xml configuration.
	public AnyEntity anyEntity() {
		return new AnyEntity();
	}
}
