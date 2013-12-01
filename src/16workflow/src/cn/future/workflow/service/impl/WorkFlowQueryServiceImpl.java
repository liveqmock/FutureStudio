package cn.future.workflow.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.api.history.HistoryComment;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.history.model.HistoryProcessInstanceImpl;
import org.jbpm.pvm.internal.history.model.HistoryTaskInstanceImpl;

import cn.future.common.dao.BaseDao;
import cn.future.workflow.dto.DActivityPosition;
import cn.future.workflow.dto.DProcessInstance;
import cn.future.workflow.dto.DTask;
import cn.future.workflow.service.WorkFlowQueryService;

public class WorkFlowQueryServiceImpl implements WorkFlowQueryService{
	private ExecutionService executionService;
	private RepositoryService repositoryService;
	private TaskService taskService;
	private HistoryService historyService;
	private BaseDao baseDao;
	
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}

	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	
	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}

	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	@Override
	public ProcessInstance findProcessInstanceById(String id){
		return executionService.findProcessInstanceById(id);
	}
	@Override
	public List<DActivityPosition> findProcessImagePosition(String processDefinitionId,String[] activityNames){
		List<DActivityPosition> positions = new ArrayList<DActivityPosition>();
		if(activityNames!=null){
			for(String s:activityNames){
				ActivityCoordinates ac = repositoryService.getActivityCoordinates(processDefinitionId, s);
				positions.add(new DActivityPosition(ac));
			}
		}
		return positions;
	}
	@Override
	public List<String> findProcessCurrentNodeName(String pid){
		//TODO
		ProcessInstance pi = this.findProcessInstanceById(pid);
		List<String> list = new ArrayList<String>();
		if(pi!=null){
			Set<String> names = pi.findActiveActivityNames();
			if(names!=null){
				for(String s: names){
					list.add(s);
				}
			}
		}
		return list;
	}
	@Override
	public List<Task> findUserTasks(String userid) {
		return taskService.findPersonalTasks(userid);
	}
	@Override
	public Map<String, Object> findTaskVariables(String taskId) {
		Set<String> variableNames = taskService.getVariableNames(taskId);
		return taskService.getVariables(taskId, variableNames);
	}
	@Override
	public List<Task> findCandidateTask(String userid) {
		return taskService.findGroupTasks(userid);		
	}

	@Override
	public ProcessInstance findProcessInstanceByTaskId(String taskId) {
		String executionId = taskService.getTask(taskId).getExecutionId();
		ProcessInstance pi = (ProcessInstance)executionService.findExecutionById(executionId).getProcessInstance();
		return pi;
	}

	@Override
	public List<DTask> transferTaskToPojo(List<Task> tasks) {
		List<DTask> list=new ArrayList<DTask>();
		if(tasks!=null){
			for(Task t:tasks){
				Set<String> variableNames=new HashSet<String>();
				variableNames.add("title");
				variableNames.add("forms");
				variableNames.add("instanceId");
				Map<String,Object> params = taskService.getVariables(t.getId(), variableNames);
				
				String title = (String)params.get("title");
				String forms = (String)params.get("forms");
				String instanceId = (String)params.get("instanceId");
				list.add(new DTask(t,instanceId, title, forms));
			}
		}
		return list;
	}

	@Override
	public List<String> findTaskTransitions(String id) {
		List<String> outCome = new ArrayList<String>();
		Set<String> outSet = taskService.getOutcomes(id);
		if(outSet!=null){
			for(String s: outSet){
				outCome.add(s);
			}
		}
		return outCome;
	}
	@Override
	public String findTaskComments(String taskId){
		String comments="";
		List<HistoryComment> list =taskService.getTaskComments(taskId);
		if(list!=null){
			for(HistoryComment hc : list){
				comments+=hc.getMessage();
			}
		}
		return comments; 
	}
	@Override
	public List<DTask> findInstanceHistoryTask(String id) {
		List<DTask> list = new ArrayList<DTask>();
		List<HistoryTaskInstanceImpl> listHti=baseDao.findAll(HistoryTaskInstanceImpl.class, 
				"from HistoryTaskInstanceImpl as hti where hti.historyProcessInstance.processInstanceId='"+id+"' and hti.state='complete'", 1, 999, null);
		if(listHti!=null){
			for(HistoryTaskInstanceImpl hti:listHti){
				String comments=this.findTaskComments(hti.getDbid()+"");
				list.add(new DTask(hti,comments));
			}
		}
		return list;
	}
	public List<HistoryProcessInstanceImpl> findHistoryInstance(String userId,int page,int pageSize){
		String p="where hpi.key like :key ";
		String hql="from HistoryProcessInstanceImpl as hpi ";
		HashMap<String,Object> params=null;
		if(userId!=null){
			hql=hql+p+"order by hpi.startTime desc";
			params=new HashMap<String,Object>();
			params.put("key", userId+"%");
		}else{
			hql=hql+"order by hpi.startTime desc";
		}
		List<HistoryProcessInstanceImpl> list = baseDao.findAll(HistoryProcessInstanceImpl.class,
				hql, page, pageSize, params);
		return list;
	}
	@Override
	public int findUserRequest(String userId,int page,int size, List<DProcessInstance> listP){
		List<HistoryProcessInstanceImpl> list = this.findHistoryInstance(userId, page, size);
		int total=baseDao.findCount("select count(*) from jbpm4_hist_procinst where key_ like '"+userId+"%'");
		if(list!=null){
			for(HistoryProcessInstanceImpl hpi : list){
				String workFlowTitle=(String)historyService.getVariable(hpi.getProcessInstanceId(), "title");
				listP.add(new DProcessInstance(hpi,workFlowTitle));
			}
		}
		return total;
	}
}
