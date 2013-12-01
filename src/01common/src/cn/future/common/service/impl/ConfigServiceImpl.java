package cn.future.common.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.dto.DConfiguration;
import cn.future.common.exception.NotFindException;
import cn.future.common.pojo.PConfiguration;
import cn.future.common.service.ConfigService;

public class ConfigServiceImpl implements ConfigService{
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public PConfiguration findConfiguration(String id) throws NotFindException {
		PConfiguration cfg=baseDao.findById(PConfiguration.class, id);
		return cfg;
	}

	@Override
	public PConfiguration addConfiguration(PConfiguration pojo){
		int count = this.findConfiguratioCount(pojo.getName(), pojo.getHostid());
		if(count>0){
			try {
				PConfiguration persi = findConfiguration(pojo.getName(), pojo.getHostid());
				pojo.setId(persi.getId());
				pojo = this.updateConfiguration(pojo);
			} catch (NotFindException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			baseDao.save(pojo);
		}
		return pojo;
	}

	@Override
	public PConfiguration findConfiguration(String name, String host) throws NotFindException {
		String hql = "from cn.future.common.pojo.PConfiguration as p where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(null!=name){
			hql+=" and p.name=:name";
			params.put("name",name);
		}
		if(null!=host){
			hql+=" and p.hostid=:host";
			params.put("host", host);
		}
		List<PConfiguration> list =baseDao.findAll(PConfiguration.class, hql, 1, 5, params);
		if(null == list || list.size()==0){
			NotFindException e =new NotFindException("Host:"+host+",Name:"+name+"配置未找到");
			throw e;
		}
		return list.get(0);
	}

	@Override
	public DConfiguration transfer(PConfiguration p) {
		return new DConfiguration(p);
	}

	@Override
	public List<DConfiguration> transfer(List<PConfiguration> p) {
		List<DConfiguration> list = new ArrayList<DConfiguration>();
		if(null!=p){
			for(PConfiguration i : p){
				list.add(this.transfer(i));
			}
		}
		return list;
	}

	@Override
	public int findConfiguratioCount(String name, String host) {
		String hql = "select count(*) from cn.future.common.pojo.PConfiguration as p where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(null!=name){
			hql+=" and p.name=:name";
			params.put("name",name);
		}
		if(null!=host){
			hql+=" and p.hostid=:host";
			params.put("host", host);
		}
		return baseDao.findCount(hql, params);
	}

	@Override
	public PConfiguration updateConfiguration(PConfiguration pojo) throws NotFindException {
		PConfiguration pers = baseDao.findById(PConfiguration.class, pojo.getId());
		pers.setComments(pojo.getComments());
		pers.setValue(pojo.getValue());
		pers.setPriority(pojo.getPriority());
		baseDao.update(pers);
		return pers;
	}

}
