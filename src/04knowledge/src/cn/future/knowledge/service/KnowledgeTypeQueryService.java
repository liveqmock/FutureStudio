package cn.future.knowledge.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.knowledge.dto.DKnowledgeType;
import cn.future.knowledge.pojo.PKnowledgeType;

public abstract interface KnowledgeTypeQueryService {
	/**
	 * 对象互转
	 * @param listD
	 * @param transMode 转换模式，0是门户目录转换模式
	 * @return
	 */
	public List<DKnowledgeType> transfer(List<PKnowledgeType> listD, int transMode);
	/**
	 * 对象互转
	 * @param d
	 * @param transMode 转换模式，0是门户目录转换模式
	 * @return
	 */
	public DKnowledgeType transfer(PKnowledgeType d, int transMode);
	/**
	 * 查询单个分类
	 * @param id
	 * @return
	 * @throws NotFindException 
	 */
	public PKnowledgeType findKnowledgeType(String id) throws NotFindException;
	/**
	 * 查询门户可用分类（任何人可以访问）
	 * @return
	 */
	public List<PKnowledgeType> findPortalKnowledgeType();
	/**
	 * 管理员查询所有的第一级分类，
	 * @return
	 */
	public List<PKnowledgeType> findAllKnowledgeTypeTop();
	/**
	 * 查询可用的分类，用户级别
	 * @return
	 */
	public List<PKnowledgeType> findAvailableKnowledgeType();
	/**
	 * 查询某目录下的所有二级目录
	 * @param id
	 * @return
	 */
	public List<PKnowledgeType> findAllKnowledgeTypeChildren(String id);
}
