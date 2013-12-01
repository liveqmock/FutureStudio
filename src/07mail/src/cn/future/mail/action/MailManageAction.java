package cn.future.mail.action;

import org.apache.commons.mail.EmailException;

import cn.future.common.action.BaseAction;
import cn.future.mail.pojo.PMail;
import cn.future.mail.service.MailSendService;
import cn.future.util.StringUtil;

public class MailManageAction extends BaseAction{
	private static final long serialVersionUID = 3749795199794899065L;
	private MailSendService mailSendService;
	//set get
	public String sendMail(){
		PMail mail=new PMail();
		mail.setContent("content");
		mail.setTitle("title");
		mail.setModule("module");
		mail.setId(StringUtil.getUuid());
		mail.setTargetAddress("309103737@qq.com");
		try {
			mailSendService.sendMail(mail);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}
	
}
