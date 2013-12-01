package cn.future.weixin.pojo;

public class PWeixinMessageLink extends PBaseWeixinMessage {

	public PWeixinMessageLink() {
		super();
	}
	public PWeixinMessageLink(String msgId, String toUserName,
			String fromUserName, long createTime, String msgType,
			String Title, String Description, String Url) {
		super(msgId, toUserName, fromUserName, createTime, msgType);
		this.Title = Title;
		this.Description = Description;
		this.Url = Url;
	}
	private static final long serialVersionUID = 8974407661260020438L;

	private String Title;
	private String Description;
	private String Url;
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getUrl() {
		return Url;
	}
	public void setUrl(String url) {
		Url = url;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
