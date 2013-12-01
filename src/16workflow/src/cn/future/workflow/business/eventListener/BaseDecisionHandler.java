package cn.future.workflow.business.eventListener;

import org.jbpm.api.jpdl.DecisionHandler;
import org.jbpm.api.model.OpenExecution;

public class BaseDecisionHandler implements DecisionHandler{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3256051531957693960L;

	@Override
	public String decide(OpenExecution execution) {
		// TODO Auto-generated method stub
		int k = Integer.parseInt((String)execution.getVariable("content"));
		if(k>3){
			return "good";
		}else{
			return "bad";
		}
	}

}
