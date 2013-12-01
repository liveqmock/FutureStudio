package cn.future.customer.service;

import java.util.List;

import cn.future.customer.dto.DCustomer;
import cn.future.customer.pojo.PCustomer;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.NotUniqueException;
import cn.future.user.exception.UserNotAllowedAccess;
import cn.future.user.exception.UserPasswordUnmatchException;

public abstract interface CustomerQueryService {
	/**
	 * 验证用户账户唯一
	 * @param account
	 * @return
	 * @throws NotUniqueException 
	 */
	public void validateCustomerAccountUnique(String account) throws NotUniqueException;
	/**
	 * Pojo To Dto
	 * @param customer
	 * @return
	 */
	public List<DCustomer> transfer(List<PCustomer> customers);
	/**
	 * Pojo To Dto
	 * @param customer
	 * @return
	 */
	public DCustomer transfer(PCustomer customer);
	/**
	 * 通过用户名查询用户
	 * @param userName
	 * @return
	 */
	public List<PCustomer> findCustomersByName(String userName);
	/**
	 * 通过用户名，查询客户
	 * @param userName
	 * @return
	 * @throws NotFindException
	 * @throws NotUniqueException
	 */
	public PCustomer findCustomerByName(String userName) throws NotFindException, NotUniqueException;
	/**
	 * 通过ID查找客户
	 * @param id
	 * @return
	 * @throws NotFindException 
	 */
	public PCustomer findCustomerById(String id) throws NotFindException;
	/**
	 * 客户登陆服务
	 * @param username
	 * @param password
	 * @return
	 * @throws NotUniqueException 
	 * @throws NotFindException 
	 * @throws UserNotAllowedAccess 
	 * @throws UserPasswordUnmatchException 
	 */
	public PCustomer customerSignInService(String username,String password) throws NotFindException, NotUniqueException, UserNotAllowedAccess, UserPasswordUnmatchException;
	/**
	 * 查询所有的客户
	 * @param list
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public int findAllCustomer(List<PCustomer> list, int page, int pageSize);
	/**
	 * 查找所有的客户信息
	 * @param list
	 * @param query
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public int findAllCustomer(List<PCustomer> list, String query, Integer page, Integer pageSize);

	/**
	 * 查询，配置为 如下的客户
	 * @param configName
	 * @param configValue
	 * @param page
	 * @param pageSize
	 * @return
	 */
	public List<PCustomer> findCustomerByConfiguration(String configName,
			String configValue, Integer page ,Integer pageSize);
}
