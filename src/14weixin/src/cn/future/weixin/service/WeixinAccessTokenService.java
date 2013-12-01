package cn.future.weixin.service;

import cn.future.weixin.exception.WeixinTokenFetchException;

public abstract interface WeixinAccessTokenService {
	/**
	 * 获取连接密钥，
	 * 1.先判断WeiXinUtil里面是否已经存在，如果存在直接返回
	 * 2.如果不存在，通过ID 和 secret去微信服务取
	 * @return
	 * @throws WeixinTokenFetchException 
	 */
	public String getWeixinAccessToken() throws WeixinTokenFetchException;
	/**
	 * 重新去微信服务器获取token
	 * 并设置到WeiXinUtil里面
	 * @return
	 */
	public String refreshWeixinAccessToken();
}
