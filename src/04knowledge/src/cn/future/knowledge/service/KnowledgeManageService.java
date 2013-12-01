package cn.future.knowledge.service;

import cn.future.common.exception.NotFindException;
import cn.future.knowledge.pojo.PKnowledge;

public abstract interface KnowledgeManageService {
	/**
	 * 更新知识库的内容
	 * @param d
	 * @throws NotFindException 
	 */
	public abstract void updateKnowledgeContent(String id, String content,String userid,String username) throws NotFindException;
	/**
	 * 新增一条知识库记录
	 * 无需传入 id 和 workflowid
	 * @param d
	 */
	public abstract void addKnowledge(PKnowledge d);
	/**
	 * 更新，除了内容意外的信息
	 * @param knowledge
	 * @param findCookieId
	 * @param findCookieName
	 * @throws NotFindException 
	 */
	public abstract void updateKnowledgeInfo(PKnowledge knowledge,String findCookieId, String findCookieName) throws NotFindException;
}
