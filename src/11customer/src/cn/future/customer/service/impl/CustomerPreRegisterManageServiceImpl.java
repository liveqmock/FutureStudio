package cn.future.customer.service.impl;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.NotUniqueException;
import cn.future.security.pojo.PSecurityCode;
import cn.future.customer.pojo.PCustomer;
import cn.future.customer.pojo.PCustomerPreRegister;
import cn.future.customer.service.CustomerPreRegisterManageService;
import cn.future.customer.service.CustomerQueryService;

public class CustomerPreRegisterManageServiceImpl implements CustomerPreRegisterManageService{
	private BaseDao baseDao;
	private CustomerQueryService customerQueryService;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setCustomerQueryService(CustomerQueryService customerQueryService) {
		this.customerQueryService = customerQueryService;
	}

	@Override
	public PCustomerPreRegister addCustomerPreRegister(
			PCustomerPreRegister pojo, PSecurityCode securityCode) throws NotUniqueException {
		PCustomer customer = null;
		try {
			customer = customerQueryService.findCustomerByName(pojo.getAccountName());
		} catch (NotFindException e) {
		} catch (NotUniqueException e) {
			e.printStackTrace();
		}
		if(customer!=null){
			NotUniqueException e = new NotUniqueException(pojo.getAccountName()+"已经存在，不能注册");
			throw e;
		}
		pojo.setActiveId(securityCode.getId());
		
		baseDao.save(pojo);
		return pojo;
	}
}
