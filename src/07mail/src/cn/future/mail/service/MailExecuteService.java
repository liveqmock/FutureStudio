package cn.future.mail.service;

import org.apache.commons.mail.EmailException;

import cn.future.mail.pojo.PMail;

public abstract interface MailExecuteService {

	/**
	 * 发送邮件
	 * @param title
	 * @param content
	 * @param mailAddress
	 * @throws EmailException 
	 */
	public void sendMail(PMail mail) throws EmailException;
}
