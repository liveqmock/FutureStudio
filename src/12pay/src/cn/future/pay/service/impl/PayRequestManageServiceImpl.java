package cn.future.pay.service.impl;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.pay.exception.PayBusinessIdUnuquieException;
import cn.future.pay.pojo.PPayRequest;
import cn.future.pay.service.CashAccountManageService;
import cn.future.pay.service.PayRequestManageService;

public class PayRequestManageServiceImpl implements PayRequestManageService {
	private BaseDao baseDao;
	private CashAccountManageService cashAccountManageService;

	@Override
	public PPayRequest addPayRequest(PPayRequest pojo) {
		baseDao.save(pojo);
		return pojo;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setCashAccountManageService(
			CashAccountManageService cashAccountManageService) {
		this.cashAccountManageService = cashAccountManageService;
	}

	@Override
	public PPayRequest updatePayRequestSuccess(PPayRequest pojo, String notifyId, String transId,String title) throws PayBusinessIdUnuquieException, NotFindException, SystemBusyException {
		if(pojo.getStatusCode()!=0){
			return pojo;
		}
		pojo.setNotifyId(notifyId);//tr.getNotifyId());
		pojo.setTransactionId(transId);//.getTransactionId());
		pojo.setStatusCode(1);
		baseDao.update(pojo);
		cashAccountManageService.createCashAccountDetailIn(pojo.getCashAccountId(), pojo.getBusinessId(), pojo.getTotalNumber(), pojo.getTitle(), pojo.getContent(), title,"");//"财付通支付成功", "");

		return pojo;
	}

	@Override
	public PPayRequest updatePayRequestSuccess(String pojoId, String notifyId, String transId,String title) throws PayBusinessIdUnuquieException, NotFindException, SystemBusyException {
		PPayRequest pojo = baseDao.findById(PPayRequest.class, pojoId);
		this.updatePayRequestSuccess(pojo, notifyId, transId, title);
		return pojo;
	}

}
