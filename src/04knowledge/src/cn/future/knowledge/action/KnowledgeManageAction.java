package cn.future.knowledge.action;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.knowledge.pojo.PKnowledge;
import cn.future.knowledge.service.KnowledgeManageService;

public class KnowledgeManageAction extends BaseAction{
	private static final long serialVersionUID = -7981642101628778191L;
	private KnowledgeManageService knowledgeManageService;
	private PKnowledge knowledge;
	private String content;
	private String id;
	//set - get
	private String message;
	/**
	 * 更新除了内容以外的信息
	 * @return
	 */
	public String modifyKnowledge(){
		try{
			knowledgeManageService.updateKnowledgeInfo(knowledge,this.findCookieId(),this.findCookieName());
			message="更新成功";
		}catch(Exception e){
			response.setStatus(400);
			message="更新出错,"+e.getMessage();
		}
		return SUCCESS;
	}
	/**
	 * 更新知识库内容
	 * @return
	 * @throws NotFindException 
	 */
	public String updateKnowledgeContent() throws NotFindException{
		knowledgeManageService.updateKnowledgeContent(id, content, this.findCookieId(), this.findCookieName());
		message="更新成功";
		return SUCCESS;
	}
	/**
	 * 添加一条知识库记录， 但不包括内容
	 * @return
	 */
	public String addKnowledge(){
		try{
			knowledge.setUserId(this.findCookieId());
			knowledge.setUserName(this.findCookieName());
			knowledgeManageService.addKnowledge(knowledge);
			message="添加成功";
		}catch(Exception e){
			message="添加失败,"+e.getMessage();
			response.setStatus(400);
		}
		return SUCCESS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setKnowledge(PKnowledge knowledge) {
		this.knowledge = knowledge;
	}
	public void setKnowledgeManageService(
			KnowledgeManageService knowledgeManageService) {
		this.knowledgeManageService = knowledgeManageService;
	}
	public String getMessage() {
		return message;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setId(String id) {
		this.id = id;
	}	
}
