package cn.future.knowledge.service.impl;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.knowledge.pojo.PKnowledge;
import cn.future.knowledge.service.KnowledgeManageService;
import cn.future.util.StringUtil;

public class KnowledgeManageServiceImpl implements KnowledgeManageService {
	private BaseDao baseDao;
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public void addKnowledge(PKnowledge d) {
		d.setId(StringUtil.getUuid());
		d.setFlowId(StringUtil.serialNumber());
		baseDao.save(d);
	}

	@Override
	public void updateKnowledgeContent(String id, String content,
			String userId, String userName) throws NotFindException {
		PKnowledge d = baseDao.findById(PKnowledge.class, id);
		d.setContent(content);
		d.setUserId(userId);
		d.setUserName(userName);
		baseDao.update(d);
	}
	@Override
	public void updateKnowledgeInfo(PKnowledge knowledge, String findCookieId,
			String findCookieName) throws NotFindException {
		PKnowledge d=baseDao.findById(PKnowledge.class, knowledge.getId());
		d.setComments(knowledge.getComments());
		d.setDate(knowledge.getDate());
		d.setKeyWords(knowledge.getKeyWords());
		d.setKnowledgeTypeId(knowledge.getKnowledgeTypeId());
		d.setKnowledgeTypeName(knowledge.getKnowledgeTypeName());
		d.setStatusCode(knowledge.getStatusCode());
		d.setStatusName(knowledge.getStatusName());
		d.setTitle(knowledge.getTitle());
		d.setUserId(findCookieId);
		d.setUserName(findCookieName);
		d.setVersion(knowledge.getVersion()+1);
		baseDao.update(d);
	}

}
