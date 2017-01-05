package architect.example3;

import org.springframework.stereotype.Component;

@Component// You can't discard this tag.
public class AnyService3 {
	public String anyMethod() {
		return "Hello World";
	}
}
