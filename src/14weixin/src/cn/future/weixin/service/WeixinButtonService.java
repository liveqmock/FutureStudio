package cn.future.weixin.service;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import cn.future.weixin.exception.WeixinButtonCreateException;
import cn.future.weixin.exception.WeixinButtonDeleteException;
import cn.future.weixin.exception.WeixinButtonLengthException;
import cn.future.weixin.exception.WeixinTokenFetchException;
import cn.future.weixin.pojo.PWeixinButton;

public abstract interface WeixinButtonService {
	/**
	 * 一级button最多3个
	 * @param buttons
	 * @throws WeixinButtonLengthException 
	 * @throws WeixinTokenFetchException 
	 * @throws UnsupportedEncodingException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws WeixinButtonCreateException 
	 */
	public void createMenu(ArrayList<PWeixinButton> buttons) throws WeixinButtonLengthException, WeixinTokenFetchException, UnsupportedEncodingException, ClientProtocolException, IOException, WeixinButtonCreateException;
	/**
	 * 查询当前菜单
	 * @return
	 * @throws WeixinTokenFetchException 
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public String findMenu() throws WeixinTokenFetchException, ClientProtocolException, IOException, URISyntaxException;
	
	/**
	 * 删除当前菜单
	 * @throws WeixinTokenFetchException 
	 * @throws URISyntaxException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws WeixinButtonDeleteException 
	 */
	public void deleteMenu() throws WeixinTokenFetchException, ClientProtocolException, IOException, URISyntaxException, WeixinButtonDeleteException;
}
