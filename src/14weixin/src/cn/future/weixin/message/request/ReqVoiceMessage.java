package cn.future.weixin.message.request;

public class ReqVoiceMessage extends ReqBaseMessage {
	// 媒体ID  
    private String MediaId;  
    // 语音格式  
    private String Format;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}  
    
    
}
