package cn.future.workflow.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.TaskService;
import org.jbpm.api.task.Task;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.oa.pojo.PAccount;
import cn.future.workflow.exception.TaskException;
import cn.future.workflow.pojo.PWorkFlowParams;
import cn.future.workflow.service.WorkFlowService;

public class WorkFlowServiceImpl implements WorkFlowService {
	private ExecutionService executionService;
	private TaskService taskService;
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	@Override
	public ProcessInstance addWorkFlowInstance(String definetionKey, PWorkFlowParams params, String businessId) {
		businessId = params.getRequestId()+"."+businessId;
		ProcessInstance pi= executionService.startProcessInstanceByKey(definetionKey,params.getParams(), businessId);
		//TODO
		//LogUtil.info("Pinstance:"+pi.getId());
		executionService.createVariables(pi.getId(), params.getParams(), true);
		return pi;
	}
	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	
	@Override
	public void updateTaskComplete(String taskId, String userId, Map<String, Object> params, String outcome,String comments) throws NotFindException {
		//添加备注
		PAccount account = baseDao.findById(PAccount.class, userId);
		this.addTaskComments(taskId, account.getName()+":"+comments);
		//完成任务
		if(outcome==null){
			taskService.completeTask(taskId, params);
		}else{
			taskService.completeTask(taskId, outcome, params);
		}
		//创建任务完成通知 TODO
	}

	
	@Override
	public void updateTaskAssign(String taskId, String userId) throws TaskException {
		Task task =taskService.getTask(taskId);
		String assignee = task.getAssignee();
		if(assignee==null){
			taskService.assignTask(taskId, userId);
		}else{
			TaskException e = new TaskException("任务已经被分配");
			throw e;
		}
		
	}

	
	@Override
	public void updateTaskComplete(String taskId,String outCome, HashMap<String,Object> variables){
		if(outCome==null){
			//只有唯一的一个流向,但是需要先判断
			Set<String> setOutComes = taskService.getOutcomes(taskId);
			if(setOutComes!=null && setOutComes.size()==1){
				if(variables!=null){
					taskService.completeTask(taskId, variables);
				}else{
					taskService.completeTask(taskId);
				}
			}
		}else{
			if(variables!=null){
				taskService.completeTask(taskId, outCome, variables);
			}else{
				taskService.completeTask(taskId, outCome);
			}		
		}
	}

	@Override
	public void addTaskComments(String taskId, String comments) {
		taskService.addTaskComment(taskId, comments);
	}
	
}
