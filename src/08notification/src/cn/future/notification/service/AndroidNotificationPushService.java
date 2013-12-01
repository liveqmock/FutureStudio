package cn.future.notification.service;

import java.util.List;

import cn.future.notification.pojo.PDevice;
import cn.future.notification.pojo.PSysNotification;

public abstract interface AndroidNotificationPushService {
	/**
	 * 向android一组设备推送多个
	 * @param devices
	 * @param notification
	 */
	public void pushNotification(List<PDevice> devices,List<PSysNotification> notifications);
	/**
	 * 向android一组设备推送一个通知
	 * @param devices
	 * @param notification
	 */
	public void pushNotification(List<PDevice> devices,PSysNotification notification);
	/**
	 * 向android单设备推送单通知
	 * @param devices
	 * @param notification
	 */
	public void pushNotification(PDevice device,PSysNotification notification);
}
