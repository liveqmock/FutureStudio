package cn.future.mail.service.impl;

import java.io.UnsupportedEncodingException;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;

import cn.future.common.service.impl.ConfigHelperImpl;
import cn.future.mail.pojo.PMail;
import cn.future.mail.service.MailExecuteService;

public class MailLocalExecuteService implements MailExecuteService{
	private String mailFrom;
	private String mailKey;
	private String mailSmtp;
	public MailLocalExecuteService(){
		mailFrom = ConfigHelperImpl.getProperty("mailFrom", "");
		mailKey = ConfigHelperImpl.getProperty("mailKey", "");
		mailSmtp = ConfigHelperImpl.getProperty("mailSmtp", "");
	}
	@Override
	public void sendMail(PMail mail) throws EmailException {
		try {
			HtmlEmail email = new HtmlEmail();
			email.setCharset("ISO-8859-1");
			String title = new String(mail.getTitle().getBytes("UTF-8"),"ISO-8859-1");
			email.setSubject(title);
			
			email.setHostName(mailSmtp);
			email.setSmtpPort(25);
			email.setAuthenticator(new DefaultAuthenticator(mailFrom, mailKey));
			email.setSSLOnConnect(false);
			email.setFrom(mailFrom);
			String msg = new String(mail.getContent().getBytes("UTF-8"),"ISO-8859-1");
			email.setHtmlMsg(msg);
			email.addTo(mail.getTargetAddress());
			email.send();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
