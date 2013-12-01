package cn.future.mail.action;

import org.apache.commons.mail.EmailException;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.mail.pojo.PMail;
import cn.future.mail.service.MailSendService;
import cn.future.common.pojo.PConfiguration;
import cn.future.common.service.ConfigService;
import cn.future.util.StringUtil;

public class UnfilterMailAction extends BaseAction{
	private static final long serialVersionUID = -544391950544293242L;
	private ConfigService configService;
	private MailSendService mailSendService;
	private String call;
	private String name;
	private String nick;
	//set - get
	private String message;
	public String test(){
		try {
			mailSendService.sendHtmlMail("这个是测试乱码问题的", "309103737@qq.com", "这是标题");
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public String callCustomer(){
		try {

			PConfiguration cfg=configService.findConfiguration("portal-customer-contact");
			String mailAddress=cfg.getValue();
			PMail mail=new PMail();
			mail.setContent("电话："+call+";姓名："+name+";称呼："+nick);
			mail.setTitle("客户联系方式登记");
			mail.setModule("Portal");
			mail.setId(StringUtil.getUuid());
			mail.setTargetAddress(mailAddress);
			mailSendService.sendMail(mail);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getMessage() {
		return message;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}

	public void setCall(String call) {
		this.call = call;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	
}
