package cn.future.customer.service.impl;

import cn.future.customer.dto.DCustomer;
import cn.future.customer.pojo.PCustomer;
import cn.future.customer.service.CustomerQueryService;
import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.NotUniqueException;
import cn.future.user.exception.UserNotAllowedAccess;
import cn.future.user.exception.UserPasswordUnmatchException;
import cn.future.util.StringUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CustomerQueryServiceImpl implements CustomerQueryService {
    private BaseDao baseDao;
    @Override
    public PCustomer customerSignInService(String username, String password) throws NotFindException, NotUniqueException, UserNotAllowedAccess, UserPasswordUnmatchException {
        String passwordSha = StringUtil.sha512Encrypt(password);
    	PCustomer customer = this.findCustomerByName(username);
        if(customer.getStatusCode()<1){
        	UserNotAllowedAccess e = new UserNotAllowedAccess("用户账户不可用，请于管理员联系");
        	throw e;
        }
        /**
         * 密码为空，说明是第三方账户。
         * 第三方账户不能直接帐号密码登陆
         */
        if(!StringUtil.notEmpty(customer.getAccountPassword())){
        	UserNotAllowedAccess e = new UserNotAllowedAccess("请使用第三方账户登陆");
        	throw e;
        }
        if(!customer.getAccountPassword().equals(passwordSha)){
        	UserPasswordUnmatchException e = new UserPasswordUnmatchException("密码错误");
        	throw e;
        }
        return customer;
    }
    @Override
    public PCustomer findCustomerByName(String userName) throws NotFindException, NotUniqueException{
    	List<PCustomer> listCustomer=this.findCustomersByName(userName);
        if(listCustomer==null || listCustomer.size()<1){
        	NotFindException e = new NotFindException(userName+"用户不存在");
        	throw e;
        }
        if(listCustomer.size()>1){
        	NotUniqueException e = new NotUniqueException("用户不为唯一");
        	throw e;
        }
        return listCustomer.get(0);
    }
    public void setBaseDao(BaseDao baseDao) {
        this.baseDao = baseDao;
    }
	@Override
	public List<PCustomer> findCustomersByName(String userName) {
		String hql="from cn.future.customer.pojo.PCustomer as p where p.accountName=:username";
        HashMap<String,Object> params=new HashMap<String,Object>();
        params.put("username",userName);
        Integer i1=new Integer(1);
        Integer i2=new Integer(10);
        List<PCustomer> listCustomer=baseDao.findAll(PCustomer.class,hql,i1,i2,params);
		return listCustomer;
	}
	@Override
	public List<DCustomer> transfer(List<PCustomer> customers) {
		List<DCustomer> list = new ArrayList<DCustomer>();
		if(customers!=null && customers.size()>0){
			for(PCustomer p : customers){
				list.add(this.transfer(p));
			}
		}
		return list;
	}
	@Override
	public DCustomer transfer(PCustomer customer) {
		return new DCustomer(customer);
	}
	@Override
	public PCustomer findCustomerById(String id) throws NotFindException {
		PCustomer p = baseDao.findById(PCustomer.class, id);
		if(p==null){
			NotFindException e = new NotFindException("ID为"+id+"的客户，没有找到");
			throw e;
		}
		return p;
	}
	@Override
	public int findAllCustomer(List<PCustomer> list, int page, int pageSize) {
		return this.findAllCustomer(list, null, page, pageSize);
	}

	@Override
	public int findAllCustomer(List<PCustomer> list, String query, Integer page, Integer pageSize){
		String hql = "from cn.future.customer.pojo.PCustomer as p where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(null!=query){
			hql+=" and (p.accountName like :query or p.cnName like :query or p.mobilePhone like :query or p.email like :query)";
			params.put("query", "%"+query+"%");
			
		}
		hql+=" order by p.accountName";
		List<PCustomer> datas = baseDao.findAll(PCustomer.class, hql, page, pageSize, params);
		list.addAll(datas);
		hql = "select count(*) " + hql;
		return baseDao.findCount(hql, params);
	}
	@Override
	public void validateCustomerAccountUnique(String account) throws NotUniqueException {
		String hql = "select count(*) from cn.future.customer.pojo.PCustomer as p where p.accountName=:account";
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("account", account);
		int count = baseDao.findCount(hql, params);
		if(count>0){
			NotUniqueException e = new NotUniqueException(account+"已经存在");
			throw e;
		}
	}
	@Override
	public List<PCustomer> findCustomerByConfiguration(String configName,
			String configValue, Integer page ,Integer pageSize) {
		String sql = "select p1.id from tcustomer as p1 inner join tconfiguration as p2 on p1.id=p2.hostId where p2.name='"+configName+"' and value='"+configValue+"'";
		List<String> ids = baseDao.findAll(String.class, sql, page, pageSize);
		List<PCustomer> list = baseDao.findAll(PCustomer.class, ids);
		return list;
	}
}
