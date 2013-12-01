package cn.future.finance.service;

import cn.future.finance.dto.DFinanceProject;
import cn.future.finance.pojo.PFinanceProject;

public abstract interface DFinanceProjectFactory {
	/**
	 * 获取DTO对象
	 * @return
	 */
	public DFinanceProject getDFinanceProject();
	/**
	 * 通过Pojo对象进行初始化
	 * @param pojo
	 */
	public DFinanceProjectFactory initByPojo(PFinanceProject pojo);
	/**
	 * 初始化客户信息
	 */
	public DFinanceProjectFactory initCustomerInfo();
	/**
	 * 初始化管理员信息
	 */
	public DFinanceProjectFactory initUserInfo();
	/**
	 * 初始化投标信息
	 * @return
	 */
	public DFinanceProjectFactory initBids();
	/**
	 * 模糊关键数据
	 * @return
	 */
	public DFinanceProjectFactory initSecurityBids();
	/**
	 * 初始化项目信用信息
	 * @return
	 */
	public DFinanceProjectFactory initCredits();
	
}
