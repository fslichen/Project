package architect.example4.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import architect.example4.entity.AnyEntity4;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class AnyService4 {
	@Autowired
	AnyEntity4 anyEntity4;
	
	@Test
	public void anyMethod() {
		anyEntity4.setName("Chen");
		System.out.println(anyEntity4);
	}
}
