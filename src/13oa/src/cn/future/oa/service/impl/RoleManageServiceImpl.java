package cn.future.oa.service.impl;

import java.util.HashSet;
import java.util.Set;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.pojo.Role;
import cn.future.oa.service.RoleManageService;

public class RoleManageServiceImpl implements RoleManageService {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public void addAccountRole(String accountId, String roleId) throws NotFindException {
		PAccount user = baseDao.findById(PAccount.class, accountId);
		Role role = baseDao.findById(Role.class, roleId);
		Set<Role> roles = user.getRoles();
		if(roles==null){
			roles=new HashSet<Role>();
		}
		int has=0;
		if(roles.size()>0){
			for(Role r : roles){
				if(r.getId().equals(roleId)){
					has=1;
				}
			}
		}
		if(has==0){
			roles.add(role);
		}
		user.setRoles(roles);
		baseDao.update(user);
	}

	@Override
	public void removeAccountRole(String accountId, String roleId)
			throws NotFindException {
		PAccount user = baseDao.findById(PAccount.class, accountId);
		Set<Role> roles = user.getRoles();
		Set<Role> newRoles = new HashSet<Role>();
		if(roles!=null && roles.size()>0){
			for(Role r : roles){
				if(!r.getId().equals(roleId)){
					newRoles.add(r);
				}
			}
		}
		user.setRoles(newRoles);
		baseDao.update(user);
	}

}
