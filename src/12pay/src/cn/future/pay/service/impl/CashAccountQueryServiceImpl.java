package cn.future.pay.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.pay.dto.DCashAccount;
import cn.future.pay.dto.DCashAccountDetail;
import cn.future.pay.pojo.PCashAccount;
import cn.future.pay.pojo.PCashAccountDetail;
import cn.future.pay.service.CashAccountQueryService;

public class CashAccountQueryServiceImpl implements CashAccountQueryService {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	/* (non-Javadoc)
	 * @see cn.future.pay.service.impl.CashAccountQueryService#findPCashAccountById(java.lang.String)
	 */
	@Override
	public PCashAccount findPCashAccountById(String id) throws NotFindException{
		return baseDao.findById(PCashAccount.class, id);
	}
	
	/* (non-Javadoc)
	 * @see cn.future.pay.service.impl.CashAccountQueryService#findPCashAccountDetail(java.util.List, java.lang.String, int, int, java.lang.String)
	 */
	@Override
	public int findPCashAccountDetail(List<PCashAccountDetail> list, String accountId,int page, int pageSize, String operate){
		String hql = "from cn.future.pay.pojo.PCashAccountDetail as p where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(accountId!=null){
			hql+=" and p.cashAccountId=:accountId";
			params.put("accountId", accountId);
		}
		if(operate!=null && ("-".equals(operate) || "+".equals(operate))){
			hql+=" and p.cashOperate=:operate";
			params.put("operate", operate);
		}
		hql+=" order by p.updateDate desc";
		List<PCashAccountDetail> ql = baseDao.findAll(PCashAccountDetail.class, hql, page, pageSize, params);
		list.addAll(ql);
		hql="select count(*) "+hql;
		return baseDao.findCount(hql, params);
	}

	@Override
	public DCashAccount transfer(PCashAccount pojo) {
		return new DCashAccount(pojo);
	}

	@Override
	public List<DCashAccount> transfer(List<PCashAccount> pojos) {
		List<DCashAccount> list= new ArrayList<DCashAccount>();
		if(pojos != null){
			for(PCashAccount p : pojos){
				list.add(this.transfer(p));
			}
		}
		return list;
	}

	@Override
	public DCashAccountDetail detailTransfer(PCashAccountDetail pojo) {
		return new DCashAccountDetail(pojo);
	}

	@Override
	public List<DCashAccountDetail> detailTransfer(
			List<PCashAccountDetail> pojos) {
		List<DCashAccountDetail> list = new ArrayList<DCashAccountDetail>();
		if(null!=pojos){
			for(PCashAccountDetail p:pojos){
				list.add(this.detailTransfer(p));
			}
		}
		return list;
	}

	@Override
	public int findPCashAccount(List<PCashAccount> list, Integer page,
			Integer pageSize, String query) {
		String hql = "from cn.future.pay.pojo.PCashAccount as p where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(null!=query){
			hql += " and p.id like :query";
			params.put("query", "%"+query+"%");
		}
		hql += " order by p.lastUpdate desc";
		List<PCashAccount> pos = baseDao.findAll(PCashAccount.class, hql, page, pageSize, params);
		list.addAll(pos);
		hql = "select count(*) "+hql;
		return baseDao.findCount(hql, params);
	}
}
