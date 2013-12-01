package cn.future.ueditor.action;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.knowledge.pojo.PKnowledge;
import cn.future.knowledge.service.KnowledgeQueryService;

public class EditorAction extends BaseAction{
	private static final long serialVersionUID = 2962383461656249136L;
	private KnowledgeQueryService knowledgeQueryService;
	private String id;
	private String content;
	//set get
	private String jspPage;
	
	/**
	 * 编辑知识库内容
	 * @return
	 * @throws NotFindException 
	 */
	public String knowledgeEdit() throws NotFindException{
		PKnowledge d = knowledgeQueryService.findKnowledge(id);
		content=d.getContent();
		jspPage="/WEB-INF/jsp/ueditor/knowledgeEdit.jsp";
		return SUCCESSJSP;
	}
	/**
	 * 测试编辑器
	 * @return
	 */
	public String edit(){
		jspPage="/WEB-INF/ueditor.jsp";
		return SUCCESSJSP;
	}
	public String getJspPage() {
		return jspPage;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setKnowledgeQueryService(KnowledgeQueryService knowledgeQueryService) {
		this.knowledgeQueryService = knowledgeQueryService;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public String getId() {
		return id;
	}
	
}
