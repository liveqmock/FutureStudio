package cn.future.workflow.business.eventListener;

import org.jbpm.api.Configuration;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.listener.EventListener;
import org.jbpm.api.listener.EventListenerExecution;

public class BaseEventListener  implements EventListener{
	private static final long serialVersionUID = 5011387584375157169L;

	@Override
	public void notify(EventListenerExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("this.is a state");
//		ExecutionService executionService = Configuration.getProcessEngine().getExecutionService();
//		String id=execution.getId();
//		executionService.signalExecutionById(id);
	}

}
