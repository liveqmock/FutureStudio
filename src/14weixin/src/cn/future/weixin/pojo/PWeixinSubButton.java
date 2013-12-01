package cn.future.weixin.pojo;

public class PWeixinSubButton {
	private String type;
	private String name;
	private String key;
	
	public PWeixinSubButton(){
		super();
	}
	public PWeixinSubButton(String type, String name, String key) {
		super();
		this.type = type;
		this.name = name;
		this.key = key;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	
}
