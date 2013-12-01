package cn.future.social.service.impl;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.social.pojo.PSocialBind;
import cn.future.social.service.SocialBindManageService;

public class SocialBindManageServiceImpl implements SocialBindManageService {
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public PSocialBind addPSocialBind(PSocialBind pojo) {
		baseDao.save(pojo);
		return pojo;
	}
	@Override
	public void removePSocialBind(String id) throws NotFindException {
		Object o = baseDao.findById(PSocialBind.class, id);
		baseDao.delete(o);
	}
	@Override
	public PSocialBind updateNickName(String id, String nickname) throws NotFindException {
		PSocialBind sb = baseDao.findById(PSocialBind.class, id);
		sb.setBindNickName(nickname);
		baseDao.update(sb);
		return sb;
	}

}
