package cn.future.finance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.finance.dto.DCreditRequest;
import cn.future.finance.pojo.PCreditRequest;
import cn.future.finance.service.CreditRequestQueryService;

public class CreditRequestQueryServiceImpl implements CreditRequestQueryService {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public int findCreditRequest(List<PCreditRequest> list, String userId,
			String position, Integer page, Integer pageSize) {
		String hql = "from cn.future.finance.pojo.PCreditRequest as p where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(null!=userId){
			hql+=" and p.userId=:userId";
			params.put("userId", userId);
		}
		if(null!=position){
			hql+=" and p.position=:position";
			params.put("position", position);
		}
		hql+=" order by p.addDate desc";
		List<PCreditRequest> finds = baseDao.findAll(PCreditRequest.class, hql, page, pageSize, params);
		list.addAll(finds);
		
		hql = "select count(*) "+hql;
		return baseDao.findCount(hql, params);
	}

	@Override
	public List<DCreditRequest> transfer(List<PCreditRequest> pojos) {
		List<DCreditRequest> list = new ArrayList<DCreditRequest>();
		if(null!=pojos){
			for(PCreditRequest p:pojos){
				list.add(this.transfer(p));
			}
		}
		return list;
	}

	@Override
	public DCreditRequest transfer(PCreditRequest pojo) {
		DCreditRequest dto = new DCreditRequest(pojo);
		return dto;
	}
}
