package cn.future.finance.service.impl;

import org.hibernate.StaleObjectStateException;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.customer.dto.DCustomer;
import cn.future.finance.exception.FinanceProjectOverBidException;
import cn.future.finance.exception.FinanceProjectStatusException;
import cn.future.finance.exception.FinanceReturnNumberException;
import cn.future.finance.pojo.PFinanceProject;
import cn.future.finance.pojo.PFinanceProjectBidDetail;
import cn.future.finance.service.FinanceProjectBidManageService;
import cn.future.finance.util.FinanceCodeUtil;
import cn.future.pay.exception.PayBusinessIdUnuquieException;
import cn.future.pay.exception.PayOverSpendException;
import cn.future.pay.service.CashAccountManageService;

public class FinanceProjectBidManageServiceImpl implements FinanceProjectBidManageService {
	private BaseDao baseDao;
	private CashAccountManageService cashAccountManageService;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void setCashAccountManageService(
			CashAccountManageService cashAccountManageService) {
		this.cashAccountManageService = cashAccountManageService;
	}

	/* (non-Javadoc)
	 * @see cn.future.finance.service.impl.PFinanceProjectBidManageService#addPFinanceProjectBidDetail(java.lang.String, java.lang.String, java.lang.String, int, int)
	 */
	@Override
	public void addCustomerPFinanceProjectBidDetail(
			String projectId,
			DCustomer customer, long cashNumber, int statusCode) throws PayOverSpendException, PayBusinessIdUnuquieException, SystemBusyException, NotFindException, FinanceProjectOverBidException, FinanceProjectStatusException{
		PFinanceProject project = baseDao.findById(PFinanceProject.class, projectId);
		if(project.getStatusCode() != FinanceCodeUtil.BIDDING){
			FinanceProjectStatusException e = new FinanceProjectStatusException("项目已经停止投标");
			throw e;
		}
		long canBidCash = project.getFinanceNumber() - project.getAlreadyNumber();
		if(cashNumber > canBidCash){
			FinanceProjectOverBidException e = new FinanceProjectOverBidException("投标金额过大");
			throw e;
		}
		
		PFinanceProjectBidDetail p = new PFinanceProjectBidDetail(projectId,customer.getId(),customer.getCnName(),customer.getNickName(),customer.getAccountName(),cashNumber,statusCode);
		cashAccountManageService.createCashAccountDetailOut(customer.getId(), p.getId(), cashNumber, "理财投标", "项目编号："+project.getFlowId(), "", "dgh-finance-project-bid");
		
		project.setAlreadyNumber(project.getAlreadyNumber() + cashNumber);
		project.setBidTimes(project.getBidTimes()+1);
		try{
			baseDao.save(p);
			//满标，关闭项目
			if(cashNumber == canBidCash){
				project.setStatusCode(FinanceCodeUtil.BID_SUCCESS);
				project.setClosedBy("10");
				project.setClosedId("0");
			}
			baseDao.update(project);
		}catch(StaleObjectStateException e){
			SystemBusyException psbe = new SystemBusyException("投标系统忙，请稍后再试",e);
			throw psbe;
		}
	}

	@Override
	public void addBidDetailsAlreadyReturn(String id, long returnNumber) throws NotFindException, FinanceReturnNumberException {
		PFinanceProjectBidDetail pojo = baseDao.findById(PFinanceProjectBidDetail.class, id);
		long bid = pojo.getCashNumber();
		long already = pojo.getAlreadyReturn();
		long newAlready = already+returnNumber;
		if(newAlready>bid){
			FinanceReturnNumberException e = new FinanceReturnNumberException("返款金额超过了投标金额");
			throw e;
		}
		pojo.setAlreadyReturn(newAlready);
		baseDao.save(pojo);
	}
}
