package cn.future.customer.service.impl;

import java.text.ParseException;

import org.hibernate.exception.ConstraintViolationException;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.NotUniqueException;
import cn.future.customer.exception.IdCardException;
import cn.future.customer.exception.ModifyNotAllowedException;
import cn.future.customer.exception.PasswordUnmatchException;
import cn.future.customer.pojo.PCustomer;
import cn.future.customer.pojo.PCustomerDetailInfo;
import cn.future.customer.pojo.PCustomerPreRegister;
import cn.future.customer.service.CustomerManageService;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.service.AccountQueryService;
import cn.future.util.IdCardUtil;
import cn.future.util.StringUtil;

public class CustomerManageServiceImpl implements CustomerManageService {
	private AccountQueryService accountQueryService;
	private BaseDao baseDao;
	public BaseDao getBaseDao() {
		return baseDao;
	}

	public void setAccountQueryService(AccountQueryService accountQueryService) {
		this.accountQueryService = accountQueryService;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public PCustomer addCustomer(PCustomer customer) throws ConstraintViolationException{
		baseDao.save(customer);
		return customer;
	}

	@Override
	public PCustomerDetailInfo addCustomerDetail(String customerId,
			PCustomerDetailInfo detail) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PCustomer addCustomerByPreRegister(String registerId) throws NotFindException {
		PCustomerPreRegister pre = baseDao.findById(PCustomerPreRegister.class, registerId);
		PCustomer customer = new PCustomer(pre);
		baseDao.save(customer);
		baseDao.delete(pre);
		return customer;
	}

	@Override
	public PCustomer updatePCustomerAdviser(String cusId, String employId) throws NotFindException, NotUniqueException {
		PCustomer cus = baseDao.findById(PCustomer.class, cusId);
		PAccount ac = accountQueryService.findAccountByEmployId(employId);
		cus.setAdviserEmployId(ac.getEmployeeId());
		baseDao.update(cus);
		return cus;
	}

	@Override
	public PCustomer updateCustomerIdcard(String cusId, String idcard, boolean admin) throws NotFindException, ModifyNotAllowedException, ParseException, IdCardException {
		String valiRes = IdCardUtil.IDCardValidate(idcard);
		if(!"".equals(valiRes)){
			IdCardException e = new IdCardException(valiRes);
			throw e;
		}
		PCustomer cus = baseDao.findById(PCustomer.class, cusId);
		String s1 = cus.getIdCard();
		if(admin){
			cus.setIdCard(idcard);
		}else{
			if(null==s1 || s1.length()<5){
				cus.setIdCard(idcard);
			}else{
				ModifyNotAllowedException e = new ModifyNotAllowedException("身份证信息不能再次修改，如需修改请联系客服，并提交身份证扫描件");
				throw e;
			}
		}
		baseDao.update(cus);
		return cus;
	}

	@Override
	public void updatePassword(String id, String oldPassword, String newPassword) throws NotFindException, PasswordUnmatchException {
		PCustomer cus = baseDao.findById(PCustomer.class, id);
		if(cus.getAccountPassword().equals(StringUtil.sha512Encrypt(oldPassword))){
			cus.setAccountPassword(StringUtil.sha512Encrypt(newPassword));
		}else{
			PasswordUnmatchException e = new PasswordUnmatchException("密码错误");
			throw e;
		}
	}

	@Override
	public PCustomer updateCustomer(PCustomer pojo) {
		baseDao.saveOrUpdate(pojo);
		return pojo;
	}


}
