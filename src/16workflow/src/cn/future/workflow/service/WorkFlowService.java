package cn.future.workflow.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;

import cn.future.common.exception.NotFindException;
import cn.future.workflow.dto.DProcessInstance;
import cn.future.workflow.dto.DTask;
import cn.future.workflow.exception.TaskException;
import cn.future.workflow.pojo.PWorkFlowParams;

public abstract interface WorkFlowService {
	/**
	 * 完成任务
	 * @param taskId 完成任务的id
	 * @param outCome  流转方向，可以是null，但是需要判断是唯一流向
	 * @param variables  任务参数，这个地方是任务的参数
	 */
	public void updateTaskComplete(String taskId,String outCome, HashMap<String,Object> variables);
	/**
	 * 为任务添加comments
	 * @param taskId
	 * @param comments
	 */
	public void addTaskComments(String taskId,String comments);
	/**
	 * 创建一个流程实例。
	 * @param definetionKey - 流程定义的KEY
	 * @param params 流程启动需要的参数
	 * @param businessId  -- 业务主键
	 */
	public ProcessInstance addWorkFlowInstance(String definetionKey, PWorkFlowParams params, String businessId);
	

	
	/**
	 * 人工任务完成。如果不指定下一步的流程，outcome传入null。
	 * @param taskid
	 * @param userId
	 * @param params
	 * @param outcome
	 * @param comments
	 * @throws NotFindException 
	 */
	public void updateTaskComplete(String taskid,String userId, Map<String,Object> params,String outcome,String comments) throws NotFindException;

	/**
	 * 接受任务
	 * @param taskId
	 * @param userId
	 */
	public void updateTaskAssign(String taskId,String userId) throws TaskException ;
	
}
