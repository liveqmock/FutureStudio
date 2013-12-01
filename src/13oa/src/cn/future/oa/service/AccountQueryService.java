package cn.future.oa.service;

import cn.future.common.exception.NotFindException;
import cn.future.common.exception.NotUniqueException;
import cn.future.oa.pojo.PAccount;

public abstract interface AccountQueryService {
	/**
	 * 通过员工ID查询员工
	 * @param id
	 * @return
	 * @throws NotUniqueException 
	 * @throws NotFindException 
	 */
	public PAccount findAccountByEmployId(String id) throws NotFindException, NotUniqueException;
}
