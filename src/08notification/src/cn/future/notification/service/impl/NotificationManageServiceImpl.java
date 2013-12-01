package cn.future.notification.service.impl;

import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.PermissionDeclineException;
import cn.future.notification.pojo.PSysNotification;
import cn.future.notification.service.NotificationManageService;

public class NotificationManageServiceImpl implements NotificationManageService{
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	

	@Override
	public void addUserNotification(PSysNotification notification,String accountId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addUserNotification(List<PSysNotification> notifications) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void updateNotificationRead(String id, String userId,
			boolean isRightControl) throws PermissionDeclineException, NotFindException{
		PSysNotification d=baseDao.findById(PSysNotification.class, id);
		if(isRightControl){
			if(userId.equals(d.getAccountId())){
				d.setStatusCode(0);
				d.setStatusName(PSysNotification.ReadedString);
				baseDao.update(d);
			}else{
				PermissionDeclineException pd = new PermissionDeclineException("您没有操作这条消息的权限");
				throw pd;
			}
		}else{
			d.setStatusCode(0);
			d.setStatusName(PSysNotification.ReadedString);
			baseDao.update(d);
		}
		
	}


}
