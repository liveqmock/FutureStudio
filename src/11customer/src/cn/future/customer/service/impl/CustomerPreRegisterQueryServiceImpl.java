package cn.future.customer.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.VerifyUnmatchException;
import cn.future.customer.dto.DCustomerPreRegister;
import cn.future.customer.pojo.PCustomerPreRegister;
import cn.future.customer.service.CustomerPreRegisterQueryService;

public class CustomerPreRegisterQueryServiceImpl implements CustomerPreRegisterQueryService {
	private BaseDao baseDao;

	private static final int PRECUSTOMER_ID_UNMATCH_ACTIVEID = 10010;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}


	@Override
	public void validatePreRegisterKey(String id, String vcodeKey) throws NotFindException, VerifyUnmatchException{
		PCustomerPreRegister pojo = baseDao.findById(PCustomerPreRegister.class, id);
		if(!pojo.getActiveId().equals(vcodeKey)){
			VerifyUnmatchException e = new VerifyUnmatchException("安全码验证失败："+PRECUSTOMER_ID_UNMATCH_ACTIVEID);
			throw e;
		}
	}


	@Override
	public List<DCustomerPreRegister> transfer(List<PCustomerPreRegister> pojos) {
		List<DCustomerPreRegister> list = new ArrayList<DCustomerPreRegister>();
		if(pojos!=null && pojos.size()>0){
			for(PCustomerPreRegister p : pojos){
				list.add(this.transfer(p));
			}
		}
		return list;
	}


	@Override
	public DCustomerPreRegister transfer(PCustomerPreRegister pojo) {
		return new DCustomerPreRegister(pojo);
	}
}
