package cn.future.weixin.message.request;
/**
 * 文本消息
 */
public class ReqTextMessage extends ReqBaseMessage {
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}
	
}
