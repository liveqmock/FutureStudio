package cn.future.notification.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.notification.dto.DSysNotification;
import cn.future.notification.pojo.PSysNotification;
import cn.future.notification.service.NotificationQueryService;

public class NotificationQueryAction extends BaseAction{
	private static final long serialVersionUID = 1036786913802580286L;
	//set
	private NotificationQueryService notificationQueryService;
	private String id;
	private String searchText;
	private Date startDate;
	private int pageSize=20;
	//set -- get
	private int page=1;
	//get
	private int count;
	private List<DSysNotification> notifications;
	/**
	 * 普通用户使用，必须有id
	 * @return
	 */
	public String findUserNotification(){
		if(id==null){
			id=this.findCookieId();
		}
		List<PSysNotification> list = new ArrayList<PSysNotification>();
		count=notificationQueryService.findUnreadNotification(list, id, searchText,startDate, page, pageSize);
		notifications = notificationQueryService.transfer(list);
		return SUCCESS;
	}
	public String findUserHistoryNotification(){
		//TODO
		return SUCCESS;
	}
	public void setId(String id) {
		this.id = id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<DSysNotification> getNotifications() {
		return notifications;
	}
	public void setNotificationQueryService(
			NotificationQueryService notificationQueryService) {
		this.notificationQueryService = notificationQueryService;
	}
	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getPage() {
		return page;
	}
	public int getCount() {
		return count;
	}
	
	

}
