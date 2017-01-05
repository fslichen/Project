package architect.example1.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "any_entity")
public class AnyEntity1 {
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "AnyEntity1 [name=" + name + "]";
	}
	
}
