package cn.future.common.service.impl;

import java.io.Serializable;

import cn.future.common.dao.BaseDao;

public class BaseService implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -7277396158541035404L;
	protected BaseDao baseDao;

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
}
