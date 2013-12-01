package cn.future.workflow.business.eventListener;

import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;

public class BaseAssigneeHandler implements AssignmentHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3423804004993369219L;
	public String valueAssignee;
	@Override
	public void assign(Assignable assignable, OpenExecution execution)
			throws Exception {
		System.out.println("assigne");
		assignable.setAssignee(valueAssignee);
		
	}
	public String getValueAssignee() {
		return valueAssignee;
	}
	public void setValueAssignee(String valueAssignee) {
		System.out.println("set Value");
		this.valueAssignee = valueAssignee;
	}

}
