package cn.future.oa.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.oa.dto.DRole;

public  abstract interface RoleService {
	/**
	 * 查找全部角色信息
	 * @return
	 */
	public List<DRole> findRoles();

	/**
	 * 删除角色的功能菜单。
	 * 可以直接执行delete * from trolesysfunction where roleid='' and sysfunctionid=''
	 * @param roleid
	 * @param functionid
	 */
	public void deleteRoleFunction(String roleid, String functionid);
	/**
	 * 新增角色的功能菜单，
	 * 首先校验是否存在这个菜单了，如果存在这个菜单了，则不执行操作，
	 * 如果不存在这个菜单，那么执行操作：insert into trolesysfunction values('','');
	 * @param roleid
	 * @param functionid
	 */
	public void addRoleFunction(String roleid, String functionid);
	/**
	 * 删除角色的操作权限
	 * @param roleid
	 * @param operationid
	 */
	public void deleteRoleOperation(String roleid, String operationid);
	/**
	 * 增加角色的操作权限，首先要判断是否已经存在了这个操作权限，如果不存在那么直接插入，
	 * 如果存在了，不执行操作
	 * @param roleid
	 * @param operationid
	 */
	public void addRoleOperation(String roleid, String operationid);

	/**
	 * 用户新添加一个角色, 
	 * 根据用户的id查询这个用户所在的公司，然后查询所有角色的priority，根据最大的priority，+1000，就是新的priority
	 * @param userId
	 * @param roleName
	 * @param comments
	 */
	public DRole addRole(String userId, String roleName, String comments)  throws NotFindException ;
	/**
	 * 更新一个角色信息。
	 * @param id
	 * @param data1
	 * @param data2
	 * @throws NotFindException
	 */
	public DRole updateRole(String id, String roleName, String comments) throws NotFindException;
	
	/**
	 * 通过用户的id查找用户的角色信息
	 * @param id
	 * @return
	 * @throws NotFindException 
	 */
	public List<DRole> findAccountRoles(String id) throws NotFindException;
}
