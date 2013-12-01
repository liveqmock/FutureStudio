package cn.future.social.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.future.customer.dto.DCustomer;
import cn.future.network.service.HttpService;
import cn.future.social.exception.SocialOpenIdAlreadyBindException;
import cn.future.social.exception.SocialQQSignException;
import cn.future.social.pojo.PSocialBind;
import cn.future.social.service.SocialBindManageService;
import cn.future.social.service.SocialBindQueryService;
import cn.future.social.service.SocialConfigService;
import cn.future.social.service.SocialQQService;
import cn.future.social.util.SocialUtil;
import cn.future.util.StringMapUtil;

public class SocialQQServiceImpl implements SocialQQService {
	private SocialConfigService socialConfigService;
	private SocialBindQueryService socialBindQueryService;
	private SocialBindManageService socialBindManageService;
	private HttpService httpService;
	private Gson gson = new Gson();
	private String AccessTokenUrl = "https://graph.qq.com/oauth2.0/token";
	private String openIdUrl = "https://graph.qq.com/oauth2.0/me";
	private String userInfoUrl = "https://graph.qq.com/user/get_user_info";
	
	private String redirect_url = "/fs/social/qqSign_in.action";
	private String appid;
	private String appkey;
	
	public void setSocialBindManageService(
			SocialBindManageService socialBindManageService) {
		this.socialBindManageService = socialBindManageService;
	}

	public void setSocialBindQueryService(
			SocialBindQueryService socialBindQueryService) {
		this.socialBindQueryService = socialBindQueryService;
	}

	public void setHttpService(HttpService httpService) {
		this.httpService = httpService;
	}

	public void setSocialConfigService(SocialConfigService socialConfigService) {
		this.socialConfigService = socialConfigService;
		appid = socialConfigService.getTenConnectId();
		appkey = socialConfigService.getTenConnectKey();
	}

	@Override
	public String findAccessTokenByAuthorizationCode(String code, String state) throws ClientProtocolException, IOException, URISyntaxException, SocialQQSignException {
		HashMap<String,String> params = new HashMap<String,String>();
		String redirectUrl = socialConfigService.getWebUrl()+redirect_url;
		params.put("grant_type", "authorization_code");
		params.put("client_id", appid);
		params.put("client_secret", appkey);
		params.put("code", code);
		params.put("state", state);
		params.put("redirect_uri", redirectUrl);
		String data = httpService.httpGetString(AccessTokenUrl, params);
		Map<String,String> dataMap = StringMapUtil.getMap(data, "&", "=");
		String token = dataMap.get("access_token");
		if(null==token){
			SocialQQSignException e = new SocialQQSignException("获取QQ登陆TOKEN信息失败"+data);
			throw e;
		}
		return token;
	}

	@Override
	public String findOpenIdByAccessToken(String accessToken) throws ClientProtocolException, IOException, URISyntaxException, SocialQQSignException {
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("access_token",accessToken);
		String data = httpService.httpGetString(openIdUrl, params);
		if(null!=data && data.contains("callback(")){
			data = data.replaceAll("callback\\(", "");
			data = data.replaceAll("\\);", "");
			TypeToken<HashMap<String,String>> tt = new TypeToken<HashMap<String,String>>(){};
			HashMap<String,String> map = gson.fromJson(data, tt.getType());
			return map.get("openid");
		}else{
			SocialQQSignException e = new SocialQQSignException("获取QQ开放ID失败"+data);
			throw e;
		}
	}
	@Override
	public String findQqNickName(String accessToken,String openId) throws ClientProtocolException, IOException, URISyntaxException{
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("access_token", accessToken);
		params.put("oauth_consumer_key",appid);
		params.put("openid", openId);
		params.put("format", "json");
		String data = httpService.httpGetString(userInfoUrl, params);
		TypeToken<HashMap<String,String>> tt = new TypeToken<HashMap<String,String>>(){};
		HashMap<String,String> map = gson.fromJson(data, tt.getType());
		String nickname = map.get("nickname");
		if(null==nickname){
			return "";
		}else{
			return nickname;
		}
		
	}

	@Override
	public PSocialBind addSocialBindByCode(DCustomer cus, String code, String state) throws SocialQQSignException, ClientProtocolException, IOException, URISyntaxException, SocialOpenIdAlreadyBindException {
		String token = this.findAccessTokenByAuthorizationCode(code, state);
		String openId = this.findOpenIdByAccessToken(token);
		String nickName = this.findQqNickName(token, openId);
		return this.addSocialBindByOpenId(cus, token, openId, nickName);
	}

	@Override
	public PSocialBind addSocialBindByOpenId(DCustomer cus, String accessToken,
			String openId, String nickName) throws SocialOpenIdAlreadyBindException {
		int count = socialBindQueryService.findCustomerQqBindUnique(openId);
		if(count==0){
			PSocialBind pojo = new PSocialBind(SocialUtil.BIND_TYPE_QQ, openId, accessToken, nickName, cus.getId(), PSocialBind.POSITION_CUSTOMER);
			socialBindManageService.addPSocialBind(pojo);
		}else{
			SocialOpenIdAlreadyBindException e = new SocialOpenIdAlreadyBindException("QQ号已经绑定了其他账户");
			throw e;
		}
		return null;
	}

}
