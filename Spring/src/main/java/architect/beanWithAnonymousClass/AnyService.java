package architect.beanWithAnonymousClass;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnyService {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AnyConfigurationClass.class);
		AnyClass anyClass = context.getBean(AnyClass.class);
		String anyString = anyClass.anyMethod();
		System.out.println(anyString);
	}
}
