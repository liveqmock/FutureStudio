package cn.future.pay.service.tenpay.impl;

import java.io.UnsupportedEncodingException;

import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.customer.dto.DCustomer;
import cn.future.pay.exception.PayBusinessIdUnuquieException;
import cn.future.pay.pojo.PPayRequest;
import cn.future.pay.pojo.TenpayRequest;
import cn.future.pay.pojo.TenpayReturn;
import cn.future.pay.service.PayRequestManageService;
import cn.future.pay.service.tenpay.TenpayPayService;
import cn.future.pay.service.tenpay.TenpayRequestFactory;
import cn.future.pay.service.tenpay.TenpayRequestService;
import cn.future.pay.util.PayPlatformCodeUtil;

public class TenpayPayServiceImpl implements TenpayPayService{
	private TenpayRequestFactory tenpayRequestFactory;
	private TenpayRequestService tenpayRequestService;
	private PayRequestManageService payRequestManageService;
	
	public void setPayRequestManageService(
			PayRequestManageService payRequestManageService) {
		this.payRequestManageService = payRequestManageService;
	}

	public void setTenpayRequestFactory(TenpayRequestFactory tenpayRequestFactory) {
		this.tenpayRequestFactory = tenpayRequestFactory;
	}

	public void setTenpayRequestService(TenpayRequestService tenpayRequestService) {
		this.tenpayRequestService = tenpayRequestService;
	}

	@Override
	public String initRedirectUrl(DCustomer customer, long productNumber, long transNumber,
			String businessId, String title, String content, String userIp,
			String trans_type, String transport_desc, String returnUrl) throws UnsupportedEncodingException {
		
		PPayRequest pay = new PPayRequest(customer.getId(),productNumber, transNumber, businessId, title, content, userIp, trans_type, transport_desc, null, PayPlatformCodeUtil.TENPAY);
		tenpayRequestFactory.initPayInfo(pay);
		TenpayRequest tr = tenpayRequestFactory.getTenpayRequest();
		tr.setReturn_url(returnUrl);
		String url = tenpayRequestService.getRequestURL(tr);
		payRequestManageService.addPayRequest(pay);
		return url;
	}

	@Override
	public void updatePaySuccess(TenpayReturn tr) throws NotFindException, PayBusinessIdUnuquieException, SystemBusyException {
		payRequestManageService.updatePayRequestSuccess(tr.getId(), tr.getNotifyId(), tr.getTransactionId(), "财付通支付");
	}

}
