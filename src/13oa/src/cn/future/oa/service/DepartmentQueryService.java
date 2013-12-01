package cn.future.oa.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.oa.dto.DDepartment;

public abstract interface DepartmentQueryService {
	/**
	 * 初始化
	 * @return
	 * @throws NotFindException 
	 */
	public List<DDepartment> findDepartmentTree() throws NotFindException;
}
