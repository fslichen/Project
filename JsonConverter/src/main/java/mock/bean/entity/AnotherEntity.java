package mock.bean.entity;

import java.util.List;

import lombok.Data;

@Data
public class AnotherEntity {
	private String address;
	private String licensePlate;
	private List<TheOtherEntity> theOtherEntities;
}
