package cn.future.social.util;

import cn.future.common.service.impl.ConfigHelperImpl;
import cn.future.util.StringUtil;

public class SocialUtil {

	public static final String SOCIAL_SESSION = "social_session";
	/**
	 * 绑定类型为QQ
	 */
	public static final String BIND_TYPE_QQ = "qq";
	/**
	 * 绑定类型为微信
	 */
	public static final String BIND_TYPE_WEIXIN = "weixin";
	
	/**
	 * 开放登陆地址，
	 * 需要传入2个参数：
	 * @param openId  当前用户的主ID
	 * @param bindType  绑定类型，参考PSocialBindService
	 * @param position 当前身份，c 客户，u 用户
	 */
	public static String SOCIAL_SIGN_URL = "/fs/social/sign_in.action";

	/**
	 * 获取，绑定账户登陆URL地址, openId会做UriEncode
	 * @param openId 第三方主ID，对应bindTypeId
	 * @param bindType 绑定类型
	 * @param position 用户类型
	 * @return
	 */
	private static String getBindSignUrl(String openId, String bindType, String position){
		String url = ConfigHelperImpl.getProperty("webUrl", "");
		String codeOpenId = StringUtil.UrlEncoder(openId);
		url = url + SOCIAL_SIGN_URL + "?openId="+codeOpenId+"&bindType="+bindType+"&position="+position;
		return url;
	}
	/**
	 * 获取，绑定客户微信账户登陆URL地址, openId会做UriEncode
	 * @param openId 第三方主ID，对应bindTypeId
	 * @return
	 */
	public static String getCustomerWeixinBindSignUrl(String openId){
		return getBindSignUrl(openId, SocialUtil.BIND_TYPE_WEIXIN, "c");
	}
}
