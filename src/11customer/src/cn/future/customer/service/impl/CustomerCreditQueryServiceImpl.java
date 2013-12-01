package cn.future.customer.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.customer.dto.DCustomerCredit;
import cn.future.customer.pojo.PCustomerCredit;
import cn.future.customer.service.CustomerCreditQueryService;

public class CustomerCreditQueryServiceImpl implements
		CustomerCreditQueryService {
	private BaseDao baseDao;
	
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<PCustomerCredit> findCustomerCredit(String customerId, int page, int pageSize) {
		String hql = "from cn.future.customer.pojo.PCustomerCredit as p where p.customerId=:customerId";
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("customerId", customerId);
		return baseDao.findAll(PCustomerCredit.class, hql, page, pageSize, params);
	}

	@Override
	public List<PCustomerCredit> findCustomerCreditByIds(List<String> ids) {
		List<PCustomerCredit> list = new ArrayList<PCustomerCredit>();
		if(ids!=null && ids.size()>0){
			for(String id : ids){
				try {
					list.add(baseDao.findById(PCustomerCredit.class, id));
				} catch (NotFindException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	@Override
	public List<DCustomerCredit> transfer(List<PCustomerCredit> pojos) {
		List<DCustomerCredit> dtos = new ArrayList<DCustomerCredit>();
		if(pojos!=null && pojos.size()>0){
			for(PCustomerCredit pojo : pojos){
				dtos.add(this.transfer(pojo));
			}
		}
		return dtos;
	}

	@Override
	public DCustomerCredit transfer(PCustomerCredit pojo) {
		return new DCustomerCredit(pojo);
	}

}
