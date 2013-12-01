package cn.future.pay.service.impl;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.pay.exception.PayBusinessIdUnuquieException;
import cn.future.pay.exception.PayCheckRemoteFailException;
import cn.future.pay.pojo.BaoReturn;
import cn.future.pay.pojo.PPayRequest;
import cn.future.pay.service.PayRequestValidateService;
import cn.future.pay.service.baopay.BaopayCheckService;
import cn.future.pay.util.PayPlatformCodeUtil;

public class PayRequestValidateServiceImpl implements PayRequestValidateService {
	private BaseDao baseDao;
	private BaopayCheckService baopayCheckService;
	
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	public void setBaopayCheckService(BaopayCheckService baopayCheckService) {
		this.baopayCheckService = baopayCheckService;
	}


	@Override
	public String validatePayRequestRemote(String id) throws NotFindException, PayCheckRemoteFailException, ParseException, ClientProtocolException, IOException, PayBusinessIdUnuquieException, SystemBusyException {
		String message = "";
		PPayRequest pojo = baseDao.findById(PPayRequest.class, id);
		int platCode = pojo.getPayPlatformCode();
		if(PayPlatformCodeUtil.ALIPAY == platCode){
			
		}else if(PayPlatformCodeUtil.TENPAY == platCode){
			
		}else if(PayPlatformCodeUtil.BAOPAY == platCode){
			BaoReturn br = baopayCheckService.checkResult(pojo.getId());
			baopayCheckService.updateResult(br);
			message = "成功刷新";
		}
		return message;
	}

}
