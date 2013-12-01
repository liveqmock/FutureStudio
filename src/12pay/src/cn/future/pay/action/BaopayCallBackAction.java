package cn.future.pay.action;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.*;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.pay.exception.PayBusinessIdUnuquieException;
import cn.future.pay.exception.PayCheckRemoteFailException;
import cn.future.pay.pojo.BaoReturn;
import cn.future.pay.service.baopay.BaopayCheckService;

public class BaopayCallBackAction extends BaseAction {

	private static final long serialVersionUID = -3867270703359104814L;
	//set
	private BaopayCheckService baopayCheckService;
	private final Logger logger = LoggerFactory.getLogger(BaopayCallBackAction.class);
	private String MerchantID;
	private String TransID;
	private String Result;
	private String resultDesc;
	private String factMoney; //分
	private String additionalInfo;
	private String SuccTime;
	private String Md5Sign;
	//get
	private String jspPage;
	/**
	 * 宝付 服务器校验
	 * @return
	 */
	public String check(){
		logger.debug("check", 
				"MerchantID:"+MerchantID+
				",TransID:"+TransID+
				",Result:"+Result+
				",resultDesc:"+resultDesc+
				",factMoney:"+factMoney+
				",additionalInfo:"+additionalInfo+
				",SuccTime:"+SuccTime+
				",Md5Sign:"+Md5Sign);
		
		BaoReturn br = new BaoReturn(
				MerchantID,
				TransID,
				Result,
				resultDesc,
				factMoney,
				additionalInfo,
				SuccTime,
				Md5Sign
				);
		
		try {
			BaoReturn r = baopayCheckService.checkResult(br);
			baopayCheckService.updateResult(r);
		} catch (PayBusinessIdUnuquieException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemBusyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PayCheckRemoteFailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		jspPage = "notify_success.jsp";
		return SUCCESSJSP;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setBaopayCheckService(BaopayCheckService baopayCheckService) {
		this.baopayCheckService = baopayCheckService;
	}

	public void setMerchantID(String merchantID) {
		MerchantID = merchantID;
	}

	public void setTransID(String transID) {
		TransID = transID;
	}

	public void setResult(String result) {
		Result = result;
	}

	public void setResultDesc(String resultDesc) {
		this.resultDesc = resultDesc;
	}

	public void setFactMoney(String factMoney) {
		this.factMoney = factMoney;
	}

	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	public void setSuccTime(String succTime) {
		SuccTime = succTime;
	}

	public void setMd5Sign(String md5Sign) {
		Md5Sign = md5Sign;
	}

	public String getJspPage() {
		return jspPage;
	}
	
}
