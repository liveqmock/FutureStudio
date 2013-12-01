package cn.future.pay.service.impl;

import cn.future.common.dao.BaseDao;
import cn.future.pay.pojo.PWithdrawAccount;
import cn.future.pay.service.WithdrawAccountManageService;

public class WithdrawAccountManageServiceImpl implements WithdrawAccountManageService{
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public PWithdrawAccount addWithdrawAccount(PWithdrawAccount pojo) {
		baseDao.save(pojo);
		return pojo;
	}

}
