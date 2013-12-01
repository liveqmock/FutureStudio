package cn.future.oa.service.impl;

import java.util.HashMap;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.NotUniqueException;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.service.AccountQueryService;

public class AccountQueryServiceImpl implements AccountQueryService {
	private BaseDao baseDao;
	@Override
	public PAccount findAccountByEmployId(String id) throws NotFindException, NotUniqueException {
		String hql = "from cn.future.oa.pojo.PAccount as p where p.employeeId=:employeeId";
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("employeeId", id);
		return baseDao.findUnique(PAccount.class, hql, params);
	}
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
