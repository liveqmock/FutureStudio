package cn.future.weixin.pojo;

public class PWeixinMessageText extends PBaseWeixinMessage{

	private static final long serialVersionUID = 4256397236059693245L;
	
	public PWeixinMessageText(String msgId, String toUserName,
			String fromUserName, long createTime, String msgType,
			String Content) {
		super(msgId, toUserName, fromUserName, createTime, msgType);
		this.Content = Content;
	}
	public PWeixinMessageText() {
		super();
	}
	
	private String Content;
	
	public String getContent() {
		return Content;
	}
	public void setContent(String content) {
		Content = content;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
