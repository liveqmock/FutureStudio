package cn.future.knowledge.action;

import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.knowledge.dto.DKnowledgeType;
import cn.future.knowledge.pojo.PKnowledgeType;
import cn.future.knowledge.service.KnowledgeTypeQueryService;
/**
 * 查询知识库分类信息
 * @author Tony
 *
 */
public class KnowledgeTypeQueryAction extends BaseAction{
	private static final long serialVersionUID = -5516293511976411531L;
	private KnowledgeTypeQueryService knowledgeTypeQueryService;
	private String id;
	//set --  get
	private List<DKnowledgeType> knowledgeTypes;

	/**
	 * 查询顶级目录下的全部子目录
	 * @return
	 */
	public String findAllKnowledgeTypeChildren(){
		List<PKnowledgeType> listD=knowledgeTypeQueryService.findAllKnowledgeTypeChildren(id);
		knowledgeTypes=knowledgeTypeQueryService.transfer(listD, 0);
		return SUCCESS;
	}
	/**
	 * 查询知识库顶级分类
	 * @return
	 */
	public String findAllKnowledgeTypeTop(){
		List<PKnowledgeType> listD=knowledgeTypeQueryService.findAllKnowledgeTypeTop();
		knowledgeTypes=knowledgeTypeQueryService.transfer(listD, 0);
		return SUCCESS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setKnowledgeTypeQueryService(
			KnowledgeTypeQueryService knowledgeTypeQueryService) {
		this.knowledgeTypeQueryService = knowledgeTypeQueryService;
	}

	public List<DKnowledgeType> getKnowledgeTypes() {
		return knowledgeTypes;
	}
	public void setId(String id) {
		this.id = id;
	}
	
}
