package architect.javaConfiguration;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AnyService {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AnyConfiguration.class);
		AnyEntity anyEntity = applicationContext.getBean(AnyEntity.class);
		anyEntity.setName("anyName");
		System.out.println(anyEntity);
	}
}
