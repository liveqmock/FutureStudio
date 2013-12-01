package cn.future.finance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.finance.dto.DFinanceProjectBidDetail;
import cn.future.finance.pojo.PFinanceProject;
import cn.future.finance.pojo.PFinanceProjectBidDetail;
import cn.future.finance.service.FinanceProjectBidQueryService;

public class FinanceProjectBidQueryServiceImpl implements
		FinanceProjectBidQueryService {
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public int findFinanceProjectBidDetails(List<PFinanceProjectBidDetail> list, 
			String financeProjectId, Integer page, Integer pageSize) {
		return this.findBidDetails(list, financeProjectId, null, page, pageSize);
	}

	@Override
	public int findCustomerBidDetails(List<PFinanceProjectBidDetail> list, 
			String customerId, Integer page, Integer pageSize) {
		return this.findBidDetails(list, null, customerId, page, pageSize);
	}
	
	public int findBidDetails(List<PFinanceProjectBidDetail> list,
			String financeProjectId, String customerId, Integer page, Integer pageSize) {
		String hql="from cn.future.finance.pojo.PFinanceProjectBidDetail as p where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(financeProjectId!=null){
			hql+=" and p.financeProjectId=:financeProjectId";
			params.put("financeProjectId", financeProjectId);
		}
		if(customerId!=null){
			hql+=" and p.customerId=:customerId";
			params.put("customerId", customerId);
		}
		hql+=" order by p.bidDate desc";
		list.addAll(baseDao.findAll(PFinanceProjectBidDetail.class, hql, page, pageSize, params));
		hql="select count(*) "+hql;
		return baseDao.findCount(hql, params);
	}

	@Override
	public List<DFinanceProjectBidDetail> transfer(
			List<PFinanceProjectBidDetail> pojos,boolean security) {
		List<DFinanceProjectBidDetail> list = new ArrayList<DFinanceProjectBidDetail>();
		if(pojos!=null){
			for(PFinanceProjectBidDetail p : pojos){
				list.add(this.transfer(p, security));
			}
		}
		return list;
	}

	@Override
	public DFinanceProjectBidDetail transfer(PFinanceProjectBidDetail pojo, boolean security) {
		DFinanceProjectBidDetail dto = new DFinanceProjectBidDetail(pojo, security);
		try {
			PFinanceProject project = baseDao.findById(PFinanceProject.class, pojo.getFinanceProjectId());
			dto.setFinanceProjectFlowId(project.getFlowId());
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public int findCustomerUnreturnedFinanceProjectBidDetails(List<PFinanceProjectBidDetail> list, String customerId, Integer page, Integer pageSize) {
		String hql = "from cn.future.finance.pojo.PFinanceProjectBidDetail as p where p.customerId=:customerId and p.cashNumber>p.alreadyReturn and p.statusCode>-1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("customerId", customerId);
		List<PFinanceProjectBidDetail> plist = baseDao.findAll(PFinanceProjectBidDetail.class, hql, page, pageSize, params);
		list.addAll(plist);
		
		hql = "select count(*) "+hql;
		return baseDao.findCount(hql, params);
	}
	
	

	@Override
	public long findCustomerUnreturnedNumber(String customerId) {
		List<PFinanceProjectBidDetail> list = new ArrayList<PFinanceProjectBidDetail>();
		long number=0;
		this.findCustomerUnreturnedFinanceProjectBidDetails(list, customerId, null, null);
		if(list!=null){
			for(PFinanceProjectBidDetail p:list){
				number = number + (p.getCashNumber()-p.getAlreadyReturn());
			}
		}
		return number;
	}

}
