package cn.future.notification.action;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.PermissionDeclineException;
import cn.future.notification.service.NotificationManageService;

public class NotificationManageAction extends BaseAction{
	private static final long serialVersionUID = -6713812615808164833L;
	private NotificationManageService notificationService;
	private String id;
	//set get
	
	private String message;
	/**
	 * 用户阅读通知
	 * @return
	 * @throws NotFindException 
	 */
	public String readNotification() throws NotFindException{
		try {
			notificationService.updateNotificationRead(id, this.findCookieId(), true);
			message="消息已阅";
		} catch (PermissionDeclineException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			this.response.setStatus(400);
			message=e.getMessage();
		}
		return SUCCESS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setNotificationService(NotificationManageService notificationService) {
		this.notificationService = notificationService;
	}

	public String getMessage() {
		return message;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	
}
