package cn.future.finance.service.impl;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.finance.pojo.PCreditRequest;
import cn.future.finance.service.CreditRequestManageService;

public class CreditRequestManageServiceImpl implements
		CreditRequestManageService {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public PCreditRequest addPCreditRequest(PCreditRequest pojo) {
		baseDao.save(pojo);
		return pojo;
	}

	@Override
	public PCreditRequest updatePCreditRequestStatus(String id, int status) throws NotFindException {
		PCreditRequest p = baseDao.findById(PCreditRequest.class, id);
		p.setStatusCode(status);
		baseDao.update(p);
		return p;
	}

	@Override
	public PCreditRequest addCreditRequestComments(String id, String comments,
			String approvalComments) throws NotFindException {
		PCreditRequest p = baseDao.findById(PCreditRequest.class, id);
		p.setComments(comments);
		p.setApprovalComments(approvalComments);
		baseDao.update(p);
		return p;
	}

}
