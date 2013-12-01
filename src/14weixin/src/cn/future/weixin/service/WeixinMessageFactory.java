package cn.future.weixin.service;

import org.dom4j.DocumentException;

public abstract interface WeixinMessageFactory {
	/**
	 * 微信消息处理中心
	 * @param postString
	 * @return
	 * @throws DocumentException
	 */
	public String createCustomerResponseMessage(String postString) throws DocumentException;
}
