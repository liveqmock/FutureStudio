package cn.future.social.pojo;//Date: 13-6-18

import java.io.Serializable;
import java.util.Date;

import cn.future.util.StringUtil;

/**
 * 社交绑定关系
 */
public class PSocialBind implements Serializable{
	private static final long serialVersionUID = 3665308052057668176L;
	public static final String POSITION_CUSTOMER = "c";
	public static final String POSITION_USER = "u";
	
	public PSocialBind(){
		
	}
	public PSocialBind(String bindType, String openId, String accessToken, String nickName, String userId, String bindPosition){
		this.id = StringUtil.getUuid();
		this.bindDate = new Date();
		this.bindType = bindType;
		this.bindTypeId = openId;
		this.accessToken = accessToken;
		this.bindNickName = nickName;
		this.userId = userId;
		this.bindPosition = bindPosition;
	}
	/**
	 * 主键KEY 32位uuid
	 */
	private String id;
	/**
	 * 绑定类型，详见SocialUtil
	 */
    private String bindType;
    /**
     * 外部用户ID，一般是一个Open id
     */
    private String bindTypeId;
    /**
     * 绑定账户的名称
     */
    private String bindNickName;
    /**
     * 数据连接授权
     */
    private String accessToken;
	/**
     * 系统内用户ID，根据bindPosition确定类型
     */
    private String userId; 
    /**
     * 绑定账户类型，
     * c 对应 customer， 
     * u 对应 user
     */
    private String bindPosition;
    /**
     * 绑定日期
     */
    private Date bindDate;

	public String getBindPosition() {
		return bindPosition;
	}
	public void setBindPosition(String bindPosition) {
		this.bindPosition = bindPosition;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBindType() {
		return bindType;
	}
	public void setBindType(String bindType) {
		this.bindType = bindType;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBindTypeId() {
		return bindTypeId;
	}
	public void setBindTypeId(String bindTypeId) {
		this.bindTypeId = bindTypeId;
	}
	public Date getBindDate() {
		return bindDate;
	}
	public void setBindDate(Date bindDate) {
		this.bindDate = bindDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getBindNickName() {
		return bindNickName;
	}
	public void setBindNickName(String bindNickName) {
		this.bindNickName = bindNickName;
	}
    
}
