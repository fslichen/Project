package architect.example1.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import architect.example1.entity.AnyEntity1;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class AnyService1 {
	@Autowired
	AnyEntity1 anyEntity1;
	
	@Test
	public void anyMethod() {
		anyEntity1.setName("Chen");
		System.out.println(anyEntity1.getName());		
	}
}
