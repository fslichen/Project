package architect.example8;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class AnyEntity8 {
	@Value("${name:Chen}")
	String name;
}
