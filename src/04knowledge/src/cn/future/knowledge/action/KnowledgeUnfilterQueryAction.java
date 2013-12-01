package cn.future.knowledge.action;

import java.util.ArrayList;
import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.knowledge.dto.DKnowledge;
import cn.future.knowledge.dto.DKnowledgeType;
import cn.future.knowledge.pojo.PKnowledge;
import cn.future.knowledge.pojo.PKnowledgeType;
import cn.future.knowledge.service.KnowledgeQueryService;
import cn.future.knowledge.service.KnowledgeTypeQueryService;

public class KnowledgeUnfilterQueryAction extends BaseAction{
	private static final long serialVersionUID = -1032942072856460426L;

	private KnowledgeTypeQueryService knowledgeTypeQueryService;
	private KnowledgeQueryService knowledgeQueryService; 
	private String knowledgeTypeId;
	private int page=1;       //不指定页码，就是第一页
	private int pageSize=20; //默认使用分页20，
	private String id;
	private int showType=0;
	//set --  get
	private String jspPage;
	private List<DKnowledgeType> konwledgeTypes;
	private List<DKnowledge> knowledges;
	private DKnowledge knowledge;
	private int pageCount;
	private int count;
	/**
	 * 门户查询新闻详细信息
	 * @return
	 * @throws NotFindException 
	 */
	public String protalKnowledgeDetail() throws NotFindException{
		PKnowledge d =knowledgeQueryService.findKnowledge(id);
		knowledge=knowledgeQueryService.transfer(d, 0);
		//knowledge.setContent(StringUtil.toHTMLString(knowledge.getContent()));
		jspPage="/portal/jsp/knowledge.jsp";
		return SUCCESSJSP;
	}
	
	/**
	 * 门户查询知识库
	 * @return
	 */
	public String portalKnowledge(){
		List<PKnowledge> listD=new ArrayList<PKnowledge>();
		count=knowledgeQueryService.findKnowledge(listD,page, pageSize, null, knowledgeTypeId,false);
		pageCount=(int)count/pageSize;
		knowledges=knowledgeQueryService.transfer(listD, 0);
		if(showType==1){
			for(DKnowledge p:knowledges){
				p.setTitle("【"+p.getKnowledgeTypeName()+"】"+p.getTitle());
			}
		}
		jspPage="/portal/jsp/knowledgeSummaryList.jsp";
		return SUCCESSJSP;
	}
	/**
	 * 门户查询所有的分类信息
	 * @return
	 */
	public String portalKnowledgeType(){
		List<PKnowledgeType> listD = knowledgeTypeQueryService.findPortalKnowledgeType();
		konwledgeTypes=knowledgeTypeQueryService.transfer(listD,0);
		jspPage="/portal/jsp/knowledgeTypeList.jsp";
		return SUCCESSJSP;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getJspPage() {
		return jspPage;
	}

	public List<DKnowledgeType> getKonwledgeTypes() {
		return konwledgeTypes;
	}

	public void setKnowledgeTypeQueryService(
			KnowledgeTypeQueryService knowledgeTypeQueryService) {
		this.knowledgeTypeQueryService = knowledgeTypeQueryService;
	}
	public void setKnowledgeTypeId(String knowledgeTypeId) {
		this.knowledgeTypeId = knowledgeTypeId;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setKnowledgeQueryService(KnowledgeQueryService knowledgeQueryService) {
		this.knowledgeQueryService = knowledgeQueryService;
	}
	public int getCount() {
		return count;
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

	public void setId(String id) {
		this.id = id;
	}

	public DKnowledge getKnowledge() {
		return knowledge;
	}

	public int getShowType() {
		return showType;
	}

	public void setShowType(int showType) {
		this.showType = showType;
	}
	
	
}
