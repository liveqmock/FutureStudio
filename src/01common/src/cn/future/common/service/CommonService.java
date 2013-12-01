package cn.future.common.service;

import cn.future.common.pojo.BasePriority;
import cn.future.common.exception.NotFindException;

public abstract interface CommonService {
	/**
	 * 对级别进行移动操作，
	 * @param type 实际类型
	 * @param roleidA
	 * @param priorityA
	 * @param roleidB
	 * @param priorityB
	 * @throws NotFindException 
	 */
	public <T extends BasePriority> void updatePriority(Class<T> type,String roleidA,int priorityA,String roleidB,int priorityB) throws NotFindException;
}
