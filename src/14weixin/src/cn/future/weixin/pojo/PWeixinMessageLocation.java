package cn.future.weixin.pojo;

public class PWeixinMessageLocation extends PBaseWeixinMessage{


	public PWeixinMessageLocation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PWeixinMessageLocation(String msgId, String toUserName,
			String fromUserName, long createTime, String msgType,
			String Location_X, String Location_Y, String Scale,
			String Label) {
		super(msgId, toUserName, fromUserName, createTime, msgType);
		this.Location_X = Location_X;
		this.Location_Y = Location_Y;
		this.Scale = Scale;
		this.Label = Label;
	}
	private static final long serialVersionUID = 1448120575940238939L;

	private String Location_X;
	private String Location_Y;
	private String Scale;
	private String Label;
	public String getLocation_X() {
		return Location_X;
	}
	public void setLocation_X(String location_X) {
		Location_X = location_X;
	}
	public String getLocation_Y() {
		return Location_Y;
	}
	public void setLocation_Y(String location_Y) {
		Location_Y = location_Y;
	}
	public String getScale() {
		return Scale;
	}
	public void setScale(String scale) {
		Scale = scale;
	}
	public String getLabel() {
		return Label;
	}
	public void setLabel(String label) {
		Label = label;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
