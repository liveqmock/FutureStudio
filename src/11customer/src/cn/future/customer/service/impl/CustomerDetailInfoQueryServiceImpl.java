package cn.future.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.customer.dto.DCustomerDetailInfo;
import cn.future.customer.pojo.PCustomerDetailInfo;
import cn.future.customer.service.CustomerDetailInfoQueryService;

public class CustomerDetailInfoQueryServiceImpl implements
		CustomerDetailInfoQueryService {
	private BaseDao baseDao;
	@Override
	public PCustomerDetailInfo findById(String id) throws NotFindException {
		PCustomerDetailInfo p = baseDao.findById(PCustomerDetailInfo.class, id);
		return p;
	}

	@Override
	public DCustomerDetailInfo transfer(PCustomerDetailInfo p) {
		
		return new DCustomerDetailInfo(p);
	}

	@Override
	public List<DCustomerDetailInfo> transfer(List<PCustomerDetailInfo> ps) {
		List<DCustomerDetailInfo> list = new ArrayList<DCustomerDetailInfo>();
		if(null!=ps){
			for(PCustomerDetailInfo p:ps){
				list.add(this.transfer(p));
			}
		}
		return list;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
