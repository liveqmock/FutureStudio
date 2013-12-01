package cn.future.finance.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.*;

import cn.future.customer.dto.DCustomer;
import cn.future.customer.dto.DCustomerCredit;
import cn.future.customer.pojo.PCustomer;
import cn.future.customer.service.CustomerQueryService;
import cn.future.common.exception.NotFindException;
import cn.future.finance.dto.DFinanceProject;
import cn.future.finance.dto.DFinanceProjectBidDetail;
import cn.future.finance.pojo.PFinanceProject;
import cn.future.finance.pojo.PFinanceProjectBidDetail;
import cn.future.finance.service.DFinanceProjectFactory;
import cn.future.finance.service.FinanceProjectBidQueryService;
import cn.future.finance.service.ProjectCreditQueryService;
import cn.future.oa.dto.DAccount;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.service.AccountService;

public class DFinanceProjectFactoryImpl implements DFinanceProjectFactory{
	private DFinanceProject dto;
	private PFinanceProject po;
	private CustomerQueryService customerQueryService;
	private AccountService accountService;
	private FinanceProjectBidQueryService financeProjectBidQueryService;
	private ProjectCreditQueryService projectCreditQueryService;
	
	private final Logger logger = LoggerFactory.getLogger(DFinanceProjectFactoryImpl.class);


	public void setProjectCreditQueryService(
			ProjectCreditQueryService projectCreditQueryService) {
		this.projectCreditQueryService = projectCreditQueryService;
	}

	public void setFinanceProjectBidQueryService(
			FinanceProjectBidQueryService financeProjectBidQueryService) {
		this.financeProjectBidQueryService = financeProjectBidQueryService;
	}

	public void setCustomerQueryService(CustomerQueryService customerQueryService) {
		this.customerQueryService = customerQueryService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	public DFinanceProjectFactoryImpl(){
		dto = new DFinanceProject();
	}

	@Override
	public DFinanceProject getDFinanceProject() {
		return dto;
	}
	@Override
	public DFinanceProjectFactory initByPojo(PFinanceProject pojo) {
		this.po = pojo;
		this.dto = new DFinanceProject(pojo);
		return null;
	}
	@Override
	public DFinanceProjectFactory initCustomerInfo() {
		try{
			String id =  po.getCustomerId();
			if(id!=null){
				PCustomer pc = customerQueryService.findCustomerById(id);
				DCustomer dc = customerQueryService.transfer(pc);
				this.dto.setCustomer(dc);
			}
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return this;
	}
	@Override
	public DFinanceProjectFactory initUserInfo() {
		try {
			String id = this.po.getUserId();
			if(id!=null){
				PAccount pa = accountService.findAccountById(id);
				DAccount da = accountService.transferPAccount(pa, 1);
				this.dto.setAccount(da);
			}
		} catch (NotFindException e) {
			logger.error(e.getMessage());
		}
		return this;
	}
	@Override
	public DFinanceProjectFactory initBids() {
		this.initBids(false);
		return this;
	}
	@Override
	public DFinanceProjectFactory initSecurityBids() {
		this.initBids(true);
		return this;
	}
	public DFinanceProjectFactory initBids(Boolean security){
		List<PFinanceProjectBidDetail> list = new ArrayList<PFinanceProjectBidDetail>();
		try{
			String id = this.dto.getId();
			financeProjectBidQueryService.findFinanceProjectBidDetails(list, id, 1, 20);
			List<DFinanceProjectBidDetail> listd = financeProjectBidQueryService.transfer(list,security);
			this.dto.setListBids(listd);
		}catch(Exception e){
			logger.error(e.getMessage());
		}
		return this;
	}
	@Override
	public DFinanceProjectFactory initCredits() {
		List<DCustomerCredit> credits = projectCreditQueryService.findProjectCreditByProjectId(this.dto.getId());
		this.dto.setCredits(credits);
		return this;
	}
	
}
