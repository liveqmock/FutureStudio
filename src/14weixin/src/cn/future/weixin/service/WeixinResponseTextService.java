package cn.future.weixin.service;

import cn.future.common.exception.NotFindException;
import cn.future.customer.dto.DCustomer;

public abstract interface WeixinResponseTextService {
	/**
	 * 创建登陆信息的回复，一个按钮图标，一个地址
	 * @param toUser
	 * @return
	 */
	public String createSignResponse(String toUser, String fromUser);
	/**
	 * 创建微信文本消息
	 * @param text 消息内容，不超过2048字节
	 * @param toUser 接收用户的open id
	 * @return 发送XML内容
	 */
	public String createTextResponse(String text,String toUser, String fromUser);
	/**
	 * 创建图片，连接消息
	 * @param toUser
	 * @param title
	 * @param desc
	 * @param picUrl
	 * @param url
	 * @return
	 */
	public String createPicResponse(String toUser, String fromUser, String title, String desc, String picUrl, String url);
	/**
	 * 创建微信文本消息
	 * @param text 消息内容，不超过2048字节
	 * @param cus 用户
	 * @return 发送XML内容
	 * @throws NotFindException 
	 */
	public String createTextResponse(String text,DCustomer cus) throws NotFindException;
}
