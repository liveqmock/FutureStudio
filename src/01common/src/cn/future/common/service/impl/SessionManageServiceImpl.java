package cn.future.common.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.logging.Logger;

import cn.future.common.service.SessionManageService;
import cn.future.customer.dto.DCustomer;

public class SessionManageServiceImpl implements SessionManageService{

	private static HashMap<String,DCustomer> custs = new HashMap<String,DCustomer>();
	private Logger logger = Logger.getLogger("cn.future.common.service.impl.SessionManageServiceImpl");
	@Override
	public void addSessionCustomer(DCustomer cus) {
		cus.setSignInTime(new Date());
		custs.put(cus.getId(), cus);
		logger.info("登陆："+cus.getId()+":"+cus.getCnName());
	}

	@Override
	public void removeSessionCustomer(DCustomer cus) {
		logger.info("退出："+cus.getId()+":"+cus.getCnName());
		custs.remove(cus.getId());
	}

	@Override
	public boolean isOld(DCustomer cus) {
		DCustomer last = custs.get(cus.getId());
		if(null!=last){
			if(cus.getSignInTime().before(last.getSignInTime())){
				return true;
			}
		}
		return false;
	}
}
