package cn.future.workflow.business.eventListener;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.BeanFactory;

import cn.future.common.service.impl.BeanFactoryHelper;
import cn.future.notification.service.NotificationManageService;
/**
 * 
 * @author Tony
 *
 */
public class NotificationJavaTask implements Serializable{

	private static final long serialVersionUID = 1015272036588963280L;
	private NotificationManageService notificationManageService;
	public NotificationJavaTask(){
		BeanFactory beanFoctory=BeanFactoryHelper.getFactory();
		notificationManageService=beanFoctory.getBean("notificationManageService", NotificationManageService.class);
	}
	public void userTaskNotification(String userId,String title,String text){
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		Date start = new Date();
		Date end = new Date();
		c1.setTime(start);
		c2.setTime(end);
		c1.set(Calendar.DAY_OF_MONTH, c1.get(Calendar.DAY_OF_MONTH)-1);
		c2.set(Calendar.DAY_OF_MONTH, c1.get(Calendar.DAY_OF_MONTH)+1);
		//notificationService.addUserNotification(userId, c1.getTime(), c2.getTime(), text, title);
	}
}
