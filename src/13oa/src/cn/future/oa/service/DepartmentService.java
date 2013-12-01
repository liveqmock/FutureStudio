package cn.future.oa.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.oa.pojo.PDepartment;

public abstract interface DepartmentService {
	/**
	 * 取当前目录下的所有职位，这个地方默认留一个bug，默认循环10次寻找
	 * @param pl
	 * @return
	 * @throws NotFindException 
	 */
	public List<PDepartment> findDeptsPosition(String id) throws NotFindException;
}
