package cn.future.weixin.service;

import cn.future.weixin.pojo.PWeixinMessageText;

public abstract interface WeixinMessageTextService {
	public String createWeixinRequest(PWeixinMessageText msg);
}
