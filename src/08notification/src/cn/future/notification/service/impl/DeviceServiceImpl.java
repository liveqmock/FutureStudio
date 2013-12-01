package cn.future.notification.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.notification.dto.DDevice;
import cn.future.notification.pojo.PDevice;
import cn.future.notification.service.DeviceService;
import cn.future.util.StringUtil;

public class DeviceServiceImpl implements DeviceService {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<PDevice> findUserDevice(String userId) {
		return baseDao.findAll(PDevice.class, "from cn.future.notification.pojo.PDevice as a where a.account.id='"+userId+"'", 1, 999, null);
	}

	@Override
	public List<PDevice> findAllDevice(int page, int pageCount,
			String deviceType) {
		List<PDevice> list = baseDao.findAll(PDevice.class, "from cn.future.notification.pojo.PDevice as a where a.deviceType=:deviceType", 1, 999, null);
		return list;
	}

	@Override
	public void addAndroidDevice(PDevice device) {
		device.setId(StringUtil.getUuid());
		baseDao.save(device);
	}

	@Override
	public void deleteUserDevice(String userId) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("id", userId);
		List<PDevice> list = baseDao.findAll(PDevice.class, "from cn.future.notification.pojo.PDevice as a where a.account.id=:id", 1, 999, params);
		baseDao.deleteAll(list);
	}

	@Override
	public void deleteDevice(String deviceId) throws NotFindException{
		PDevice d = baseDao.findById(PDevice.class, deviceId);
		if(d!=null){
			baseDao.delete(d);
		}else{
			NotFindException e = new NotFindException(deviceId+"设备未找到");
			throw e;
		}
	}

	@Override
	public DDevice deviceTransfer(PDevice d) {
		return new DDevice(d);
	}

	@Override
	public List<DDevice> deviceTransfer(List<PDevice> devices) {
		List<DDevice> list=new ArrayList<DDevice>();
		if(devices!=null){
			for(PDevice d: devices){
				list.add(this.deviceTransfer(d));
			}
		}
		return list;
	}

}
