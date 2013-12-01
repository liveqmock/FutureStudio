package cn.future.workflow.service;

import java.util.List;
import java.util.Map;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.task.Task;

import cn.future.workflow.dto.DActivityPosition;
import cn.future.workflow.dto.DProcessInstance;
import cn.future.workflow.dto.DTask;

public abstract interface WorkFlowQueryService {
	/**
	 * 通过流程实例ID，查询流程实例
	 * @param id
	 * @return
	 */
	public ProcessInstance findProcessInstanceById(String id);
	/**
	 * 通过流程定义id，当前活动名称查询流程图片的坐标定位
	 * @param processDefinitionId
	 * @param activityNames
	 * @return
	 */
	public List<DActivityPosition> findProcessImagePosition(String processDefinitionId,String[] activityNames);
	/**
	 * 查询运行中流程实例的当前活动名称。必须是运行中的流程实例
	 * @param pid
	 * @return
	 */
	public List<String> findProcessCurrentNodeName(String pid);
	/**
	 * 查询用户提交的申请
	 * @param userId
	 * @param page,
	 * @param size
	 * @return
	 */
	public int findUserRequest(String userId,int page,int size, List<DProcessInstance> listP);
	/**
	 * 查询任务的comments信息
	 * @param taskId
	 * @return
	 */
	public String findTaskComments(String taskId);
	/**
	 * 将任务对象转换成前端可用的Pojo task对象
	 * @param tasks
	 * @return
	 */
	public List<DTask> transferTaskToPojo(List<Task> tasks);
	/**
	 * 通过任务Id，获取流程实例
	 * @param taskId
	 * @return
	 */
	public ProcessInstance findProcessInstanceByTaskId(String taskId);
	/**
	 * 查找用户任务
	 * @param userid
	 * @return
	 */
	public List<Task> findUserTasks(String userid);
	/**
	 * 查找任务的的变量
	 * @param taskId
	 * @return
	 */
	public Map<String,Object> findTaskVariables(String taskId);
	/**
	 * 查找用户的候选任务
	 * @param userid
	 */
	public List<Task> findCandidateTask(String userid);
	/**
	 * 通过任务id查找任务能够流转出去的选项
	 * @param id
	 */
	public List<String> findTaskTransitions(String id);
	/**
	 * 通过流程实例的id查询已经完成的历史任务
	 * @param id
	 * @return
	 */
	public List<DTask> findInstanceHistoryTask(String id);
}
