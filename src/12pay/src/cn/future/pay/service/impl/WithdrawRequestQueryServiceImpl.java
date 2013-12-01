package cn.future.pay.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.pay.dto.DWithdrawRequest;
import cn.future.pay.pojo.PWithdrawRequest;
import cn.future.pay.service.WithdrawRequestQueryService;

public class WithdrawRequestQueryServiceImpl implements
		WithdrawRequestQueryService {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public int find(List<PWithdrawRequest> list, String userId, Integer status,
			Integer page, Integer pageSize) {
		String hql = "from cn.future.pay.pojo.PWithdrawRequest as p where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(null!=userId){
			hql += " and p.userId=:userId";
			params.put("userId",userId);
		}
		if(null!=status){
			hql += " and p.statusCode>:statusCode";
			params.put("statusCode",status);
		}
		hql += " order by p.addDate desc";
		baseDao.findAll(PWithdrawRequest.class, hql, page, pageSize, params);
		hql = "select count(*) "+hql;
		return baseDao.findCount(hql,params);
	}

	@Override
	public List<DWithdrawRequest> transfer(List<PWithdrawRequest> pojos) {
		List<DWithdrawRequest> dtos = new ArrayList<DWithdrawRequest>();
		if(pojos!=null){
			for(PWithdrawRequest p : pojos){
				dtos.add(this.transfer(p));
			}
		}
		return dtos;
	}

	@Override
	public DWithdrawRequest transfer(PWithdrawRequest pojo) {
		return new DWithdrawRequest(pojo);
	}

}
