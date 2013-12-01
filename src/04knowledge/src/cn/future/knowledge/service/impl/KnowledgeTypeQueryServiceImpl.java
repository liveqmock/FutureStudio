package cn.future.knowledge.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.pojo.ComparatorPriority;
import cn.future.common.exception.NotFindException;
import cn.future.knowledge.dto.DKnowledgeType;
import cn.future.knowledge.pojo.PKnowledgeType;
import cn.future.knowledge.service.KnowledgeTypeQueryService;

public class KnowledgeTypeQueryServiceImpl implements KnowledgeTypeQueryService{
	private BaseDao baseDao;
	@Override
	public PKnowledgeType findKnowledgeType(String id) throws NotFindException {
		return baseDao.findById(PKnowledgeType.class, id);
	}

	@Override
	public List<PKnowledgeType> findPortalKnowledgeType() {
		List<PKnowledgeType> list=baseDao.findAll(PKnowledgeType.class, "from cn.future.knowledge.pojo.PKnowledgeType as a where a.outReader=1 and a.isDirectory=0 and a.statusCode>0", 1, 999, null);
		return list;
	}

	@Override
	public List<PKnowledgeType> findAllKnowledgeTypeTop() {
		List<PKnowledgeType> list=baseDao.findAll(PKnowledgeType.class, "from cn.future.knowledge.pojo.PKnowledgeType as a where a.isDirectory=1", 1, 999, null);
		return list;
	}

	@Override
	public List<PKnowledgeType> findAvailableKnowledgeType() {
		List<PKnowledgeType> list=baseDao.findAll(PKnowledgeType.class, "from cn.future.knowledge.pojo.PKnowledgeType as a where a.statusCode>0", 1, 999, null);
		return list;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<DKnowledgeType> transfer(List<PKnowledgeType> listD, int transMode) {
		List<DKnowledgeType> list = new ArrayList<DKnowledgeType>();
		if(transMode==0){
			if(listD!=null){
				for(PKnowledgeType d : listD){
					list.add(this.transfer(d, transMode));
				}
			}
		}
		Collections.sort(list, new ComparatorPriority());
		return list;
	}

	@Override
	public DKnowledgeType transfer(PKnowledgeType d, int transMode) {
		return new DKnowledgeType(d);
	}

	@Override
	public List<PKnowledgeType> findAllKnowledgeTypeChildren(String id) {
		List<PKnowledgeType> list=baseDao.findAll(PKnowledgeType.class, "from cn.future.knowledge.pojo.PKnowledgeType as a where a.parentId='"+id+"'", 1, 999, null);
		return list;
	}

}
