package cn.future.common.service.impl;

import cn.future.common.dao.BaseDao;
import cn.future.common.pojo.BasePriority;
import cn.future.common.service.CommonService;
import cn.future.common.exception.NotFindException;

public class CommonServiceImpl implements CommonService {
	private BaseDao baseDao;
	@Override
	public <T extends BasePriority> void updatePriority(Class<T> type, String roleidA,
			int priorityA, String roleidB, int priorityB) throws NotFindException {
		T bpa = baseDao.findById(type, roleidA);
		T bpb = baseDao.findById(type, roleidB);
		if(bpa!=null && bpb!=null){
			if(bpa.getPriority()==priorityA && bpb.getPriority()==priorityB){
				bpa.setPriority(priorityB);
				bpb.setPriority(priorityA);
				baseDao.update(bpa);
				baseDao.update(bpb);
			}
		}
	}
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
