package cn.future.workflow.action;

import java.util.Date;
import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.oa.dto.DAccount;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.service.AccountService;
import cn.future.util.StringUtil;
import cn.future.util.TimeUtil;
import cn.future.workflow.dto.DTask;
import cn.future.workflow.pojo.PWorkFlowParams;
import cn.future.workflow.service.WorkFlowDefinitionService;
import cn.future.workflow.service.WorkFlowService;

public class WorkFlowAction extends BaseAction{

	private static final long serialVersionUID = -8402403471882524197L;
	
	private WorkFlowDefinitionService workFlowDefinitionService;
	private WorkFlowService workFlowService;
	private AccountService accountService;
	private String id;
	private String comments;
	private String variables; //key:value,key:value
	private String definitionFileName;
	private String definitionKey="Oa_CeShi";
	private String transition;
	//分割线   上面是set，下面是get
	private List<String> transitions;
	private List<DTask> tasks;
	private String message="";
	public String completeTask() throws NotFindException{
		//TODO add variables
		message="任务失败";
		workFlowService.updateTaskComplete(id, this.findCookieId(), null, transition,comments);
		message="待办提交成功";
		return SUCCESS;
	}

	/**
	 *这是一个测试的addTest，在不需要的使用，可以删除
	 */
	public String addTest()throws Exception{
		//新增测试流程
		PAccount requestUser = accountService.findAccountById(this.findCookieId());
		String businessId = StringUtil.getUuid();
		String title=TimeUtil.dateToYMDHMSString(new Date())+"["+requestUser.getName()+"]提交了[CeShi]申请";
		PWorkFlowParams params= new PWorkFlowParams(this.findCookieId(),definitionKey+"."+businessId,title,null,null);
		params.put("requestId",this.findCookieId());
		//TODO
		//LogUtil.info(businessId);
		
		workFlowService.addWorkFlowInstance(definitionKey, params, businessId);
		return SUCCESS;
	}
	public String deployDefinition(){
		message="部署失败";
		workFlowDefinitionService.addWorkFlowDefinition(definitionFileName);
		message="部署成功";
		return SUCCESS;
	}
	
	public String deleteDefinition(){
		return SUCCESS;
	}
	public String definitionDetail(){
		return SUCCESS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMessage() {
		return message;
	}
	public void setWorkFlowService(WorkFlowService workFlowService) {
		this.workFlowService = workFlowService;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setDefinitionFileName(String definitionFileName) {
		this.definitionFileName = definitionFileName;
	}


	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}

	public List<DTask> getTasks() {
		return tasks;
	}
	public List<String> getTransitions() {
		return transitions;
	}
	public void setTransition(String transition) {
		this.transition = transition;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	public void setWorkFlowDefinitionService(
			WorkFlowDefinitionService workFlowDefinitionService) {
		this.workFlowDefinitionService = workFlowDefinitionService;
	}
	
}
