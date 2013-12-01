package cn.future.mail.service;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.mail.EmailException;

import cn.future.mail.pojo.PMail;
import cn.future.security.pojo.PSecurityCode;

public abstract interface MailSendService {
	/**
	 * 发送邮件
	 * @param title
	 * @param content
	 * @param mailAddress
	 * @throws EmailException 
	 */
	public void sendMail(PMail mail) throws EmailException;
	/**
	 * 发送注册邮件
	 * @param toUser 发送到用户邮箱 , 使用邮件地址作为用户名称
	 * @param url 需要放在邮箱的URL地址，
	 * 
	 * 特别：会调用cn/future/mail/registerActive.html文件
	 * 并将文件中{UrlPlaceHolder}插入这个URL代码
	 * @throws IOException 
	 * @throws EmailException 
	 */
	public void sendRegisterMail(String toUser, String url) throws IOException, EmailException;
	/**
	 * 发送注册邮件
	 * @param toUser 发送到用户邮箱
	 * @param url 需要放在邮箱的URL地址，
	 * 
	 * 特别：会调用cn/future/mail/registerActive.html文件
	 * 并将文件中{UrlPlaceHolder}插入这个URL代码
	 * @throws IOException 
	 * @throws EmailException 
	 */
	public void sendRegisterMail(String toUser, String userName, String url) throws IOException, EmailException;
	/**
	 * 发送安全码到邮箱
	 * @param sc 安全码
	 * @param reason 发送原因
	 * @param email 客户邮箱
	 * @throws IOException
	 * @throws EmailException
	 */
	public void sendSecurityMail(PSecurityCode sc, String reason, String email) throws IOException, EmailException ;
	/**
	 * 发送内容替换邮件
	 * @param htmlFile 文件名，具体到：cn.future.mail 下的html文件
	 * @param toUser 
	 * @param title
	 * @param contents 需要执行替换的键值对
	 * @throws IOException
	 * @throws EmailException
	 */
	public void sendHtmlMail(String htmlFile,String toUser, String title, Map<String,String> contents) throws IOException, EmailException;
	/**
	 * 发送Html邮件，会自动再content前面添加<!--html-->
	 * @param content
	 * @param toUser
	 * @param title
	 * @throws EmailException
	 */
	public void sendHtmlMail(String content,String toUser, String title) throws EmailException;
}
