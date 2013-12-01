package cn.future.weixin.pojo;

public class PWeixinMessageEvent extends PBaseWeixinMessage{

	public PWeixinMessageEvent() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PWeixinMessageEvent(String msgId, String toUserName,
			String fromUserName, long createTime, String msgType,
			String Event, String EventKey) {
		super(msgId, toUserName, fromUserName, createTime, msgType);
		this.Event = Event;
		this.EventKey = EventKey;
	}
	private static final long serialVersionUID = 4769541317698177284L;

	//注意，事件消息没有msgid
	private String Event;
	private String EventKey;

	public String getEvent() {
		return Event;
	}
	public void setEvent(String event) {
		Event = event;
	}
	public String getEventKey() {
		return EventKey;
	}
	public void setEventKey(String eventKey) {
		EventKey = eventKey;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
