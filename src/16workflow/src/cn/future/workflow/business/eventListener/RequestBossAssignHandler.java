package cn.future.workflow.business.eventListener;

import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;

import cn.future.common.dao.BaseDao;
import cn.future.common.service.impl.BeanFactoryHelper;
import cn.future.oa.pojo.PAccount;

public class RequestBossAssignHandler implements AssignmentHandler{

	/**
	 * boss审批
	 */
	private static final long serialVersionUID = 2377520889222461138L;
	@Override
	public void assign(Assignable arg0, OpenExecution arg1) throws Exception {
		// TODO Auto-generated method stub
		BaseDao baseDao = BeanFactoryHelper.getFactory().getBean("baseDao",BaseDao.class);
		String requestId = (String)arg1.getVariable("requestId");
		PAccount requestUser = baseDao.findById(PAccount.class, requestId);
		//TODO need boss id
		//arg0.setAssignee(requestUser.getCompany().getBossId());
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
