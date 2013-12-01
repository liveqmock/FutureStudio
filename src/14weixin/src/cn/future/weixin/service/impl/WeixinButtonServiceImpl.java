package cn.future.weixin.service.impl;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.StringEntity;
import org.apache.http.protocol.HTTP;

import com.google.gson.Gson;

import cn.future.network.service.HttpService;
import cn.future.weixin.exception.WeixinButtonCreateException;
import cn.future.weixin.exception.WeixinButtonDeleteException;
import cn.future.weixin.exception.WeixinButtonLengthException;
import cn.future.weixin.exception.WeixinTokenFetchException;
import cn.future.weixin.pojo.PWeixinButton;
import cn.future.weixin.pojo.PButtonResponse;
import cn.future.weixin.service.WeixinAccessTokenService;
import cn.future.weixin.service.WeixinButtonService;
import cn.future.weixin.util.WeixinUtil;

public class WeixinButtonServiceImpl implements WeixinButtonService{
	private WeixinAccessTokenService weixinAccessTokenService;
	private HttpService httpService;
	private Gson gson = new Gson();
	
	public void setHttpService(HttpService httpService) {
		this.httpService = httpService;
	}


	public void setWeixinAccessTokenService(
			WeixinAccessTokenService weixinAccessTokenService) {
		this.weixinAccessTokenService = weixinAccessTokenService;
	}


	@Override
	public void createMenu(ArrayList<PWeixinButton> buttons) throws WeixinButtonLengthException, WeixinTokenFetchException, ClientProtocolException, IOException, WeixinButtonCreateException {
		
		if(buttons == null || buttons.size() < 1){
			WeixinButtonLengthException e = new WeixinButtonLengthException("按钮不能少于1");
			throw e;
		}
		String token = weixinAccessTokenService.getWeixinAccessToken();
		
		HashMap<String,ArrayList<PWeixinButton>> dataMap = new HashMap<String,ArrayList<PWeixinButton>>();
		dataMap.put("button", buttons);
		String data = gson.toJson(dataMap);
		StringEntity dataEntity = new StringEntity(data, HTTP.UTF_8);
		
		String res = httpService.httpPostString(WeixinUtil.MENU_CREATE_URL+token, dataEntity);
		PButtonResponse resObject= gson.fromJson(res, PButtonResponse.class);
		if(resObject.getErrcode() != 0){
			//创建失败;
			WeixinButtonCreateException e = new WeixinButtonCreateException(res);
			throw e;
		}
	}


	@Override
	public String findMenu() throws WeixinTokenFetchException, ClientProtocolException, IOException, URISyntaxException {
		String token = weixinAccessTokenService.getWeixinAccessToken();
		return httpService.httpGetString(WeixinUtil.MENU_GET_URL+token, null);
	}


	@Override
	public void deleteMenu() throws WeixinTokenFetchException, ClientProtocolException, IOException, URISyntaxException, WeixinButtonDeleteException {
		String token = weixinAccessTokenService.getWeixinAccessToken();
		String resString = httpService.httpGetString(WeixinUtil.MENU_DELETE_URL+token, null);
		PButtonResponse resObj = gson.fromJson(resString, PButtonResponse.class);
		if(resObj.getErrcode()!=0){
			//删除出错
			WeixinButtonDeleteException e = new WeixinButtonDeleteException(resString);
			throw e;
		}
	}

}
