package cn.future.security.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.security.exception.SecurityCodeNotFindException;
import cn.future.security.pojo.PSecurityCode;
import cn.future.security.service.SecurityCodeService;

public class SecurityCodeServiceImpl implements SecurityCodeService {
	private BaseDao baseDao;
	private static final String SESSION_CODE_KEY ="session_code_key";
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public PSecurityCode findSecurityCode(String userId, String businessId, String businessTag, int availableMinutes) {
		PSecurityCode sc = new PSecurityCode(userId, businessId, businessTag, availableMinutes);
		baseDao.save(sc);
		return sc;
	}

	@Override
	public PSecurityCode findLongTimeSecurityCode(String userId, String businessId, String businessTag) {
		int k = 60 * 2; // 两个小时
		return this.findSecurityCode(userId, businessId, businessTag, k);
	}

	@Override
	public PSecurityCode findShortTimeSecurityCode(String userId, String businessId, String businessTag) {
		int k = 30; // 30分钟
		return this.findSecurityCode(userId, businessId, businessTag, k);
	}

	@Override
	public boolean validateSecurityCode(String id, String key, String userId, String businessId, String businessTag) throws SecurityCodeNotFindException{
		try {
			PSecurityCode sc = baseDao.findById(PSecurityCode.class, id);
			if(null!=userId){
				if(!userId.equals(sc.getUserId())){
					return false;
				}
			}
			if(null!=businessId){
				if(!businessId.equals(sc.getBusinessId())){
					return false;
				}
			}
			if(null!=businessTag){
				if(!businessTag.equals(sc.getBusinessTag())){
					return false;
				}
			}
			if (sc.getAvailable() > 0) {
				if (sc.getCode().equals(key)) {
					sc.setAvailable(0);
					baseDao.update(sc);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		} catch (NotFindException e) {
			e.printStackTrace();
			SecurityCodeNotFindException e1 = new SecurityCodeNotFindException("验证码错误");
			throw e1;
		}
		
	}

	@Override
	public void deleteUnavailableSecurityCode() {
		String hql = "from cn.future.common.pojo.PSecurityCode as p where p.available<1 or p.deadDate<:deadDate";
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("deadDate", new Date());
		List<PSecurityCode> list = baseDao.findAll(PSecurityCode.class, hql, 1,
				999, params);
		baseDao.deleteAll(list);
	}

	@Override
	public boolean validateSessionSecurityCode(HttpServletRequest request,
			String userId, String businessId, String businessTag, String key) throws SecurityCodeNotFindException {
		String id = (String)request.getSession().getAttribute(SESSION_CODE_KEY);
		request.getSession().removeAttribute(SESSION_CODE_KEY);
		return this.validateSecurityCode(id, key, userId, businessId, businessTag);
	}

	@Override
	public PSecurityCode findSessionSecurityCode(HttpServletRequest request,
			String userId, String businessId, String businessTag) {
		PSecurityCode pc = this.findSecurityCode(userId, businessId, businessTag, 30);
		request.getSession().setAttribute(SESSION_CODE_KEY, pc.getId());
		return pc;
	}

}
