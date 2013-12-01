package cn.future.pay.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.pay.dto.DWithdrawAccount;
import cn.future.pay.pojo.PWithdrawAccount;
import cn.future.pay.service.WithdrawAccountQueryService;

public class WithdrawAccountQueryServiceImpl implements
		WithdrawAccountQueryService {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<PWithdrawAccount> findAll(String userId, Integer statusCode) {
		String hql = "from cn.future.pay.pojo.PWithdrawAccount as p where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(null!=userId){
			hql+=" and p.userId=:userId";
			params.put("userId", userId);
		}
		if(null!=statusCode){
			hql+=" and p.statusCode=:statusCode";
			params.put("statusCode", statusCode);
		}
		hql+=" order by p.lastUpdate desc";
		return baseDao.findAll(PWithdrawAccount.class, hql, null, null, params);
	}

	@Override
	public PWithdrawAccount findById(String id) throws NotFindException {
		return baseDao.findById(PWithdrawAccount.class,id);
	}

	@Override
	public List<DWithdrawAccount> transfer(List<PWithdrawAccount> list) {
		List<DWithdrawAccount> dtos = new ArrayList<DWithdrawAccount>();
		if(null!=list && list.size()>0){
			for(PWithdrawAccount p :list){
				dtos.add(this.transfer(p));
			}
		}
		return dtos;
	}

	@Override
	public DWithdrawAccount transfer(PWithdrawAccount pojo) {
		return new DWithdrawAccount(pojo);
	}

}
