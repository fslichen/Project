package architect.autowired;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class AnyService {
	@Autowired
	AnyEntity anyEntity;// Declare the bean in applicationContext.xml before autowiring it.

	@Autowired
	ObjectMapper objectMapper;// Third party bean can also be autowired, as long as you declare the bean in applicationContext.xml.
	
	@Test
	public void anyEntity() {
		anyEntity.setName("anyName");
		System.out.println(anyEntity);
	}
	
	@Test
	public void objectMapper() {
		try {
			JsonNode jsonNode = objectMapper.readTree("{\"name\" : \"Chen\"}");
			String name = jsonNode.findValue("name").toString();
			System.out.println(name);
		} 
		catch (Exception e) {}
	}
}
