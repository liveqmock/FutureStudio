package cn.future.workflow.business.eventListener;

import java.io.Serializable;

import org.springframework.beans.factory.BeanFactory;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.service.impl.BeanFactoryHelper;
import cn.future.workflow.pojo.PBaseWorkFlow;
/**
 * 用于处理工作流中java任务，修改buiness表单的状态。
 * 需要两个参数，一个参数是注入表单的类型，第二个参数是表单的id。
 */
public class BusinessJavaTask implements Serializable{
	
	public String businessType;//存放的是需要更新的对象的全路径
	private static final long serialVersionUID = 945981858496593128L;
	private BaseDao baseDao;
	/**
	 * 提供一个无餐的够早方法。
	 */
	public BusinessJavaTask(){
		BeanFactory beanFactory = BeanFactoryHelper.getFactory();
		baseDao=beanFactory.getBean("baseDao", BaseDao.class);
	}
	public void executeBusiness(String businessId,int code,String name)throws ClassNotFoundException, NotFindException{
		@SuppressWarnings("unchecked")
		Class<? extends PBaseWorkFlow> t = (Class<? extends PBaseWorkFlow>) Class.forName(businessType);
		PBaseWorkFlow b = baseDao.findById(t, businessId);
		b.setStatusCode(code);
		b.setStatusName(name);
		baseDao.update(b);
	}
	public void endBusiness(String businessId) throws ClassNotFoundException, NotFindException{
		this.executeBusiness(businessId, PBaseWorkFlow.proval, PBaseWorkFlow.provalString);
	}
	public void errorBusiness(String businessId) throws ClassNotFoundException, NotFindException{
		this.executeBusiness(businessId, PBaseWorkFlow.error, PBaseWorkFlow.errorString);
	}
	public void cancelBusiness(String businessId) throws ClassNotFoundException, NotFindException{
		this.executeBusiness(businessId, PBaseWorkFlow.cancel, PBaseWorkFlow.cancelString);
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
