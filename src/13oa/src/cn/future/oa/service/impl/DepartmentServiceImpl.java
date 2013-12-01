package cn.future.oa.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.oa.pojo.PDepartment;
import cn.future.oa.service.DepartmentService;

public class DepartmentServiceImpl implements DepartmentService{
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public List<PDepartment> findDeptsPosition(String id) throws NotFindException{
		PDepartment d = baseDao.findById(PDepartment.class, id);
		List<PDepartment> position=new ArrayList<PDepartment>();
		position.add(d);
		int a=position.size();
		int b=0;
		while(a!=b){
			a=position.size();
			Map<String,PDepartment> map=new HashMap<String,PDepartment>();
			for(PDepartment i:position){
				if(i.getLevel()==100){
					map.put(i.getId(),i);
				}else{
					Set<PDepartment> setChildren = i.getChildren();
					if(setChildren!=null){
						for(PDepartment i2:setChildren){
							map.put(i2.getId(), i2);
						}
					}
				}		
			}
			position=new ArrayList<PDepartment>();
			for(String s:map.keySet()){
				position.add(map.get(s));
			}
			b=position.size();
		}
		return position;
	}
}
