package cn.future.security.action;

import java.io.IOException;

import org.apache.commons.mail.EmailException;

import cn.future.common.action.BaseAction;
import cn.future.customer.dto.DCustomer;
import cn.future.mail.service.MailSendService;
import cn.future.security.pojo.PSecurityCode;
import cn.future.security.service.SecurityCodeService;
import cn.future.util.SessionUtil;

public class SecurityCodeQueryAction extends BaseAction{

	private static final long serialVersionUID = 6842949489671340435L;
	//set
	private String businessId;
	private String businessTag;
	private String reason;
	private String sendMethod; // mail weixin
	private SecurityCodeService securityCodeService;
	private MailSendService mailSendService;
	private String idType="s"; //主ID类型 s 从session中取，i 自定义使用id，
	private String id=""; //主ID
	//get
	private String message;
	
	/**
	 * idType | id | businessId | businessTag | reason
	 * 
	 * @return
	 */
	public String findCode(){
		try {
			response.setStatus(500);
			String userId = "";
			String email = "";
			if("s".equalsIgnoreCase(idType)){
				DCustomer cus = SessionUtil.getSessionCustomer(request);
				email = cus.getEmail();
				userId = cus.getId();
			}else{
				email = id;
				userId = id;
			}
			
			PSecurityCode sc = securityCodeService.findShortTimeSecurityCode(userId, businessId, businessTag);
			//if(null!=sendMethod){}; 暂时不支持这个方法，
			mailSendService.sendSecurityMail(sc, reason, email);
			message = sc.getId();
			response.setStatus(200);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setSecurityCodeService(SecurityCodeService securityCodeService) {
		this.securityCodeService = securityCodeService;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public void setBusinessTag(String businessTag) {
		this.businessTag = businessTag;
	}
	public void setSendMethod(String sendMethod) {
		this.sendMethod = sendMethod;
	}
	public String getMessage() {
		return message;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public void setMailSendService(MailSendService mailSendService) {
		this.mailSendService = mailSendService;
	}
	public void setIdType(String idType) {
		this.idType = idType;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
}
