package cn.future.weixin.service.impl;

import java.util.Date;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import cn.future.common.exception.NotFindException;
import cn.future.customer.pojo.PCustomer;
import cn.future.customer.service.CustomerQueryService;
import cn.future.social.pojo.PSocialBind;
import cn.future.social.service.SocialBindQueryService;
import cn.future.util.StringUtil;
import cn.future.util.parser.XmlParser;
import cn.future.weixin.message.response.ResTextMessage;
import cn.future.weixin.parser.MessageParser;
import cn.future.weixin.pojo.PWeixinMessageText;
import cn.future.weixin.service.WeixinMessageFactory;
import cn.future.weixin.service.WeixinResponseTextService;
import cn.future.weixin.util.MessageUtil;

public class WeixinMessageFactoryImpl implements WeixinMessageFactory{
	//set
	private SocialBindQueryService socialBindQueryService;
	private WeixinResponseTextService weixinResponseTextService;
	private CustomerQueryService customerQueryService;

	@Override
	public String createCustomerResponseMessage(String postString) throws DocumentException {
		String resString;
		
		Map<String,String> requestMap = XmlParser.parseXml(postString);

		// 发送方帐号（open_id）  
        String fromUserName = requestMap.get("FromUserName");  
        // 公众帐号  
        String toUserName = requestMap.get("ToUserName");  
        // 消息类型  
        String msgType = requestMap.get("MsgType");  

        // 回复文本消息  
        ResTextMessage textMessage = new ResTextMessage();  
        textMessage.setToUserName(fromUserName);  
        textMessage.setFromUserName(toUserName);  
        textMessage.setCreateTime(new Date().getTime());  
        textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);  
        textMessage.setFuncFlag(0); 
        
        textMessage.setContent("This is Tony's 测试");  
        resString = MessageParser.textMessageToXml(textMessage);  
        
		return resString;
	}

	public void setSocialBindQueryService(
			SocialBindQueryService socialBindQueryService) {
		this.socialBindQueryService = socialBindQueryService;
	}

	public void setWeixinResponseTextService(
			WeixinResponseTextService weixinResponseTextService) {
		this.weixinResponseTextService = weixinResponseTextService;
	}
	public void setCustomerQueryService(CustomerQueryService customerQueryService) {
		this.customerQueryService = customerQueryService;
	}

}
