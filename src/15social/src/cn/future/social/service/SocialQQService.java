package cn.future.social.service;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;

import cn.future.customer.dto.DCustomer;
import cn.future.social.exception.SocialOpenIdAlreadyBindException;
import cn.future.social.exception.SocialQQSignException;
import cn.future.social.pojo.PSocialBind;

public abstract interface SocialQQService {
	/**
	 * 通过CODE获取连接ID
	 * @param code 返回 code
	 * @param state 防攻击标识符
	 * @return
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws SocialQQSignException 
	 */
	public String findAccessTokenByAuthorizationCode(String code, String state) throws ClientProtocolException, IOException, URISyntaxException, SocialQQSignException;
	/**
	 * 通过连接TOKEN获取OPEN ID
	 * @param accessToken
	 * @return
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws SocialQQSignException 
	 */
	public String findOpenIdByAccessToken(String accessToken) throws ClientProtocolException, IOException, URISyntaxException, SocialQQSignException;
	/**
	 * 查询QQ用户的Qzone名称
	 * @param accessToken
	 * @param openId
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws URISyntaxException
	 */
	public String findQqNickName(String accessToken,String openId) throws ClientProtocolException, IOException, URISyntaxException;
	/**
	 * 通过code绑定用户
	 * @param cus
	 * @param code
	 * @param state
	 * @return
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws SocialQQSignException 
	 * @throws SocialOpenIdAlreadyBindException 
	 */
	public PSocialBind addSocialBindByCode(DCustomer cus,String code, String state) throws SocialQQSignException, ClientProtocolException, IOException, URISyntaxException, SocialOpenIdAlreadyBindException;
	/**
	 * 通过open id 绑定用户
	 * @param cus
	 * @param accessToken
	 * @param openId
	 * @return
	 * @throws SocialOpenIdAlreadyBindException 
	 */
	public PSocialBind addSocialBindByOpenId(DCustomer cus,String accessToken, String openId, String nickName) throws SocialOpenIdAlreadyBindException;
}
