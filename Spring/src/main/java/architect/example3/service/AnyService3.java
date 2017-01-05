package architect.example3.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class AnyService3 {
	@Autowired
	ObjectMapper objectMapper;
	
	@Test
	public void anyMethod() {
		try {
			JsonNode jsonNode = objectMapper.readTree("{\"name\" : \"Chen\"}");
			String name = jsonNode.findValue("name").toString();
			System.out.println(name);
		} 
		catch (Exception e) {}
	}
}
