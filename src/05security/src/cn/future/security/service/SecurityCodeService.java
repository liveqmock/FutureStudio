package cn.future.security.service;

import javax.servlet.http.HttpServletRequest;

import cn.future.security.exception.SecurityCodeNotFindException;
import cn.future.security.pojo.PSecurityCode;
//TODO 定时清理过期验证码
public abstract interface SecurityCodeService {

	/**
	 * session级别验证码都只有15分钟
	 * @param request
	 * @param userId
	 * @param businessId
	 * @param businessTag
	 * @throws SecurityCodeNotFindException 
	 */
	public boolean validateSessionSecurityCode(HttpServletRequest request,String userId, String businessId, String businessTag, String key) throws SecurityCodeNotFindException;
	/**
	 * session级别验证码都只有15分钟
	 * @param request
	 * @param userId
	 * @param businessId
	 * @param businessTag 重要字段，不为空时会校验
	 * @return id
	 */
	public PSecurityCode findSessionSecurityCode(HttpServletRequest request,String userId, String businessId, String businessTag);
	/**
	 * 自定义有效期，单位分钟
	 * @param minutes
	 * @return
	 */
	public PSecurityCode findSecurityCode(String userId, String businessId, String businessTag, int availableMinutes);
	/**
	 * 两个小时有效期
	 * @return
	 */
	public PSecurityCode findLongTimeSecurityCode(String userId, String businessId, String businessTag);
	/**
	 * 30分钟有效期
	 * @return
	 */
	public PSecurityCode findShortTimeSecurityCode(String userId, String businessId, String businessTag);
	/**
	 * 验证code 是否正确，正确之后则失效
	 * @param id 校验ID
	 * @param key 校验短码
	 * @param userId 执行验证的用户ID，可以为null，则不校验
	 * @param businessId 业务ID，可以为null，则不校验
	 * @param businesstag 业务标记， 可以为null，则不校验
	 * @return
	 * @throws SecurityCodeNotFindException 
	 */
	public boolean validateSecurityCode(String id, String key, String userId, String businessId, String businesstag) throws SecurityCodeNotFindException, SecurityCodeNotFindException;
	/**
	 * 删除无效的code 记录
	 */
	public void deleteUnavailableSecurityCode();
}
