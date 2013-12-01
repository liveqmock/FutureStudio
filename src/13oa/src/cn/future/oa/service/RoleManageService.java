package cn.future.oa.service;

import cn.future.common.exception.NotFindException;

public abstract interface RoleManageService {
	/**
	 * 为账户添加一个角色
	 * @param accountId
	 * @param roleId
	 * @throws NotFindException 
	 */
	public void addAccountRole(String accountId, String roleId) throws NotFindException;
	/**
	 * 删除账户的角色
	 * @param accountId
	 * @param roleId
	 * @throws NotFindException
	 */
	public void removeAccountRole(String accountId, String roleId) throws NotFindException;
}
