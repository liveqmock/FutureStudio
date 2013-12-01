package cn.future.social.service;

import cn.future.common.exception.NotFindException;
import cn.future.social.pojo.PSocialBind;

public abstract interface SocialBindQueryService {
	/**
	 * 查找绑定ID是否唯一
	 * @param bindTypeId
	 * @param bindType
	 * @param bindPosition
	 * @return
	 */
	public int findOpenIdUnique(String bindTypeId, String bindType, String bindPosition);
	/**
	 * 验证用户QQ open id 的唯一性
	 * @param bindTypeId
	 * @return  open id的数量
	 */
	public int findUserQqBindUnique(String bindTypeId);
	/**
	 * 验证用户 微信 open id 唯一性
	 * @param bindTypeId
	 * @return open id的数量
	 */
	public int findUserWeixinBindUnique(String bindTypeId);
	/**
	 * 验证客户QQ open id 的唯一性
	 * @param bindTypeId
	 * @return  open id的数量
	 */
	public int findCustomerQqBindUnique(String bindTypeId);
	/**
	 * 验证客户 微信 open id 唯一性
	 * @param bindTypeId
	 * @return open id的数量
	 */
	public int findCustomerWeixinBindUnique(String bindTypeId);
	/**
	 * 通过QQ open id 查找用户
	 * @param bindTypeId
	 * @return
	 * @throws NotFindException
	 */
	public PSocialBind findPSocialBindByQQ(String bindTypeId) throws NotFindException;
	/**
	 * 通过微信 open id 查找用户
	 * @param bindTypeId
	 * @return
	 * @throws NotFindException
	 */
	public PSocialBind findPSocialBindByWeixin(String bindTypeId) throws NotFindException;

	/**
	 * 用户用户ID查找微信绑定关系
	 * @param userId
	 * @return
	 * @throws NotFindException
	 */
	public PSocialBind findWeixinPSocialBindByUserId(String userId) throws NotFindException;
	/**
	 * 通过客户ID查询微信的绑定信息
	 * @param id
	 * @return
	 * @throws NotFindException 
	 */
	public PSocialBind findWeixinByCustomerId(String id) throws NotFindException;

	/**
	 * 通过 数据连接token进行查询，一般有几十天的有效期
	 * 暂时不要启用，因为存在token过期的问题
	 * @param bindType
	 * @param token
	 * @return
	 * @throws NotFindException
	 */
	public PSocialBind findByAccessToken(String bindType, String token)throws NotFindException;
	
}
