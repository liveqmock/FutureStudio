package cn.future.pay.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.pay.dto.DPayRequest;
import cn.future.pay.pojo.PPayRequest;
import cn.future.pay.service.PayRequestQueryService;

public class PayRequestQueryServiceImpl implements PayRequestQueryService {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public int findPayRequest(List<PPayRequest> list, int page, int pageSize,
			String customerId, Date start, Date end, Integer status) {
		
		return this.findPayRequestImpl(list, page, pageSize, customerId, start, end, status);
	}

	public int findPayRequestImpl(List<PPayRequest> list, int page, int pageSize,
			String customerId, Date start, Date end, Integer status){
		String hql = "from cn.future.pay.pojo.PPayRequest as p where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(null!=customerId){
			hql+=" and p.cashAccountId=:cashAccountId";
			params.put("cashAccountId", customerId);
		}
		if(null!=start){
			hql+=" and p.createDate>:start";
			params.put("start",start);
		}
		if(null!=end){
			hql+=" and p.createDate<:end";
			params.put("end",end);
		}
		if(null!=status){
			hql+=" and p.statucCode=:status";
			params.put("status", status);
		}
		hql+=" order by p.createDate desc";
		List<PPayRequest> pojos = baseDao.findAll(PPayRequest.class, hql, page, pageSize, params);
		list.addAll(pojos);
		hql = "select count(*) "+hql;
		return baseDao.findCount(hql,params);
	}

	@Override
	public DPayRequest transfer(PPayRequest pojo) {
		return new DPayRequest(pojo);
	}

	@Override
	public List<DPayRequest> transfer(List<PPayRequest> pojos) {
		List<DPayRequest> list = new ArrayList<DPayRequest>();
		if(null!=pojos){
			for(PPayRequest p : pojos){
				list.add(this.transfer(p));
			}
		}
		return list;
	}
}
