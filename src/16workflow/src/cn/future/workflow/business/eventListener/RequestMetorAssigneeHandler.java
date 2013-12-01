package cn.future.workflow.business.eventListener;

import org.jbpm.api.model.OpenExecution;
import org.jbpm.api.task.Assignable;
import org.jbpm.api.task.AssignmentHandler;

import cn.future.common.dao.BaseDao;
import cn.future.common.service.impl.BeanFactoryHelper;
import cn.future.oa.pojo.PAccount;

public class RequestMetorAssigneeHandler implements AssignmentHandler{
	private BaseDao baseDao;
	/**
	 * 设置用户的领导进行审批
	 */
	private static final long serialVersionUID = -2578306997344110810L;

	@Override
	public void assign(Assignable arg0, OpenExecution arg1) throws Exception {
		// TODO Auto-generated method stub
		baseDao=BeanFactoryHelper.getFactory().getBean("baseDao", BaseDao.class);
		String requestId = (String)arg1.getVariable("requestId");

		PAccount account=baseDao.findById(PAccount.class, requestId);
		PAccount metor = account.getDepartment().getPrincipal();
		if(metor==null){
			//用户领导不存在，直接启用老板审批 TODO
			//String bossId = account.getCompany().getBossId();
			
			//PAccount boss = baseDao.findById(PAccount.class, bossId);
			//arg0.setAssignee(boss.getId());
		}else{
			arg0.setAssignee(metor.getId());
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
