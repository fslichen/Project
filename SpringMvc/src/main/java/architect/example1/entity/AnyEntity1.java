package architect.example1.entity;

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
