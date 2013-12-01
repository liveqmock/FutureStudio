package cn.future.workflow.business.eventListener;

import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;

import cn.future.common.dao.BaseDao;
import cn.future.common.service.impl.BeanFactoryHelper;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.pojo.PDepartment;

public class RequestMetorMetorAssignHandler implements AssignmentHandler{

	/**
	 * 用户，领导的领导审批
	 */
	private static final long serialVersionUID = 7945564526274651025L;
	@Override
	public void assign(Assignable arg0, OpenExecution arg1) throws Exception {
		// TODO Auto-generated method stub
		BaseDao baseDao = BeanFactoryHelper.getFactory().getBean("baseDao",BaseDao.class);
		String requestId = (String)arg1.getVariable("requestId");
		PAccount requestUser = baseDao.findById(PAccount.class,requestId);
		PDepartment department= requestUser.getDepartment();//所在部门
		int needBoss=1;
		if(department!=null){
			PDepartment parent = department.getParent();
			if(parent!=null){
				PAccount mentorMentor = parent.getPrincipal();
				if(mentorMentor!=null){
					needBoss=0;
					arg0.setAssignee(mentorMentor.getId());
				}
			}
		}
		
		if(needBoss==1){
			//arg0.setAssignee(requestUser.getCompany().getBossId());
		}
		
		
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
