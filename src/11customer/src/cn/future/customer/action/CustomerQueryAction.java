package cn.future.customer.action;

import java.util.ArrayList;
import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.NotUniqueException;
import cn.future.customer.dto.DCustomer;
import cn.future.customer.dto.DCustomerCredit;
import cn.future.customer.dto.DCustomerDetailInfo;
import cn.future.customer.exception.DCustomerFactoryNotFindDtoException;
import cn.future.customer.pojo.PCustomer;
import cn.future.customer.service.CustomerQueryService;
import cn.future.customer.service.DCustomerFactory;
/**
 * 获取客户信息
 */
public class CustomerQueryAction extends BaseAction{

	private static final long serialVersionUID = 5447527059608964419L;
	private CustomerQueryService customerQueryService;
	private DCustomerFactory dCustomerFactory;
	private String query=null;
	//set
	private int page=1;
	private int pageSize = 20;
	private String id;
	//set -- get
	private int count;
	private List<DCustomer> customers;
	private List<DCustomerCredit> customerCredits;
	private DCustomer customer;
	private DCustomerDetailInfo customerDetailInfo;
	/**
	 * 列出所有的客户
	 */
	public String list(){
		List<PCustomer> list = new ArrayList<PCustomer>();
		count = customerQueryService.findAllCustomer(list, query, page, pageSize);
		customers = customerQueryService.transfer(list);
		return SUCCESS;
	}
	/**
	 * 列出客户的 扩展信息、信用
	 */
	public String detail(){
		try {
			PCustomer pojo = customerQueryService.findCustomerById(id);
			dCustomerFactory.initByPojo(pojo);
			dCustomerFactory.initCredit();
			dCustomerFactory.initDetailInfo();
			dCustomerFactory.initAdviser();
			this.customer = dCustomerFactory.getDCustomer();
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DCustomerFactoryNotFindDtoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotUniqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setCustomerQueryService(CustomerQueryService customerQueryService) {
		this.customerQueryService = customerQueryService;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getCount() {
		return count;
	}

	public void setId(String id) {
		this.id = id;
	}
	public List<DCustomer> getCustomers() {
		return customers;
	}
	public DCustomer getCustomer() {
		return customer;
	}
	public List<DCustomerCredit> getCustomerCredits() {
		return customerCredits;
	}
	public DCustomerDetailInfo getCustomerDetailInfo() {
		return customerDetailInfo;
	}
	public void setdCustomerFactory(DCustomerFactory dCustomerFactory) {
		this.dCustomerFactory = dCustomerFactory;
	}
	public int getPage() {
		return page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	
	
}
