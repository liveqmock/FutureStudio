package cn.future.mail.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.mail.EmailException;
import org.slf4j.*;

import cn.future.mail.pojo.PMail;
import cn.future.mail.service.MailExecuteService;
import cn.future.mail.service.MailSendService;
import cn.future.security.pojo.PSecurityCode;
import cn.future.util.StringUtil;
import cn.future.util.TimeUtil;

public class MailSendServiceImpl implements MailSendService {

	private Logger logger = LoggerFactory.getLogger(MailSendServiceImpl.class);
	private MailExecuteService mailExecuteService;

	public void setMailExecuteService(MailExecuteService mailExecuteService) {
		this.mailExecuteService = mailExecuteService;
	}

	@Override
	public void sendHtmlMail(String htmlFile,String toUser, String title, Map<String,String> contents) throws IOException, EmailException{
		logger.debug("send register mail:"+toUser);
		InputStream is = this.getClass().getResourceAsStream("/cn/future/mail/"+htmlFile);
		String content = IOUtils.toString(is);
		content = StringUtil.UrlDecoder(content);
		if(null!=contents){
			contents.put("CurrentTime", TimeUtil.date2ShowYMDHMS(new Date()));
			for(String key:contents.keySet()){
				content = content.replace(key, contents.get(key));
			}
		}
		this.sendHtmlMail(content, toUser, title);
	}
	@Override
	public void sendHtmlMail(String content,String toUser, String title) throws EmailException{
		content = "<!--HTML-->"+content;
		PMail mail = new  PMail(title, content, toUser);
		this.sendMail(mail);
	}
	@Override
	public void sendMail(PMail mail) throws EmailException {
		mailExecuteService.sendMail(mail);
	}
	@Override
	public void sendSecurityMail(PSecurityCode sc, String reason, String email) throws IOException, EmailException {
		Map<String,String> contents = new HashMap<String,String>();
		contents.put("SendReason", reason);
		contents.put("SecurityCode", sc.getCode());
		this.sendHtmlMail("securityCode.html", email,"安全验证", contents);
	}
	@Override
	public void sendRegisterMail(String toUser, String url) throws IOException, EmailException {
		this.sendRegisterMail(toUser, null, url);
	}

	@Override
	public void sendRegisterMail(String toUser, String userName, String url)
			throws IOException, EmailException {
		Map<String,String> contents = new HashMap<String,String>();
		String UserName;
		if(null!=userName){
			UserName = userName;
		}else{
			UserName = toUser;
		}
		contents.put("UrlPlaceHolder", url);
		contents.put("UserName", UserName);
		this.sendHtmlMail("registerActive.html", toUser,"激活邮件", contents);
	}
}
