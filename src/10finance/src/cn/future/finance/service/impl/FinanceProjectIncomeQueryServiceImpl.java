package cn.future.finance.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.dao.BaseTypeDao;
import cn.future.common.exception.NotFindException;
import cn.future.finance.dto.DFinanceProjectIncome;
import cn.future.finance.pojo.PFinanceProject;
import cn.future.finance.pojo.PFinanceProjectIncome;
import cn.future.finance.service.FinanceProjectIncomeQueryService;

public class FinanceProjectIncomeQueryServiceImpl implements
		FinanceProjectIncomeQueryService {
	private BaseDao baseDao;
	private BaseTypeDao baseTypeDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setBaseTypeDao(BaseTypeDao baseTypeDao) {
		this.baseTypeDao = baseTypeDao;
	}

	@Override
	public int findFinanceProjectIncome(List<PFinanceProjectIncome> list,
			String cusId, String projectId, String bidId, Integer typeCode,
			Date start, Date end, Integer page, Integer pageSize) {
		String hql = "from cn.future.finance.pojo.PFinanceProjectIncome as p where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(null!=cusId){
			hql+=" and p.customerId=:cusId";
			params.put("cusId", cusId);
		}
		if(null!=projectId){
			hql+=" and p.financeProjectId=:financeProjectId";
			params.put("financeProjectId",projectId);
		}
		if(null!=bidId){
			hql+=" and p.financeProjectBidId=:bidId";
			params.put("bidId", bidId);
		}
		if(null!=typeCode){
			hql+=" and p.typeCode=:typeCode";
			params.put("typeCode", typeCode);
		}
		if(null!=start){
			hql+=" and p.incomeDate>:start";
			params.put("start", start);
		}
		if(null!=end){
			hql+=" and p.incomeDate<:end";
			params.put("end",end);
		}
		hql+=" order by p.incomeDate desc";
		List<PFinanceProjectIncome> pojos = baseDao.findAll(PFinanceProjectIncome.class, hql, page, pageSize, params);
		list.addAll(pojos);
		hql = "select count(*) "+hql;
		return baseDao.findCount(hql, params);
	}

	@Override
	public DFinanceProjectIncome transfer(PFinanceProjectIncome pojo) {
		DFinanceProjectIncome dto = new DFinanceProjectIncome(pojo);
		try {
			PFinanceProject project = baseDao.findById(PFinanceProject.class,pojo.getFinanceProjectId());
			dto.setFinanceProjectFlowId(project.getFlowId());
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return dto;
	}

	@Override
	public List<DFinanceProjectIncome> transfer(
			List<PFinanceProjectIncome> pojos) {
		List<DFinanceProjectIncome> dtos = new ArrayList<DFinanceProjectIncome>();
		if(null!=pojos){
			for(PFinanceProjectIncome p : pojos){
				dtos.add(this.transfer(p));
			}
		}
		return dtos;
	}

	@Override
	public long findSumIncome(String cusId, String projectId) {
		String hql = "select sum(p.incomeNumber) from cn.future.finance.pojo.PFinanceProjectIncome as p where p.typeCode=2";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(null!=cusId){
			hql+=" and p.customerId=:customerId";
			params.put("customerId",cusId);
		}
		if(null!=projectId){
			hql+=" and p.financeProjectId=:financeProjectId";
			params.put("financeProjectId",projectId);
		}
		Double t= baseTypeDao.sumOne(hql, params);
		return t.longValue();
	}

}
