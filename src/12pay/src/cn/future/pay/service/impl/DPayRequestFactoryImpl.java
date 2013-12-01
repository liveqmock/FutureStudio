package cn.future.pay.service.impl;

import java.util.ArrayList;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.customer.dto.DCustomer;
import cn.future.customer.pojo.PCustomer;
import cn.future.customer.service.CustomerQueryService;
import cn.future.pay.dto.DPayRequest;
import cn.future.pay.pojo.PPayRequest;
import cn.future.pay.service.DPayRequestFactory;

public class DPayRequestFactoryImpl implements DPayRequestFactory {
	private BaseDao baseDao;
	private DPayRequest dto;
	private List<DPayRequest> dtos;
	private CustomerQueryService customerQueryService;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setCustomerQueryService(CustomerQueryService customerQueryService) {
		this.customerQueryService = customerQueryService;
	}

	@Override
	public DPayRequest getDPayRequest() {
		return this.dto;
	}

	@Override
	public DPayRequestFactory initByPojo(PPayRequest pojo) {
		this.dto = new DPayRequest(pojo);
		return this;
	}

	@Override
	public DPayRequestFactory initById(String id) throws NotFindException {
		PPayRequest pojo = baseDao.findById(PPayRequest.class, id);
		return this.initByPojo(pojo);
	}

	@Override
	public DPayRequestFactory initCustomer() throws NotFindException {
		this.initDcustomer(this.dto);
		return this;
	}

	public void initDcustomer(DPayRequest d) throws NotFindException{
		PCustomer customer = customerQueryService.findCustomerById(d.getCustomerId());
		DCustomer dcus = customerQueryService.transfer(customer);
		d.setCustomer(dcus);
	}
	@Override
	public List<DPayRequest> getDPayRequests() {
		return this.dtos;
	}

	@Override
	public DPayRequestFactory initByPojo(List<PPayRequest> pojos) {
		this.dtos = new ArrayList<DPayRequest>();
		if(null!=pojos){
			for(PPayRequest p : pojos){
				this.dtos.add(new DPayRequest(p));
			}
		}
		return this;
	}

	@Override
	public DPayRequestFactory initById(List<String> ids)
			throws NotFindException {
		List<PPayRequest> list = baseDao.findAll(PPayRequest.class, ids);
		return this.initByPojo(list);
	}

	@Override
	public DPayRequestFactory initCustomers() throws NotFindException {
		if(null!=this.dtos){
			for(DPayRequest d : this.dtos){
				this.initDcustomer(d);
			}
		}
		return this;
	}

}
