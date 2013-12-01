package cn.future.customer.service.impl;

import cn.future.common.dao.BaseDao;
import cn.future.customer.pojo.PCustomerCredit;
import cn.future.customer.service.CustomerCreditManageService;

public class CustomerCreditManageServiceImpl implements
		CustomerCreditManageService {
	private BaseDao baseDao;
	
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	@Override
	public PCustomerCredit addCustomerCredit(PCustomerCredit pojo) {
		baseDao.save(pojo);
		return pojo;
	}

}
