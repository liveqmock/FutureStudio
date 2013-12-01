package cn.future.mail.service.impl;

import com.baidu.bae.api.bcms.BaeBcms;
import com.baidu.bae.api.bcms.model.concrete.MailRequest;

import cn.future.common.service.impl.ConfigHelperImpl;
import cn.future.mail.pojo.PMail;
import cn.future.mail.service.MailExecuteService;
import cn.future.util.bae.BcmsHelper;

public class MailCloudExecuteService implements MailExecuteService{

	private BaeBcms bcms;
	private String bcmsMailQueue;
	private String mailFrom;
	
	public MailCloudExecuteService(){
		bcms = BcmsHelper.getBaeBcms();
		bcmsMailQueue = BcmsHelper.bcmsMailQueue;
		mailFrom = ConfigHelperImpl.getProperty("mailFrom", "service@credit-dgh.com");
	}
	
	@Override
	public void sendMail(PMail mail) {
		// TODO Auto-generated method stub
		//发送邮件
		MailRequest mailRequest = new MailRequest();
		mailRequest.setQueueName(bcmsMailQueue);
		//String companyName=ConfigHelperImpl.getProperty("companyName", "futurefocus");
		mailRequest.setSubject(mail.getTitle());//+" - "+companyName+" - "+mail.getModule()
		mailRequest.setFrom(mailFrom);
		mailRequest.setMessage(mail.getContent());
		mailRequest.addMailAddress(mail.getTargetAddress());
		bcms.mail(mailRequest );
	}

}
