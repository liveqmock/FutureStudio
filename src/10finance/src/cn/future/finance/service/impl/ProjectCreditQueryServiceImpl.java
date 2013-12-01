package cn.future.finance.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.customer.dto.DCustomerCredit;
import cn.future.customer.pojo.PCustomerCredit;
import cn.future.customer.service.CustomerCreditQueryService;
import cn.future.finance.pojo.PFinanceProjectCredit;
import cn.future.finance.service.ProjectCreditQueryService;

public class ProjectCreditQueryServiceImpl implements ProjectCreditQueryService {
	private BaseDao baseDao;
	private CustomerCreditQueryService customerCreditQueryService;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setCustomerCreditQueryService(
			CustomerCreditQueryService customerCreditQueryService) {
		this.customerCreditQueryService = customerCreditQueryService;
	}

	@Override
	public List<DCustomerCredit> findProjectCreditByProjectId(String projectId) {
		String hql = "from cn.future.finance.pojo.PFinanceProjectCredit as p where p.projectId=:projectId";
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("projectId", projectId);
		List<PFinanceProjectCredit> cols = baseDao.findAll(PFinanceProjectCredit.class, hql,null,null, params);
		
		List<String> ids = new ArrayList<String>();
		if(cols!=null && cols.size()>0){
			for(PFinanceProjectCredit p : cols){
				ids.add(p.getCreditId());
			}
		}
		List<PCustomerCredit> pojos = customerCreditQueryService.findCustomerCreditByIds(ids);
		List<DCustomerCredit> dtos = customerCreditQueryService.transfer(pojos);
		return dtos;
	}

}
