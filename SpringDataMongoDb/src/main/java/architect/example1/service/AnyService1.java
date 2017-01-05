package architect.example1.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import architect.example1.entity.AnyEntity1;
import architect.example1.repository.AnyRepository1;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml", "/applicationContext-mongo.xml"})
public class AnyService1 {
	@Autowired
	MongoTemplate mongoTemplate;
	
	@Autowired
	AnyRepository1 anyRepository;
	
	@Test
	public void anyMethod() {
		Criteria criteria = new Criteria();
		criteria.and("name").is("Chen");
		List<AnyEntity1> anyEntities = mongoTemplate.find(new Query().addCriteria(criteria), AnyEntity1.class);
		System.out.println(anyEntities);
		anyEntities = anyRepository.findByName("Chen");
		System.out.println(anyEntities);
	}
}
