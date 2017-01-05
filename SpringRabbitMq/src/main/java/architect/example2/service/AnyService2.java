package architect.example2.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class AnyService2 {
	@Test
	public void anyMethod() {
		ApplicationContext applicationContext =
			    new GenericXmlApplicationContext("classpath*:/rabbit-context.xml");
		AmqpTemplate amqpTemplate = applicationContext.getBean(AmqpTemplate.class);
		amqpTemplate.convertAndSend("anyQueue", "anyMessage");
		String anyMessage = (String) amqpTemplate.receiveAndConvert("anyQueue");
		System.out.println(anyMessage);
	}
}
