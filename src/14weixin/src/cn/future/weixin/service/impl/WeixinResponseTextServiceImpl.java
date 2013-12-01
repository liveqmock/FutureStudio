package cn.future.weixin.service.impl;

import java.util.Date;

import cn.future.common.exception.NotFindException;
import cn.future.common.service.impl.ConfigHelperImpl;
import cn.future.customer.dto.DCustomer;
import cn.future.social.pojo.PSocialBind;
import cn.future.social.service.SocialBindQueryService;
import cn.future.social.util.SocialUtil;
import cn.future.weixin.service.WeixinResponseTextService;
import cn.future.weixin.util.WeixinUtil;

public class WeixinResponseTextServiceImpl implements WeixinResponseTextService{
	private SocialBindQueryService socialBindQueryService;
	
	public void setSocialBindQueryService(
			SocialBindQueryService socialBindQueryService) {
		this.socialBindQueryService = socialBindQueryService;
	}

	String textTpl = "<xml>"+  
            "<ToUserName><![CDATA[%1$s]]></ToUserName>"+  
            "<FromUserName><![CDATA[%2$s]]></FromUserName>"+  
            "<CreateTime>%3$s</CreateTime>"+  
            "<MsgType><![CDATA[text]]></MsgType>"+  
            "<Content><![CDATA[%4$s]]></Content>"+
            "<FuncFlag>0</FuncFlag>"+
            "</xml>";   
	String picTpl = "<xml>"+
			"<ToUserName><![CDATA[%1$s]]></ToUserName>"+
			 "<FromUserName><![CDATA[%2$s]]></FromUserName>"+
			 "<CreateTime>%3$s</CreateTime>"+
			 "<MsgType><![CDATA[pic]]></MsgType>"+
			 "<ArticleCount>1</ArticleCount>"+
			 "<Articles>"+
			 "<item>"+
			 "<Title><![CDATA[%4$s]]></Title> "+
			 "<Description><![CDATA[%5$s]]></Description>"+
			 "<PicUrl><![CDATA[%6$s]]></PicUrl>"+
			 "<Url><![CDATA[%7$s]]></Url>"+
			 "</item>"+
			 "</Articles>"+
			 "</xml>";
	@Override
	public String createTextResponse(String text, String toUser, String fromUser) {
		String createTime = new Date().getTime()+"";
		String res = String.format(textTpl, toUser, fromUser, createTime, text);
		return res;
	}
	@Override
	public String createPicResponse(String toUser, String fromUser, String title, String desc, String picUrl, String url) {
		String createTime = new Date().getTime()+"";
		String res = String.format(picTpl, toUser, fromUser, createTime, title, desc, picUrl, url);
		return res;
	}

	@Override
	public String createSignResponse(String toUser, String fromUser) {
		String baseUrl = ConfigHelperImpl.getProperty("webUrl", "");
		String webUrl = baseUrl + 
				"/fs/social/sign_in?openId="+
				toUser+
				"&position="+PSocialBind.POSITION_CUSTOMER+
				"&bindType="+SocialUtil.BIND_TYPE_WEIXIN;
		String picUrl = baseUrl + "/img/sign/sign_word.png";
		String text = "亲，您尚未绑定账户，先绑定账户吧!";
		return this.createPicResponse(toUser,fromUser, "请登陆", text,picUrl, webUrl);
	}

	@Override
	public String createTextResponse(String textz, DCustomer cus) throws NotFindException {
		PSocialBind pb = socialBindQueryService.findWeixinPSocialBindByUserId(cus.getId());
		//TODO
		return "";//this.createTextResponse(text, pb.getBindTypeId());
	}

}
