package cn.future.finance.service.impl;

import java.util.Date;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.finance.exception.FinanceProjectStatusCodeError;
import cn.future.finance.pojo.PFinanceProject;
import cn.future.finance.service.FinanceProjectManageService;
import cn.future.finance.util.FinanceCodeUtil;

public class FinanceProjectManageServiceImpl implements
		FinanceProjectManageService {
	private BaseDao baseDao;
	
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public PFinanceProject addFinanceProject(PFinanceProject pojo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void refreshFinanceProjectNumberStatus(String id) throws NotFindException {
		//先处理项目时间
		PFinanceProject pojo = baseDao.findById(PFinanceProject.class, id);
		Date currentDate = new Date();
		long currentDateLong = currentDate.getTime();
		Date deadDate = pojo.getDeadDate();
		if(deadDate!=null){
			if(currentDateLong > deadDate.getTime()){
				pojo.setStatusCode(FinanceCodeUtil.BID_CLOSED);
				pojo.setClosedBy("5");
				pojo.setClosedId("0");
				
				return ;
			}
		}
		if(pojo.getAlreadyNumber() == pojo.getFinanceNumber()){
			pojo.setStatusCode(FinanceCodeUtil.BID_SUCCESS);
			pojo.setClosedBy("10");
			pojo.setClosedId("0");
			
			return ;
		}
	}

	@Override
	public void updateFinanceProjectStatus(String id, int statusCode) throws NotFindException, FinanceProjectStatusCodeError {
		PFinanceProject p = baseDao.findById(PFinanceProject.class, id);
		FinanceCodeUtil.getStatusName(statusCode);
		p.setStatusCode(statusCode);
		baseDao.save(p);
	}

}
