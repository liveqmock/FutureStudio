package cn.future.common.service;

import java.util.List;

import cn.future.common.dto.DConfiguration;
import cn.future.common.exception.NotFindException;
import cn.future.common.pojo.PConfiguration;

public abstract interface ConfigService {
	/**
	 * 通过ID获取配置
	 * @param id
	 * @return
	 * @throws NotFindException
	 */
	public PConfiguration findConfiguration(String id) throws NotFindException;
	/**
	 * 添加一个配置, 这里会校验一个复合主键hostId name，如果有重复的，仅仅更新值
	 * @param pojo
	 * @return
	 * @throws NotFindException 
	 */
	public PConfiguration addConfiguration(PConfiguration pojo);
	/**
	 * 查询配置文件
	 * @param name
	 * @param host
	 * @return
	 * @throws NotFindException 
	 */
	public PConfiguration findConfiguration(String name,String host) throws NotFindException;
	/**
	 * Pojo to dto;
	 * @param p
	 * @return
	 */
	public DConfiguration transfer(PConfiguration p);
	/**
	 * Pojos to dtos;
	 * @param p
	 * @return
	 */
	public List<DConfiguration> transfer(List<PConfiguration> p);
	/**
	 * 判断当前的配置是否已经存在
	 * @param name
	 * @param host
	 * @return
	 */
	public int findConfiguratioCount(String name,String host);
	/**
	 * 更新数据, name hostId Id 不会更新
	 * @param pojo
	 * @return
	 * @throws NotFindException 
	 */
	public PConfiguration updateConfiguration(PConfiguration pojo) throws NotFindException;
}
