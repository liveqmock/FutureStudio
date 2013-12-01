package cn.future.weixin.pojo;

import java.io.Serializable;

public class PBaseWeixinMessage implements Serializable{

	private static final long serialVersionUID = 8419477842407770487L;
	private String MsgId; //64位
	private String ToUserName;
	private String FromUserName;
	private long CreateTime;
	private String MsgType;
	private String resString="";//默认为空
	public PBaseWeixinMessage(){
		
	}

	public PBaseWeixinMessage(String msgId, String toUserName,
			String fromUserName, long createTime, String msgType) {
		super();
		MsgId = msgId;
		ToUserName = toUserName;
		FromUserName = fromUserName;
		CreateTime = createTime;
		MsgType = msgType;
	}

	public String getResString() {
		return resString;
	}

	public void setResString(String resString) {
		this.resString = resString;
	}

	public String getMsgId() {
		return MsgId;
	}
	public void setMsgId(String msgId) {
		MsgId = msgId;
	}
	public String getToUserName() {
		return ToUserName;
	}
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}
	public String getFromUserName() {
		return FromUserName;
	}
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}
	public long getCreateTime() {
		return CreateTime;
	}
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}
	public String getMsgType() {
		return MsgType;
	}
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
