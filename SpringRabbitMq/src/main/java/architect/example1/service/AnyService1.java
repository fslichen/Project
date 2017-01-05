package architect.example1.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@Service
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class AnyService1 {
	@Test
	public void anyMethod() {
		// Host Name and Port Number
		ConnectionFactory connectionFactory = new CachingConnectionFactory("127.0.0.1", 5672);
		AmqpAdmin amqpAdmin = new RabbitAdmin(connectionFactory);
		amqpAdmin.declareQueue(new Queue("anyQueue"));
		AmqpTemplate amqpTemplate = new RabbitTemplate(connectionFactory);
		amqpTemplate.convertAndSend("anyQueue", "anyMessage");
		String anyMessage = (String) amqpTemplate.receiveAndConvert("anyQueue");
		System.out.println(anyMessage);
	}
}
