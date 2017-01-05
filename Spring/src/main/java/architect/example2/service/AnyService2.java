package architect.example2.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import architect.example2.entity.AnyEntity2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class AnyService2 {
	@Autowired
	AnyEntity2 anyEntity2;
	
	@Test
	public void anyMethod() {
		anyEntity2.setName("Chen");
		System.out.println(anyEntity2);
	}
}
