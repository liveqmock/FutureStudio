package cn.future.finance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.finance.dto.DFinanceProject;
import cn.future.finance.pojo.PFinanceProject;
import cn.future.finance.pojo.PFinanceProjectBidDetail;
import cn.future.finance.service.DFinanceProjectFactory;
import cn.future.finance.service.FinanceProjectQueryService;
import cn.future.finance.util.FinanceCodeUtil;

public class FinanceProjectQueryServiceImpl implements  FinanceProjectQueryService{
	private BaseDao baseDao;
	private DFinanceProjectFactory dFinanceProjectFactory;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	public void setdFinanceProjectFactory(
			DFinanceProjectFactory dFinanceProjectFactory) {
		this.dFinanceProjectFactory = dFinanceProjectFactory;
	}


	@Override
	public int findFinanceProject(List<PFinanceProject> list, Integer projectType, Integer returnType, Integer startStatus, int page,
			int pageSize, String query) {
		String hql="from cn.future.finance.pojo.PFinanceProject as p where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(startStatus!=null){
			hql+=" and p.statusCode>:statusCode";
			params.put("statusCode", startStatus.intValue());
		}
		if(projectType!=null){
			hql+=" and p.projectTypeCode=:projectType";
			params.put("projectType", projectType);
		}
		if(returnType!=null){
			hql+=" and p.returnTypeCode=:returnType";
			params.put("returnType", returnType);
		}
		if(query!=null){
			hql+=" and (p.title like :query or p.timeType like :query or p.flowId like :query)";
			params.put("query", "%"+query+"%");
		}
		hql+=" order by p.startDate desc";
		list.addAll(baseDao.findAll(PFinanceProject.class, hql, page, pageSize, params));
		
		hql="select count(*) "+hql;
		int c = baseDao.findCount(hql, params);
		return c;
	}


	@Override
	public PFinanceProject findFinanceById(String id) throws NotFindException {
		PFinanceProject project = baseDao.findById(PFinanceProject.class, id);
		if(project == null){
			NotFindException e = new NotFindException("ID为"+id+"的项目，没有找到");
			throw e;
		}
		return project;
	}


	@Override
	public List<DFinanceProject> transfer(List<PFinanceProject> pojos) {
		int[] m = {0};
		return this.transfer(pojos, m);
	}


	@Override
	public DFinanceProject transfer(PFinanceProject pojo) {
		int[] m = {0};
		return this.transfer(pojo, m);
	}


	@Override
	public List<DFinanceProject> transfer(List<PFinanceProject> pojos,
			int[] transMode) {
		List<DFinanceProject> list = new ArrayList<DFinanceProject>();
		if(pojos!=null){
			for(PFinanceProject p : pojos){
				list.add(this.transfer(p, transMode));
			}
		}
		return list;
	}


	@Override
	public DFinanceProject transfer(PFinanceProject pojo, int[] transMode) {
		dFinanceProjectFactory.initByPojo(pojo);
		for(int k : transMode){
			switch(k){
			case 1:
				dFinanceProjectFactory.initUserInfo();
				break;
			case 2:
				dFinanceProjectFactory.initCustomerInfo();
				break;
			case 4:
				dFinanceProjectFactory.initBids();
				break;
			case 5:
				dFinanceProjectFactory.initSecurityBids();
				break;
			case 6:
				dFinanceProjectFactory.initCredits();
				break;
			}
		}
		
		return dFinanceProjectFactory.getDFinanceProject();
	}
	@Override
	public int findCustomerHistoryRequestFinanceProject(List<PFinanceProject> list, String customerId, Integer page, Integer pageSize) {
		String hql = "from cn.future.finance.pojo.PFinanceProject as p where p.statusCode>:statusCode and p.customerId=:customerId order by p.startDate desc";
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("statusCode", FinanceCodeUtil.BIDDING);
		params.put("customerId", customerId);
		List<PFinanceProject> listp = baseDao.findAll(PFinanceProject.class, hql, page, pageSize, params);
		list.addAll(listp);
		
		hql = "select count(*) " + hql;
		
		return baseDao.findCount(hql, params);
	}
	@Override
	public int findCustomerHistoryBidFinanceProject(List<PFinanceProject> list, String customerId, Integer page, Integer pageSize, Integer isFinish) {
		String hql = "from cn.future.finance.pojo.PFinanceProjectBidDetail as p where p.customerId=:customerId";
		if(null!=isFinish){
			if(isFinish.intValue()==0){
				hql+=" and p.cashNumber!=p.alreadyReturn";
			}else if(isFinish.intValue()==1){
				hql+=" and p.cashNumber=p.alreadyReturn";
			}	
		}
		hql+=" order by p.bidDate desc group by p.financeProjectId";
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("customerId", customerId);
		List<PFinanceProjectBidDetail> listBids = baseDao.findAll(PFinanceProjectBidDetail.class, hql, page, pageSize, params);
		List<String> ids = new ArrayList<String>();
		HashMap<String,String> mapIds = new HashMap<String,String>();
		
		if(listBids != null && listBids.size()>0){
			for(PFinanceProjectBidDetail pb : listBids){
				mapIds.put(pb.getFinanceProjectId(), pb.getId());
			}
			for(String s: mapIds.keySet()){
				ids.add(s);
			}
		}
		
		
		list.addAll(baseDao.findAll(PFinanceProject.class, ids));
		return ids.size();
	}
}
