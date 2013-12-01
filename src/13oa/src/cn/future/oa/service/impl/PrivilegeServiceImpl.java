package cn.future.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.service.CacheService;
import cn.future.oa.dto.DSysFunction;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.pojo.Role;
import cn.future.oa.pojo.SysFunction;
import cn.future.oa.pojo.SysOperation;
import cn.future.oa.service.PrivilegeService;

public class PrivilegeServiceImpl implements PrivilegeService{
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public List<DSysFunction> findSysFunctionByUserId(String id) throws NotFindException {
		//加入缓存机制
		if(findAccountSysFunctionFromCache(id)!=null){
			return findAccountSysFunctionFromCache(id);
		}
		PAccount account = baseDao.findById(PAccount.class, id);
		Set<Role> roles=account.getRoles();
		HashMap<String,DSysFunction> hasMenu = new HashMap<String,DSysFunction>();
		//将所有的SysFunction放在HasMenu里面
		if(roles!=null){
			for(Role i : roles){
				Set<SysFunction> roleSysFunctions = i.getSysFunctions();
				if(roleSysFunctions!=null){
					for(SysFunction s:roleSysFunctions){
						hasMenu.put(s.getId(), new DSysFunction(s,false));
					}
				}
			}
		}
		List<SysFunction> allnav = findAllActiveNavSysFunction();
		List<DSysFunction> preMenu = new ArrayList<DSysFunction>();
		//根据实际的可用allnav判断，用户是否有这个权限，有则添加到preMenu。
		for(SysFunction s:allnav){
			DSysFunction sclone = new DSysFunction(s,false);  //没有children的
			
			Set<SysFunction> menuItems = s.getChildren(); //当前Nav下的所有菜单
			List<DSysFunction> hasItems = new ArrayList<DSysFunction>(); //实际有的菜单
			if(menuItems!=null){
				for(SysFunction it : menuItems){
					if(hasMenu.get(it.getId())!=null && it.getStatusCode()>0){
						hasItems.add(new DSysFunction(it,false));
					}
				}
			}
			sclone.setChildren(hasItems);
			preMenu.add(sclone);
		}
		//去掉preMenu里面的空菜单
		List<DSysFunction> menu = new ArrayList<DSysFunction>();
		for(DSysFunction s : preMenu){
			if(s.getChildren().size()>0){
				menu.add(s);
			}
		}
		cacheAccountSysFunction(id, menu);
		return menu;
	}

	@SuppressWarnings("unchecked")//已经添加缓存机制
	@Override
	public List<SysFunction> findAllActiveNavSysFunction() {
		//加入缓存机制
		if(CacheService.find("Nav:AllActive")!=null){
			return (List<SysFunction>)CacheService.find("Nav:AllActive");
		}
		String hql="from SysFunction as a where a.funcType='Nav' and a.statusCode>0";
		int page=1;
		int size=999;
		HashMap<String,Object> params = new HashMap<String,Object>();
		List<SysFunction> navFunc = baseDao.findAll(SysFunction.class, hql, page, size, params);
		CacheService.add("Nav:AllActive", navFunc); //刷新缓存
		return navFunc;
	}
	@SuppressWarnings("unchecked")//已经添加缓存机制
	@Override
	public List<SysFunction> findAllNavSysFunction() {
		//加入缓存机制
				if(CacheService.find("Nav:All")!=null){
					return (List<SysFunction>)CacheService.find("Nav:All");
				}
				String hql="from cn.future.oa.pojo.SysFunction as a where a.funcType='Nav'";
				int page=1;
				int size=999;
				HashMap<String,Object> params = new HashMap<String,Object>();
				List<SysFunction> navFunc = baseDao.findAll(SysFunction.class, hql, page, size, params);
				CacheService.add("Nav:All", navFunc); //刷新缓存
				return navFunc;
	}
	@Override
	public List<SysOperation> findSysOperationByUserId(String id) throws NotFindException {
		List<SysOperation> privileges = new ArrayList<SysOperation>();
		if(findAccountSysOperationFromCache(id)!=null){
			return findAccountSysOperationFromCache(id);
		}
		PAccount account = baseDao.findById(PAccount.class, id);
		Set<Role> setRole = account.getRoles();
		
		HashMap<String,SysOperation> allp = new HashMap<String,SysOperation>();
		if(setRole!=null){
			//将所有的操作对象放如数组里面
			for(Role i : setRole){
				Set<SysOperation> setS = i.getSysOperations();
				if(setS!=null){
					for(SysOperation si : setS){
						allp.put(si.getId(), si);
					}
				}
				
			}
			//把hashmap转为list
			for(String key : allp.keySet()){
				privileges.add(allp.get(key));
			}
		}
		cacheAccountSysOperation(id, privileges);
		return privileges;
	}
	
	/**
	 * 将用户的操作权限信息进行缓存
	 * @param userid , 保存的key为userid_SysOperations
	 * @param privileges
	 */
	public void cacheAccountSysOperation(String userid,List<SysOperation> privileges){
		CacheService.add(userid+"_SysOperations", privileges);
	}
	/**
	 * 通过id获取用户的权限信息
	 * @param userid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<SysOperation> findAccountSysOperationFromCache(String userid){
		if(CacheService.find(userid+"_SysOperations")!=null){
			return (List<SysOperation>)CacheService.find(userid+"_SysOperations");
		}else{
			return null;
		}
		
	}
	/**
	 * 从缓存里面取用户菜单权限信息
	 * @param userid
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<DSysFunction> findAccountSysFunctionFromCache(String userid){
		if(CacheService.find(userid+"_SysFunctions")!=null){
			return (List<DSysFunction>)CacheService.find(userid+"_SysFunctions");
		}else{
			return null;
		}
	}

	/**
	 * 将用户的菜单信息缓存起来
	 * @param userid
	 * @param menus
	 */
	public void cacheAccountSysFunction(String userid,List<DSysFunction> menus){
		CacheService.add(userid+"_SysFunctions", menus);
	}

	@Override
	public List<DSysFunction> findSysFunctionMenuByRoleId(String roleid) throws NotFindException {
		Role role = baseDao.findById(Role.class, roleid);
		Set<SysFunction> funset = role.getSysFunctions();
		List<DSysFunction> list = new ArrayList<DSysFunction>();
		if(funset!=null){
			for(SysFunction s : funset){
				if("Menu".equals(s.getFuncType())){
					list.add(new DSysFunction(s, false));
				}
			}
		}
		return list;
 	}
	@Override
	public List<SysOperation> findSysOperationByRoleId(String roleid) throws NotFindException {
		Role role = baseDao.findById(Role.class,roleid);
		Set<SysOperation> operset = role.getSysOperations();
		List<SysOperation> list = new ArrayList<SysOperation>();
		if(operset!=null){
			for(SysOperation s : operset){
				list.add(s);
			}
		}
		return list;
	}
	@Override
	public List<DSysFunction> findSysfunctionMenuAll() {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("funcType", "Menu");
		List<DSysFunction> list = new ArrayList<DSysFunction>();
		List<SysFunction> sysList = baseDao.findAll(SysFunction.class, "from cn.future.oa.pojo.SysFunction as a where a.funcType=:funcType", 1, 9999, params);
		if(sysList!=null){
			for(SysFunction s:sysList){
				list.add(new DSysFunction(s,false));
			}
		}
		return list; 
	}
	@Override
	public List<SysOperation> findSysOperationAll() {
		return baseDao.findAll(SysOperation.class, "from cn.future.oa.pojo.SysOperation", 1, 9999, null);
	}
}
