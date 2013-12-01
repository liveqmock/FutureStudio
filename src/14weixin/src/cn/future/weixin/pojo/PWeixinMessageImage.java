package cn.future.weixin.pojo;

public class PWeixinMessageImage extends PBaseWeixinMessage{

	public PWeixinMessageImage() {
		super();
	}
	public PWeixinMessageImage(String msgId, String toUserName,
			String fromUserName, long createTime, String msgType,
			String PicUrl) {
		super(msgId, toUserName, fromUserName, createTime, msgType);
		this.PicUrl = PicUrl;
	}
	private static final long serialVersionUID = -6089980560249147248L;
	private String PicUrl;
	public String getPicUrl() {
		return PicUrl;
	}
	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
