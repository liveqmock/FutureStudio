package cn.future.knowledge.action;

import java.util.ArrayList;
import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.knowledge.dto.DKnowledge;
import cn.future.knowledge.pojo.PKnowledge;
import cn.future.knowledge.service.KnowledgeQueryService;

public class KnowledgeQueryAction extends BaseAction{
	private static final long serialVersionUID = -4141942805741827427L;
	private KnowledgeQueryService knowledgeQueryService;
	private String knowledgeTypeId;
	private int page;
	private int pageSize;
	//set -get
	private int count;
	private int countPage;
	private List<DKnowledge> knowledges;
	public String allKnowledge(){
		List<PKnowledge> listD=new ArrayList<PKnowledge>();
		count=knowledgeQueryService.findKnowledge(listD,page, pageSize, null, knowledgeTypeId,true);
		countPage=(int)count/pageSize+1;
		knowledges=knowledgeQueryService.transfer(listD, -1);
		return SUCCESS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setKnowledgeTypeId(String knowledgeTypeId) {
		this.knowledgeTypeId = knowledgeTypeId;
	}
	public void setKnowledgeQueryService(KnowledgeQueryService knowledgeQueryService) {
		this.knowledgeQueryService = knowledgeQueryService;
	}

    public int getPage() {
        return page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPage(int page) {
		this.page = page;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<DKnowledge> getKnowledges() {
		return knowledges;
	}
	public int getCount() {
		return count;
	}
	public int getCountPage() {
		return countPage;
	}
	
}
