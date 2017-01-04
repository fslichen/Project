package mock.bean.entity;

import java.util.Date;
import java.util.List;

public class Device {

	private String _id;
	private String deviceId;
	private String productId;
	private String supplierId;
	private String supplierName;
	private String serialNumber;
	private List<Channel> channels;
	private List<Sensor> sensors;
	private String name;
	private String keywords;
	private String description;
	private String key;
	private String password;
	private String imageUrl;
	private String thumbnailUrl;
	private String category;//����
	private List<Bind> binds;//����ϸ˵��
	private Date createTime;
	private Date updateTime;
}
