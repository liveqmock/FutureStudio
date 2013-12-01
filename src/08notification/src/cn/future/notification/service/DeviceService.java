package cn.future.notification.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.notification.dto.DDevice;
import cn.future.notification.pojo.PDevice;

public abstract interface DeviceService {
	/**
	 * Domain to Pojo
	 * @param d
	 * @return
	 */
	public DDevice deviceTransfer(PDevice d);
	/**
	 * Domain to pojo
	 * @param devices
	 * @return
	 */
	public List<DDevice> deviceTransfer(List<PDevice> devices);
	/**
	 * 查询用户的所有设备信息
	 * @param userId
	 * @return
	 */
	public List<PDevice> findUserDevice(String userId);
	/**
	 * 查询设备信息
	 * @param page
	 * @param pageCount
	 * @param deviceType
	 * @return
	 */
	public List<PDevice> findAllDevice(int page,int pageCount,String deviceType);
	/**
	 * 增加一台安卓推送设备
	 * @param device
	 */
	public void addAndroidDevice(PDevice device);
	/**
	 * 删除用户的所有设备
	 */
	public void deleteUserDevice(String userId);
	/**
	 * 删除一台安卓推送设备
	 * @param deviceId
	 */
	public void deleteDevice(String deviceId)throws NotFindException;
}
