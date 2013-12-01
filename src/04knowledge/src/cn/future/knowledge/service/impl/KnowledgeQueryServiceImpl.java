package cn.future.knowledge.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.knowledge.dto.DKnowledge;
import cn.future.knowledge.pojo.PKnowledge;
import cn.future.knowledge.service.KnowledgeQueryService;

public class KnowledgeQueryServiceImpl implements KnowledgeQueryService{
	private BaseDao baseDao;
	@Override
	public PKnowledge findKnowledge(String id) throws NotFindException {
		return baseDao.findById(PKnowledge.class, id);
	}

	@Override
	public int findKnowledge(List<PKnowledge> listD, int page, int pageSize, String query,
			String knowledgeTypeId,boolean isAll) {
		String hql="from cn.future.knowledge.pojo.PKnowledge as a where 1=1";
		HashMap<String,Object> params = new HashMap<String,Object>();
		if(!isAll){
			hql+=" and a.statusCode=1";
		}
		if(query!=null){
			hql+=" and a.content like :query";
			params.put("query", "%"+query+"%");
		}
		if(knowledgeTypeId!=null){
			hql+=" and a.knowledgeTypeId=:knowledgeTypeId";
			params.put("knowledgeTypeId", knowledgeTypeId);
		}
		hql+=" order by a.date desc";
		listD.addAll(baseDao.findAll(PKnowledge.class, hql, page, pageSize, params));
		
		return baseDao.findCount("select count(*) "+hql, params);
	}

	@Override
	public List<DKnowledge> transfer(List<PKnowledge> listD, int transMode) {
		List<DKnowledge> listP = new ArrayList<DKnowledge>();
		if(listD!=null){
			for(PKnowledge d:listD){
				listP.add(this.transfer(d, transMode));
			}
		}
		return listP;
	}

	@Override
	public DKnowledge transfer(PKnowledge d, int transMode) {
		if(transMode==0){
			return new DKnowledge(d);
		}if(transMode==-1){
			DKnowledge p= new DKnowledge(d);
			p.setContent("");
			return p;
		}else{
			return null;
		}
		
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
