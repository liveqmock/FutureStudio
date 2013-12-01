package cn.future.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.oa.dto.DRole;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.pojo.Role;
import cn.future.oa.service.RoleService;
import cn.future.util.StringUtil;

public class RoleServiceImpl implements RoleService {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<DRole> findRoles() {
		HashMap<String,Object> params=new HashMap<String,Object>();
		List<Role> roles = baseDao.findAll(Role.class, "from cn.future.oa.pojo.Role ", 1, 99999, params);
		List<DRole> list = new ArrayList<DRole>();
		if(roles!=null){
			for(Role r:roles){
				list.add(new DRole(r,false));
			}
		}
		return list;
	}

	@Override
	public void deleteRoleFunction(String roleid, String functionid) {
		baseDao.deleteSql("delete from trolesysfunction where roleid='"+roleid+"' and sysfunctionid='"+functionid+"'");		
	}
	@Override
	public void addRoleFunction(String id1, String id2) {
		int count=baseDao.findCount("select count(*) from trolesysfunction where roleid='"+id1+"' and sysfunctionid='"+id2+"'");
		if(count<1){
			baseDao.updateSql("insert into trolesysfunction(roleid,sysfunctionid) values('"+id1+"','"+id2+"')");
		}
	}
	@Override
	public void deleteRoleOperation(String roleid, String operationid) {
		baseDao.deleteSql("delete from trolesysoperation where roleid='"+roleid+"' and sysoperationid='"+operationid+"'");	
		
	}
	@Override
	public void addRoleOperation(String roleid, String operationid) {
		int count=baseDao.findCount("select count(*) from trolesysoperation where roleid='"+roleid+"' and sysoperationid='"+operationid+"'");
		if(count<1){
			baseDao.updateSql("insert into trolesysoperation(roleid,sysoperationid) values('"+roleid+"','"+operationid+"')");
		}
	}
	@Override
	public DRole addRole(String userId, String roleName, String comments) throws NotFindException {
		PAccount a=baseDao.findById(PAccount.class, userId);
		if(a!=null){
			int maxPriority = baseDao.findMaxPriority("trole");
			Role r = new Role();
			r.setComments(comments);
			r.setId(StringUtil.getUuid());
			r.setName(roleName);
			r.setPriority(maxPriority+1000);
			baseDao.saveOrUpdate(r);
			return new DRole(r,false);
		}else{
			NotFindException n = new NotFindException("保存失败");
			throw n;
		}
		
	}
	@Override
	public DRole updateRole(String id, String roleName, String comments)
			throws NotFindException {
		Role r=baseDao.findById(Role.class, id);
		if(r!=null){
			r.setName(roleName);
			r.setComments(comments);
			return new DRole(r,false);
		}else{
			NotFindException e = new NotFindException("更新角色失败");
			throw e;
		}
	}
	@Override
	public List<DRole> findAccountRoles(String id) throws NotFindException {
		PAccount a=baseDao.findById(PAccount.class,id);
		Set<Role> setRole = a.getRoles();
		List<DRole> list =new ArrayList<DRole>();
		if(setRole!=null){
			for(Role r:setRole){
				list.add(new DRole(r,false));
			}
		}
		return list;
	}
}
