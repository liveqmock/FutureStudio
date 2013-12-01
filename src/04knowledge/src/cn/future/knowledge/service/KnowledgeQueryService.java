package cn.future.knowledge.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.knowledge.dto.DKnowledge;
import cn.future.knowledge.pojo.PKnowledge;

public abstract interface KnowledgeQueryService {
	
	/**
	 * 对象转换
	 * @param listD
	 * @param transMode 门户使用模式0， -1 抛弃内容模式减少传输数据量
	 * @return
	 */
	public List<DKnowledge> transfer(List<PKnowledge> listD, int transMode);
	/**
	 * 对象转换
	 * @param d
	 * @param transMode  门户使用模式0
	 * @return
	 */
	public DKnowledge transfer(PKnowledge d, int transMode);
	/**
	 * 查询单个知识库信息
	 * @param id
	 * @return
	 * @throws NotFindException 
	 */
	public PKnowledge findKnowledge(String id) throws NotFindException;
	/**
	 * 
	 * @param page 
	 * @param pageSize 
	 * @param query 可选，模糊查询内容
	 * @param knowledgeTypeId   可选，查询分类
	 * @return
	 */
	public int findKnowledge(List<PKnowledge> listD, int page,int pageSize,String query,String knowledgeTypeId,boolean isAll);
}
