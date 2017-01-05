package architect.example1.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import architect.example1.entity.AnyEntity1;

public interface AnyRepository1 extends MongoRepository<AnyEntity1, String> {
	@Query(value = "{'name':?0}")
	public List<AnyEntity1> findByName(String name);
}
