package architect.example6.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AnyService6 {
	@Value("${name:Chen}")
	private String name;
	
	public String getName() {
		return this.name;
	}
}
