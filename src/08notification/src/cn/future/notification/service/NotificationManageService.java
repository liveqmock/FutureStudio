package cn.future.notification.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.common.exception.PermissionDeclineException;
import cn.future.notification.pojo.PSysNotification;


/**
 * 通知服务
 * @author Tony
 *
 */
public abstract interface NotificationManageService {
	
	/**
	 * 添加用户的通知，
	 * 无需提供 id,flowid, accountId;		accountName;	positionId;	positionName;
	 * @param notification
	 */
	public abstract void addUserNotification(PSysNotification notification,String accountid);
	/**
	 * 批量添加用户通知
	 * @param notifications
	 */
	public abstract void addUserNotification(List<PSysNotification> notifications);
	/**
	 * 在非权限控制下，可以直接标记信息一读。否则，需要校验信息是否是用户的。是用户的才能已读，否则抛出异常：access
	 * decline
	 * @param id
	 * @param userId
	 * @param isRightControl
	 * @throws NotFindException 
	 */
	public abstract void updateNotificationRead(String id,String userId,boolean isRightControl)throws PermissionDeclineException, NotFindException;
}
