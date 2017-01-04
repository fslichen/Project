package mock.bean.entity;

import java.util.Date;

public class BindProtocol {
	private String id;
	private String type;//蓝牙，wifi，扫码
	private String key;//type下唯一
	private String name;//协议名称
	private Date createTime;
	private Date updateTime;
}
