package cn.future.oa.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.oa.dto.DSysFunction;
import cn.future.oa.pojo.SysFunction;
import cn.future.oa.pojo.SysOperation;

public abstract interface PrivilegeService {
	/**
	 * 通过id查询查询用户有的系统功能
	 * 找到id的账户，然后获取全部角色信息，通过角色信息获取功能信息。 通过findAllNavSysFunction方法获取可用的
	 * @param id
	 * @return
	 * @throws NotFindException 
	 */
	public List<DSysFunction> findSysFunctionByUserId(String id) throws NotFindException;

	/**
	 * 查询所有的导航菜单信息, 搜索从cacheservice查找Nav:AllActive，如果不存在，则查询，查询成功后，保存到缓存 .statusCode>0
	 * @return
	 */
	public List<SysFunction> findAllActiveNavSysFunction();
	/**
	 * 查询所有的导航菜单信息, 搜索从cacheservice查找Nav:All，如果不存在，则查询，查询成功后，保存到缓存 
	 * @return
	 */
	public List<SysFunction> findAllNavSysFunction();
	/**
	 * 通过用户id查询操作权限信息。
	 * 通过id找到role信息，然后遍历role信息，将操作权限放入数组中
	 * @param id
	 * @return
	 * @throws NotFindException 
	 */
	public List<SysOperation> findSysOperationByUserId(String id) throws NotFindException;
	
	/**
	 * 根据角色的id，查找角色拥有的所有所有菜单：Menu类型
	 * @param roleid
	 * @return
	 * @throws NotFindException 
	 */
	public List<DSysFunction> findSysFunctionMenuByRoleId(String roleid) throws NotFindException;
	/**
	 * 根据角色的id，超找角色拥有的操作权限
	 * @param roleid
	 * @return
	 * @throws NotFindException 
	 */
	public List<SysOperation> findSysOperationByRoleId(String roleid) throws NotFindException;
	/**
	 * 草诏所有，类型为Menu的菜单
	 * @return
	 */
	public List<DSysFunction> findSysfunctionMenuAll();
	/**
	 * 查找所有的操作权限数据
	 * @return
	 */
	public List<SysOperation> findSysOperationAll();
}
