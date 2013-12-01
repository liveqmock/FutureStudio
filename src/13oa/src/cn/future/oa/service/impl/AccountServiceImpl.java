package cn.future.oa.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.pojo.ComparatorPriority;
import cn.future.common.service.CacheService;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.NotUniqueException;
import cn.future.oa.dto.DAccount;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.pojo.PAccountType;
import cn.future.oa.pojo.PDepartment;
import cn.future.oa.service.AccountService;
import cn.future.user.exception.UserNotAllowedAccess;
import cn.future.user.exception.UserPasswordUnmatchException;
import cn.future.util.IntUtil;
import cn.future.util.StringUtil;

public class AccountServiceImpl implements AccountService {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<PAccount> findAccountByPositionId(String id,boolean isAll)throws NotFindException{
		List<PAccount> list =new ArrayList<PAccount>();
		PDepartment department = baseDao.findById(PDepartment.class, id);
		if(department.getLevel()==100){
			//查找前首先确认是否是对应的level
			HashMap<String,Object> params = new HashMap<String,Object>();
			params.put("p1", department);
			int statusCode=0;
			if(isAll){
				statusCode=-10;
			}else{
				statusCode=-1;
			}
			params.put("statusCode", statusCode);
			list = baseDao.findAll(PAccount.class, "from cn.future.oa.pojo.PAccount as a where a.department=:p1 and a.statusCode>:statusCode", 1, 9999, params);
//			if(list!=null){
//				for(Account a : list){
//					listp.add(new PAccount(a));
//				}
//			}
			Collections.sort(list, new ComparatorPriority());
		}
		return list;
	}
	@Override
	public PAccount signInService(String account, String password) throws NotFindException, UserPasswordUnmatchException, UserNotAllowedAccess, NotUniqueException {
		String hql="from cn.future.oa.pojo.PAccount as a where a.account=:account";
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("account", account);
		PAccount a = baseDao.findUnique(PAccount.class, hql, params);
		if(a==null){
			NotFindException e = new NotFindException("用户登陆:"+account+"不存在");
			throw e;
		}else{
			if(a.getPassword().equals(StringUtil.sha512Encrypt(password))){
				if(a.getStatusCode()<IntUtil.STATUS_ACTIVE){
					UserNotAllowedAccess e = new UserNotAllowedAccess("");
					throw e;
				}else{
					return a;
				}
			}else{
				UserPasswordUnmatchException e = new UserPasswordUnmatchException("用户登陆:"+account+"密码不匹配");
				throw e;
			}
		}
	}

	@Override
	public void clearUserCache(String userid){
		CacheService.add(userid+"_SysFunctions", null);
		CacheService.add(userid+"_SysOperations", null);
	}
	
	@Override
	public PAccount updateAccount(String userId, String id, String name,
			String employeeId, String account, String mobile, String email,
			String comments, String statusCode, String statusName) throws NotFindException {
		PAccount a = baseDao.findById(PAccount.class, id);
		a.setName(name);
		a.setEmployeeId(employeeId);
		a.setMobile(mobile);
		a.setEmail(email);
		a.setComments(comments);
		a.setStatusCode(Integer.valueOf(statusCode));
		a.setStatusName(statusName);
		baseDao.update(a);
		return a;
	}
	@Override
	public PAccount addAccount(String userid, String positionId, PAccount account) throws NotFindException {
		PDepartment d = baseDao.findById(PDepartment.class, positionId);
		PAccount user = baseDao.findById(PAccount.class, userid);
		if(d!=null && user!=null){
			if(d.getLevel()==100){
				account.setDepartment(d);
				baseDao.save(account);
			}else{
				NotFindException e = new NotFindException("ID:"+positionId+"不是职位，无法添加用户");
				throw e;
			}
		}else{
			NotFindException e = new NotFindException("ID:"+positionId+"的职位，未找到");
			throw e;
		}
		
		
		return account;
		
	}
	@Override
	public int accountCount(String account) {
		return baseDao.findCount("select count(*) from taccount where account='"+account+"'"); 
	}
	@Override
	public List<PAccountType> findAllAccountType() {
		return baseDao.findAll(PAccountType.class, "from cn.future.oa.pojo.PAccountType", 1, 99, null);
	}
	@Override
	public int employeeIdCount(String employeeId) {
		return baseDao.findCount("select count(*) from taccount where employeeid='"+employeeId+"'");
	}
	@Override
	public PAccount findAccountById(String id) throws NotFindException {
		PAccount add = baseDao.findById(PAccount.class, id);
		if(add==null){
			NotFindException e = new NotFindException(id+"的用户，未找到");
			throw e;
		}
		return add;
	}
	@Override
	public void updateAccount(PAccount add) {
		baseDao.update(add);
		
	}

	@Override
	public void addAccountType(String name, String comments, String priority) {
		String id=StringUtil.getUuid();
		PAccountType at = new PAccountType();
		at.setId(id);
		at.setName(name);
		at.setComments(comments);
		at.setPriority(Integer.parseInt(priority));
		baseDao.save(at);
		
	}

	@Override
	public void updateAccountType(String id, String name, String comments,
			String priority) throws NotFindException {
		PAccountType at = baseDao.findById(PAccountType.class, id);
		at.setComments(comments);
		at.setName(name);
		at.setPriority(Integer.parseInt(priority));
		baseDao.update(at);
	}

	@Override
	public void updateAccountTypePriority(String id1, String id2) throws NotFindException {
		PAccountType at1=baseDao.findById(PAccountType.class, id1);
		PAccountType at2=baseDao.findById(PAccountType.class,id2);
		at1.setPriority(at2.getPriority());
		at2.setPriority(at1.getPriority());
		baseDao.update(at1);
		baseDao.update(at2);
	}

	@Override
	public List<PAccount> findPositionsAccount(String[] positions){
		List<PAccount> list = new ArrayList<PAccount>();
		if(positions!=null){
			for(String s:positions){
				HashMap<String,Object> params=new HashMap<String,Object>();
				params.put("id", s);
				List<PAccount> lists=baseDao.findAll(PAccount.class, 
						"from cn.future.oa.pojo.PAccount as a where a.department.id=:id and a.statusCode>0", 
						1, 999, params);
				if(lists!=null){
					list.addAll(lists);
				}
			}
		}
		return list;
	}

	@Override
	public DAccount transferPAccount(PAccount a, int transMode) {
		DAccount p;
		if(transMode==0){
			p= new DAccount(a);
		}else{
			p=new DAccount(a,transMode);
		}
		return p;
	}

	@Override
	public List<DAccount> transferPAccount(List<PAccount> list, int transMode) {
		List<DAccount> p=new ArrayList<DAccount>();
		if(list!=null){
			for(PAccount a:list){
				DAccount pi;
				if(transMode==0){
					pi= new DAccount(a);
				}else{
					pi=new DAccount(a,transMode);
				}
				p.add(pi);
			}
		}
		return p;
	}

	@Override
	public void updatePassword(String oldPassword, String newPassword,String userId) throws UserPasswordUnmatchException, NotFindException {
		// TODO 以后需要添加，更改密码记录，但是现在写一个日志就可以了
		PAccount a=baseDao.findById(PAccount.class, userId);
		if(a.getPassword().equals(StringUtil.sha512Encrypt(oldPassword))){
			a.setPassword(StringUtil.sha512Encrypt(newPassword));
			baseDao.update(a);
		}else{
			UserPasswordUnmatchException e = new UserPasswordUnmatchException("旧密码不匹配");
			throw e;
		}
	}
}
