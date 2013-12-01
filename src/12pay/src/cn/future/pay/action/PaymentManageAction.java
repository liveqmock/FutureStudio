package cn.future.pay.action;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.pay.exception.PayBusinessIdUnuquieException;
import cn.future.pay.exception.PayCheckRemoteFailException;
import cn.future.pay.service.PayRequestValidateService;

public class PaymentManageAction extends BaseAction {

	private static final long serialVersionUID = 3889044493335228830L;
	//set
	private PayRequestValidateService payRequestValidateService;
	private String id;
	//get
	private String message;
	/**
	 * 重新远端检查数据
	 * @return
	 */
	public String checkRemote(){
		try {
			this.beforeResponse();
			message = payRequestValidateService.validatePayRequestRemote(id);
			this.successResponse();
		} catch (PayCheckRemoteFailException e) {
			e.printStackTrace();
			message = e.getMessage();
		} catch (PayBusinessIdUnuquieException e) {

			e.printStackTrace();
			message = e.getMessage();
		} catch (NotFindException e) {

			e.printStackTrace();
			message = e.getMessage();
		} catch (ParseException e) {

			e.printStackTrace();
			message = e.getMessage();
		} catch (ClientProtocolException e) {

			e.printStackTrace();
			message = e.getMessage();
		} catch (SystemBusyException e) {

			e.printStackTrace();
			message = e.getMessage();
		} catch (IOException e) {

			e.printStackTrace();
			message = e.getMessage();
		}
		return SUCCESS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setPayRequestValidateService(
			PayRequestValidateService payRequestValidateService) {
		this.payRequestValidateService = payRequestValidateService;
	}
	public String getMessage() {
		return message;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
	
}
