package cn.future.finance.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.common.service.impl.BaseService;
import cn.future.finance.dto.DProjectReturnPlan;
import cn.future.finance.pojo.PFinanceProject;
import cn.future.finance.pojo.PProjectReturnPlan;
import cn.future.finance.service.ProjectReturnPlanQueryService;

public class ProjectReturnPlanQueryServiceImpl extends BaseService implements
		ProjectReturnPlanQueryService {

	private static final long serialVersionUID = -7095540123450323544L;

	@Override
	public List<PProjectReturnPlan> findProjectReturnPlan(String projectId) {
		String hql = "from cn.future.finance.pojo.PProjectReturnPlan as p where p.projectId=:projectId order by p.planReturnDate asc";
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("projectId", projectId);
		return baseDao.findAll(PProjectReturnPlan.class, hql, null, null, params);
	}

	@Override
	public DProjectReturnPlan transfer(PProjectReturnPlan p) {
		DProjectReturnPlan dto = new DProjectReturnPlan(p);
		
		try {
			PFinanceProject pro = baseDao.findById(PFinanceProject.class, p.getProjectId());
			dto.setProjectFlowId(pro.getFlowId());
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dto;
	}

	@Override
	public List<DProjectReturnPlan> transfer(List<PProjectReturnPlan> ps) {
		List<DProjectReturnPlan> list = new ArrayList<DProjectReturnPlan>();
		if(null!=ps){
			for(PProjectReturnPlan p:ps){
				list.add(this.transfer(p));
			}
		}
		return list;
	}

	@Override
	public PProjectReturnPlan findById(String id) throws NotFindException {
		
		return baseDao.findById(PProjectReturnPlan.class,id);
	}

	@Override
	public int findAll(List<PProjectReturnPlan> list,Integer statusCode, Integer page,
			Integer pageSize, Date start, Date end, String queryComments, boolean isDesc) {
		String hql = "from cn.future.finance.pojo.PProjectReturnPlan as p where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(null!=statusCode){
			hql+=" and p.statusCode=:statusCode";
			params.put("statusCode", statusCode);
		}
		if(null!=start){
			hql+=" and p.planReturnDate>:start";
			params.put("start", start);
		}
		if(null!=end){
			hql+=" and p.planReturnDate<:end";
			params.put("end", end);
		}
		if(null!=queryComments){
			hql+=" and p.comments like :query";
			params.put("query", "%"+queryComments+"%");
		}
		if(isDesc){
			hql+=" order by p.planReturnDate desc";
		}else{
			hql+=" order by p.planReturnDate asc";
		}
		List<PProjectReturnPlan> pojos = baseDao.findAll(PProjectReturnPlan.class, hql, page, pageSize, params);
		list.addAll(pojos);
		
		hql="select count(*) "+hql;
		
		return baseDao.findCount(hql, params);
	}

	@SuppressWarnings("rawtypes")
	@Override
	public int findAll(List<PProjectReturnPlan> list, List<String> projectIds,
			Integer statusCode, Integer page, Integer pageSize, Date start,
			Date end, String queryComments, boolean isDesc) {
		String hql = "from cn.future.finance.pojo.PProjectReturnPlan as p where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		HashMap<String,Collection> paramsList = new HashMap<String,Collection>();
		if(null!=projectIds){
			hql+=" and p.projectId in (:projectIds)";
			paramsList.put("projectIds", projectIds);
		}
		if(null!=statusCode){
			hql+=" and p.statusCode=:statusCode";
			params.put("statusCode", statusCode);
		}
		if(null!=start){
			hql+=" and p.planReturnDate>:start";
			params.put("start", start);
		}
		if(null!=end){
			hql+=" and p.planReturnDate<:end";
			params.put("end", end);
		}
		if(null!=queryComments){
			hql+=" and p.comments like :query";
			params.put("query", "%"+queryComments+"%");
		}
		if(isDesc){
			hql+=" order by p.planReturnDate desc";
		}else{
			hql+=" order by p.planReturnDate asc";
		}
		List<PProjectReturnPlan> pojos = baseDao.findAll(PProjectReturnPlan.class, hql, page, pageSize, params,paramsList);
		list.addAll(pojos);
		
		hql="select count(*) "+hql;
		
		return baseDao.findCount(hql, params, paramsList);
	}

}
