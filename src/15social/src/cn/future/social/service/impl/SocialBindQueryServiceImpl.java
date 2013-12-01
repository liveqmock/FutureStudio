package cn.future.social.service.impl;

import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.social.pojo.PSocialBind;
import cn.future.social.service.SocialBindQueryService;
import cn.future.social.util.SocialUtil;

public class SocialBindQueryServiceImpl implements SocialBindQueryService {

	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public PSocialBind findPSocialBindByQQ(String bindTypeId) throws NotFindException {
		return this.findSocialBind(null, bindTypeId, SocialUtil.BIND_TYPE_QQ).get(0);
	}

	@Override
	public PSocialBind findPSocialBindByWeixin(String bindTypeId) throws NotFindException {
		return this.findSocialBind(null, bindTypeId, SocialUtil.BIND_TYPE_WEIXIN).get(0);
	}
	
	public List<PSocialBind> findSocialBind(String userId, String bindTypeId, String bindType) throws NotFindException{
		String hql = "from cn.future.social.pojo.PSocialBind as p where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(null!=userId){
			hql += " and p.userId=:userId";
			params.put("userId",userId);
			
		}
		if(null!=bindTypeId){
			hql +=" and p.bindTypeId=:bindTypeId";
			params.put("bindTypeId", bindTypeId);
		}
		if(null!=bindType){
			hql += " and p.bindType=:bindType";
			params.put("bindType", bindType);
		}
		List<PSocialBind> list = baseDao.findAll(PSocialBind.class, hql, null, null, params);
		if(list==null || list.size()<1){
			NotFindException e = new NotFindException("绑定ID为："+bindTypeId+",绑定类型为："+bindType+"的用户，未找到");
			throw e;
		}
		return list;
	}

	@Override
	public int findUserQqBindUnique(String bindTypeId) {
		return this.findOpenIdUnique(bindTypeId, SocialUtil.BIND_TYPE_QQ,"u");
		
	}

	@Override
	public int findUserWeixinBindUnique(String bindTypeId) {
		return this.findOpenIdUnique(bindTypeId, SocialUtil.BIND_TYPE_WEIXIN,"u");
		
	}
	@Override
	public int findOpenIdUnique(String bindTypeId, String bindType, String bindPosition){
		String hql = "select count(*) from cn.future.social.pojo.PSocialBind as p where p.bindType=:bindType and p.bindTypeId=:bindTypeId and p.bindPosition=:bindPosition";
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("bindType", bindType);
		params.put("bindTypeId", bindTypeId);
		params.put("bindPosition", bindPosition);
		return baseDao.findCount(hql, params);
	}

	@Override
	public int findCustomerQqBindUnique(String bindTypeId) {
		return this.findOpenIdUnique(bindTypeId, SocialUtil.BIND_TYPE_QQ,"c");
	}

	@Override
	public int findCustomerWeixinBindUnique(String bindTypeId) {
		return this.findOpenIdUnique(bindTypeId, SocialUtil.BIND_TYPE_WEIXIN,"c");
	}

	@Override
	public PSocialBind findWeixinByCustomerId(String id) throws NotFindException {
		return this.findSocialBind(id, null, SocialUtil.BIND_TYPE_WEIXIN).get(0);
	}

	@Override
	public PSocialBind findWeixinPSocialBindByUserId(String userId)
			throws NotFindException {
		PSocialBind pb = this.findSocialBind(userId, null, SocialUtil.BIND_TYPE_WEIXIN).get(0);
		return pb;
	}

	@Override
	public PSocialBind findByAccessToken(String bindType, String token)
			throws NotFindException {
		// TODO Auto-generated method stub
		return null;
	}
}
