package cn.future.weixin.util;

import java.util.Date;

public class WeixinUtil {
	
	public static String access_token = null; //请勿直接使用，应当通过服务获取
	public static Date token_expires = null;
	
	public static String MENU_CREATE_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=";//需要ACT
	public static String MENU_GET_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token="; //需要ACT
	public static String MENU_DELETE_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="; //需要ACT
	
	public static String MESSAGE_TOKEN = "futurefocus";//这个是开发自定义的TOKEN，用于加密
	/**
	 * 开发者微信号
	 * public static String DEV_WEIXIN_ID = "service@credit-dgh.com";
	 */
	

}
