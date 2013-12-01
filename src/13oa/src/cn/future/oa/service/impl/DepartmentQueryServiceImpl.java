package cn.future.oa.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.pojo.ComparatorPriority;
import cn.future.oa.dto.DDepartment;
import cn.future.oa.pojo.PDepartment;
import cn.future.oa.service.DepartmentQueryService;

public class DepartmentQueryServiceImpl implements DepartmentQueryService {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<DDepartment> findDepartmentTree() throws NotFindException {
		PDepartment top = baseDao.findById(PDepartment.class, "0");
		List<DDepartment> list = new ArrayList<DDepartment>();
		Set<PDepartment> pos = top.getChildren();
		if(pos!=null && pos.size()>0){
			for(PDepartment d : pos){
				if(d.getId()!="0"){
					list.add(new DDepartment(d,true));
				}
			}
		}
		Collections.sort(list, new ComparatorPriority());
		return list;
	}

}
