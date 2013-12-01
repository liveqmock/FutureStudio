package cn.future.customer.service.impl;

import cn.future.common.dao.BaseDao;
import cn.future.customer.pojo.PCustomerDetailInfo;
import cn.future.customer.service.CustomerDetailInfoManageService;

public class CustomerDetailInfoManageServiceImpl implements
		CustomerDetailInfoManageService {
	private BaseDao baseDao;
	@Override
	public PCustomerDetailInfo save(PCustomerDetailInfo p) {
		baseDao.saveOrUpdate(p);
		return p;
	}
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
