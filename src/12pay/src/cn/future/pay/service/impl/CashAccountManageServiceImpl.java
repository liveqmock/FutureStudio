package cn.future.pay.service.impl;

import java.util.HashMap;

import org.hibernate.StaleObjectStateException;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.pay.exception.PayBusinessIdUnuquieException;
import cn.future.pay.exception.PayOverSpendException;
import cn.future.pay.pojo.PCashAccount;
import cn.future.pay.pojo.PCashAccountDetail;
import cn.future.pay.service.CashAccountManageService;

public class CashAccountManageServiceImpl implements CashAccountManageService {
	private BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	/* (non-Javadoc)
	 * @see cn.future.pay.service.impl.CashAccountManageService#addCashAccount(cn.future.pay.pojo.PCashAccount)
	 */
	@Override
	public PCashAccount addCashAccount(PCashAccount ca){
		baseDao.save(ca);
		return ca;
	}
	/* (non-Javadoc)
	 * @see cn.future.pay.service.impl.CashAccountManageService#createCashAccountDetailIn(java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public PCashAccountDetail createCashAccountDetailIn(String accountId, String businessId,
			long inNumber, String title, String content,
			String comments, String tagCode) throws NotFindException, PayBusinessIdUnuquieException, SystemBusyException{
		if(inNumber < 0){
			inNumber = inNumber * -1;
		}
		
		this.checkBusinessIdUnique(businessId);
		PCashAccountDetail p = null;
		try {
			p = this.createCashAccountDetail(accountId, businessId, inNumber, "+", title, content, comments, tagCode);
		} catch (PayOverSpendException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}
	/* (non-Javadoc)
	 * @see cn.future.pay.service.impl.CashAccountManageService#createCashAccountDetailOut(java.lang.String, java.lang.String, int, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public PCashAccountDetail createCashAccountDetailOut(String accountId, String businessId,
			long outNumber, String title, String content,
			String comments, String tagCode) throws NotFindException, PayOverSpendException, PayBusinessIdUnuquieException, SystemBusyException{
		if(outNumber < 0){
			outNumber = outNumber * -1;
		}
		
		this.checkBusinessIdUnique(businessId);
		PCashAccountDetail p = this.createCashAccountDetail(accountId, businessId, outNumber, "-", title, content, comments, tagCode);
			
		return p;
	}
	/**
	 * 
	 * @param accountId
	 * @param businessId
	 * @param inNumber 存入为正，取出为负
	 * @param operate  - +
	 * @param title
	 * @param content
	 * @param comments
	 * @param tagCode
	 * @return
	 * @throws NotFindException
	 * @throws PayOverSpendException 
	 * @throws PaySystemBusyException 
	 */
	public PCashAccountDetail createCashAccountDetail( String accountId, String businessId,
			long number, String operate, String title, String content,
			String comments, String tagCode) throws NotFindException, PayOverSpendException, SystemBusyException{
		if(number < 0){
			number = number*-1;
		}

		PCashAccount customerAccount = baseDao.findById(PCashAccount.class, accountId);
		long oldNumber = customerAccount.getCashNumber();
		long newNumber;
		
		if("-".equals(operate)){
			if(oldNumber < number){
				PayOverSpendException pose = new PayOverSpendException("账户金额不足");
				throw pose;
			}
			newNumber = oldNumber - number;
			
		}else{
			newNumber = oldNumber + number;
		}

		PCashAccountDetail p = new PCashAccountDetail(accountId, businessId, number, oldNumber, newNumber, operate, title, content,comments, tagCode);
		
		customerAccount.setCashNumber(newNumber);
		try{
			baseDao.save(p);
			baseDao.update(customerAccount);
		}catch(StaleObjectStateException e){
			SystemBusyException psbe = new SystemBusyException("支付系统忙，请稍后再试",e);
			throw psbe;
		}
		
		return p;
	}
	/* (non-Javadoc)
	 * @see cn.future.pay.service.impl.CashAccountManageService#checkBusinessIdUnique(java.lang.String)
	 */
	@Override
	public void checkBusinessIdUnique(String businessId) throws PayBusinessIdUnuquieException {
		String hql = "select count(*) from cn.future.pay.pojo.PCashAccountDetail as p where p.businessId=:businessId";
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("businessId", businessId);
		int k = baseDao.findCount(hql, params);
		if(k>0){
			PayBusinessIdUnuquieException e = new PayBusinessIdUnuquieException("业务号已经入账过，请勿重复入账");
			throw e;
		}
	}
	@Override
	public void validateCustomerCashAccountExist(String id) {
		PCashAccount account = null;
		try {
			account = baseDao.findById(PCashAccount.class, id);
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(account == null){
			PCashAccount pojo = new PCashAccount(id,2);
			baseDao.save(pojo);
		}
	}
}
