package cn.future.weixin.action;

import cn.future.common.action.BaseAction;
import cn.future.weixin.exception.WeixinTokenFetchException;
import cn.future.weixin.service.WeixinAccessTokenService;

public class QueryAction extends BaseAction{

	private static final long serialVersionUID = 99752416222725729L;
	private WeixinAccessTokenService weixinAccessTokenService;
	private String message;
	/**
	 * 查询微信连接Token
	 * @return
	 */
	public String token(){
		try {
			message = weixinAccessTokenService.getWeixinAccessToken();
		} catch (WeixinTokenFetchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMessage() {
		return message;
	}
	public void setWeixinAccessTokenService(
			WeixinAccessTokenService weixinAccessTokenService) {
		this.weixinAccessTokenService = weixinAccessTokenService;
	}
	
	
}
