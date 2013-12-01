package cn.future.weixin.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.future.weixin.exception.WeixinTokenFetchException;
import cn.future.weixin.service.WeixinAccessTokenService;
import cn.future.weixin.util.WeixinUtil;
import cn.future.common.service.impl.ConfigHelperImpl;
import cn.future.network.service.HttpService;

public class WeixinAccessTokenServiceImpl implements WeixinAccessTokenService {
	public static String token_url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";
	public static String appid = "";
	public static String appsecret = "";
	
	private HttpService httpService;
	private Gson gson = new Gson();
	
	public WeixinAccessTokenServiceImpl(){
		WeixinAccessTokenServiceImpl.appid = ConfigHelperImpl.getProperty("weixin_ak", "");
		WeixinAccessTokenServiceImpl.appsecret = ConfigHelperImpl.getProperty("weixin_sk", "");
		WeixinAccessTokenServiceImpl.token_url = WeixinAccessTokenServiceImpl.token_url 
				+ "&appid="+WeixinAccessTokenServiceImpl.appid
				+ "&secret="+WeixinAccessTokenServiceImpl.appsecret;
	}
	public void setHttpService(HttpService httpService) {
		this.httpService = httpService;
	}

	@Override
	public String getWeixinAccessToken() throws WeixinTokenFetchException {
		Date current = new Date();
		if(WeixinUtil.access_token == null || current.getTime() > WeixinUtil.token_expires.getTime()){
			this.fetchWeixinAccessToken();
		}
		return WeixinUtil.access_token;
	}

	@Override
	public String refreshWeixinAccessToken() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void fetchWeixinAccessToken() throws WeixinTokenFetchException {
		try {
			String res = httpService.httpGetString(token_url, null);
			HashMap<String,String> t = new HashMap<String,String> ();
			t = gson.fromJson(res, new TypeToken<HashMap<String,String>>(){}.getType());
			if(t.get("errcode")!=null){
				WeixinTokenFetchException e = new WeixinTokenFetchException(res);
				throw e;
			}
			WeixinUtil.access_token = t.get("access_token");
			int expires = Integer.parseInt(t.get("expires_in"));
			Date current = new Date();
			long expiresLongTime = current.getTime()+expires-1200;//提前20分钟刷新
			current.setTime(expiresLongTime);
			WeixinUtil.token_expires = current;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
