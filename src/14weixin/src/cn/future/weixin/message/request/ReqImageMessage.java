package cn.future.weixin.message.request;
/**
 * 图片消息
 */
public class ReqImageMessage extends ReqBaseMessage {
	private String PicUrl;

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}
	
	
}
