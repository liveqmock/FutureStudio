package cn.future.customer.service.impl;

import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.NotUniqueException;
import cn.future.customer.dto.DCustomer;
import cn.future.customer.dto.DCustomerCredit;
import cn.future.customer.dto.DCustomerDetailInfo;
import cn.future.customer.exception.DCustomerFactoryNotFindDtoException;
import cn.future.customer.pojo.PCustomer;
import cn.future.customer.pojo.PCustomerCredit;
import cn.future.customer.pojo.PCustomerDetailInfo;
import cn.future.customer.service.CustomerCreditQueryService;
import cn.future.customer.service.CustomerQueryService;
import cn.future.customer.service.DCustomerFactory;
import cn.future.oa.dto.DAccount;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.service.AccountQueryService;
import cn.future.oa.service.AccountService;

public class DCustomerFactoryImpl implements DCustomerFactory {
	private BaseDao baseDao;
	private CustomerCreditQueryService customerCreditQueryService;
	private CustomerQueryService customerQueryService;
	private AccountQueryService accountQueryService;
	private AccountService accountService;
	private DCustomer dto=null;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public void setAccountQueryService(AccountQueryService accountQueryService) {
		this.accountQueryService = accountQueryService;
	}

	public void setCustomerQueryService(CustomerQueryService customerQueryService) {
		this.customerQueryService = customerQueryService;
	}

	public void setCustomerCreditQueryService(
			CustomerCreditQueryService customerCreditQueryService) {
		this.customerCreditQueryService = customerCreditQueryService;
	}

	private void checkExist() throws DCustomerFactoryNotFindDtoException{
		if(dto == null){
			DCustomerFactoryNotFindDtoException e = new DCustomerFactoryNotFindDtoException("数据对象没有初始化");
			throw e;
		}
	}
	@Override
	public DCustomerFactory initById(String id) throws NotFindException {
		PCustomer pojo = customerQueryService.findCustomerById(id);
		return this.initByPojo(pojo);
	}

	@Override
	public DCustomerFactory initByPojo(PCustomer pojo) {
		this.dto = customerQueryService.transfer(pojo);
		return this;
	}

	@Override
	public DCustomer getDCustomer() throws DCustomerFactoryNotFindDtoException {
		this.checkExist();
		return this.dto;
	}

	@Override
	public DCustomerFactory initCredit() throws DCustomerFactoryNotFindDtoException {
		this.checkExist();
		List<PCustomerCredit> pojos = customerCreditQueryService.findCustomerCredit(this.dto.getId(), 1, 20);
		List<DCustomerCredit> dtos = customerCreditQueryService.transfer(pojos);
		this.dto.setCredits(dtos);
		return this;
	}

	@Override
	public DCustomerFactory initDetailInfo() throws DCustomerFactoryNotFindDtoException, NotFindException {
		this.checkExist();
		PCustomerDetailInfo pojo = null;
		try{
			pojo = baseDao.findById(PCustomerDetailInfo.class, this.dto.getId());
		}catch(NotFindException e){
			
		}
		if(null==pojo){
			pojo = new PCustomerDetailInfo();
			pojo.setId(this.dto.getId());
			baseDao.save(pojo);
		}
		DCustomerDetailInfo detail = new DCustomerDetailInfo(pojo);
		this.dto.setDetailInfo(detail);
		return this;
	}

	@Override
	public DCustomerFactory initAdviser() throws DCustomerFactoryNotFindDtoException, NotFindException, NotUniqueException {
		this.checkExist();
		PAccount ap = accountQueryService.findAccountByEmployId(this.dto.getAdviserEmployId());
		DAccount dp = accountService.transferPAccount(ap, 0);
		this.dto.setAdviserEmploy(dp);
		return this;
	}

}
