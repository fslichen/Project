package mock.bean.entity;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class AnyEntity {
	private String name;
	private String gender;
	private List<Date> dates;
	private List<AnotherEntity> anotherEntities;
	private List<String> strings;
	private double grade;
}
