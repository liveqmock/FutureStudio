package cn.future.workflow.dto;

import java.io.Serializable;

import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.history.model.HistoryTaskInstanceImpl;

import cn.future.util.StringUtil;
import cn.future.util.TimeUtil;

/**
 * Task Pojo类
 * @author Tony
 *
 */
public class DTask implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8698674660253908545L;
	public DTask(){
		
	}
	public DTask(Task t,String instanceId, String title, String forms){
		this.title=title;
		this.activityName=t.getActivityName();
		this.assignee=t.getAssignee();
		this.createTime=TimeUtil.dateToYMDHMSString(t.getCreateTime());
		this.description=t.getDescription();
		this.duedate=TimeUtil.dateToYMDHMSString(t.getDuedate());
		this.executionId=t.getExecutionId();
		this.formResourceName=t.getFormResourceName();
		this.id=t.getId();
		this.name=t.getName();
		this.priority=t.getPriority();
		this.progress=t.getProgress();
		this.forms=forms;
		this.instanceId=instanceId;
	}
	public DTask(HistoryTaskInstanceImpl t,String comments){
		this.title="";
		this.activityName=t.getActivityName();
		this.assignee="";
		this.createTime=TimeUtil.dateToYMDHMSString(t.getStartTime());
		this.description=t.getTransitionName();
		this.duedate=TimeUtil.dateToYMDHMSString(t.getEndTime());
		this.executionId=t.getExecutionId();
		this.formResourceName="";
		this.id=t.getDbid()+"";
		this.name=t.getActivityName();
		this.priority=0;
		this.progress=0;
		this.forms="";
		this.comments=comments;
		this.instanceId="";
	}
	private String comments;
	private String forms;
	private String activityName;
	private String assignee;
	private String createTime;
	private String description;
	private String duedate;
	private String executionId;
	private String formResourceName;
	private String id;
	private String name;
	private int priority;
	private Integer progress;
	private String title;
	private String instanceId;
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getAssignee() {
		return assignee;
	}
	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDuedate() {
		return duedate;
	}
	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}
	public String getExecutionId() {
		return executionId;
	}
	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}
	public String getFormResourceName() {
		return formResourceName;
	}
	public void setFormResourceName(String formResourceName) {
		this.formResourceName = formResourceName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Integer getProgress() {
		return progress;
	}
	public void setProgress(Integer progress) {
		this.progress = progress;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getForms() {
		return forms;
	}
	public void setForms(String forms) {
		this.forms = forms;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getInstanceId() {
		return instanceId;
	}
	public void setInstanceId(String instanceId) {
		this.instanceId = instanceId;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
	
}
