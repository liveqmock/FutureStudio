package cn.future.social.service;

import cn.future.common.exception.NotFindException;
import cn.future.social.pojo.PSocialBind;

public abstract interface SocialBindManageService {
	/**
	 * 添加社会绑定
	 * @param pojo
	 * @return
	 */
	public PSocialBind addPSocialBind(PSocialBind pojo);
	/**
	 * 解绑一个账户
	 * @param id
	 * @throws NotFindException 
	 */
	public void removePSocialBind(String id) throws NotFindException;
	/**
	 * 更新昵称
	 * @param id
	 * @param nickname
	 * @return
	 * @throws NotFindException 
	 */
	public PSocialBind updateNickName(String id, String nickname) throws NotFindException;
}
