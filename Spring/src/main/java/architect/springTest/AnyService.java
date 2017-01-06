package architect.springTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class AnyService {
	@Autowired
	AnyEntity anyEntity;
	
	@Test// You don't need to start the server to run the test.
	public void anyMethod() {
		anyEntity.setName("anyName");
		System.out.println(anyEntity.getName());		
	}
}
