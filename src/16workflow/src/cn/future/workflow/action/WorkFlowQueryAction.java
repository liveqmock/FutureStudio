package cn.future.workflow.action;

import java.util.ArrayList;
import java.util.List;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;

import cn.future.common.action.BaseAction;
import cn.future.workflow.dto.DActivityPosition;
import cn.future.workflow.dto.DProcessInstance;
import cn.future.workflow.dto.DTask;
import cn.future.workflow.service.WorkFlowQueryService;
/**
 * 这个Action用于处理工作流的查询
 */
public class WorkFlowQueryAction extends BaseAction{
	private WorkFlowQueryService workFlowQueryService;
	private String id;
	private String[] activityNames;
	private int page;  //get set 
	private int pageSize;
	//上面是set下下面是get
	private List<DTask> tasks;
	private int totalPage;
	private List<DActivityPosition> positions;
	private List<DProcessInstance> processInstances;
	private String message="";
	private List<String> transitions;
	private static final long serialVersionUID = -9184925512848761912L;
	/**
	 * 通过流程实例ID获取图片定位信息
	 * @return
	 */
	public String findProcessImagePositions(){
		List<String> acs=workFlowQueryService.findProcessCurrentNodeName(id);
		ProcessInstance pi = workFlowQueryService.findProcessInstanceById(id);
		String[] activities = (String[])acs.toArray();
		positions=workFlowQueryService.findProcessImagePosition(pi.getProcessDefinitionId(), activities);
		return SUCCESS;
	}
	/**
	 * 查询流转的位置信息
	 * @return
	 */
	public String findProcessActivityImagePositions(){
		positions = workFlowQueryService.findProcessImagePosition(id, activityNames);
		return SUCCESS;
	}
	/**
	 * 查询我的申请,如果没有id，那么从cookie里面取。如果有id查询指定id的历史申请
	 * @return
	 */
	public String findUserRequest(){
		String uid="";
		if(id!=null){
			uid=id;
		}else{
			uid=this.findCookieId();
		}
		int count=0;
		processInstances=new ArrayList<DProcessInstance>();
		totalPage=workFlowQueryService.findUserRequest(uid, page, pageSize, processInstances);
		if(count!=0){
			totalPage=count/pageSize;
		}
		return SUCCESS;
	}
	/**
	 * 通过流程id查询历史任务
	 * @return
	 */
	public String findInstanceHistoryTask(){
		if(id!=null){
			tasks =workFlowQueryService.findInstanceHistoryTask(id);
		}else{
			tasks=new ArrayList<DTask>();
		}
		return SUCCESS;
	}
	/**
	 * 根据任务id查询流出选项
	 * @return
	 */
	public String findTaskTrans(){
		transitions=workFlowQueryService.findTaskTransitions(id);
		return SUCCESS;
	}
	/**
	 * 查询我的任务
	 * @return
	 * @throws Exception
	 */
	public String myTask()throws Exception{
		List<Task> ts=workFlowQueryService.findUserTasks(this.findCookieId());
		tasks=workFlowQueryService.transferTaskToPojo(ts);
		return SUCCESS;
	}
	public List<DTask> getTasks() {
		return tasks;
	}
	public String getMessage() {
		return message;
	}
	public List<String> getTransitions() {
		return transitions;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public void setWorkFlowQueryService(WorkFlowQueryService workFlowQueryService) {
		this.workFlowQueryService = workFlowQueryService;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<DProcessInstance> getProcessInstances() {
		return processInstances;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPage() {
		return page;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setActivityNames(String[] activityNames) {
		this.activityNames = activityNames;
	}
	public List<DActivityPosition> getPositions() {
		return positions;
	}
	
	
}
