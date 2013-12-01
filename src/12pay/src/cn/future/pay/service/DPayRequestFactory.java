package cn.future.pay.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.pay.dto.DPayRequest;
import cn.future.pay.pojo.PPayRequest;

public abstract interface DPayRequestFactory {

	public DPayRequest getDPayRequest();
	
	public DPayRequestFactory initByPojo(PPayRequest pojo);
	
	public DPayRequestFactory initById(String id) throws NotFindException;
	
	public DPayRequestFactory initCustomer() throws NotFindException;
	

	public List<DPayRequest> getDPayRequests();
	
	public DPayRequestFactory initByPojo(List<PPayRequest> pojos);
	
	public DPayRequestFactory initById(List<String> ids) throws NotFindException;
	
	public DPayRequestFactory initCustomers() throws NotFindException;
}
