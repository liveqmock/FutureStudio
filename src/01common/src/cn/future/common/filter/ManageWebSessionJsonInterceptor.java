package cn.future.common.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import cn.future.common.service.SessionManageService;
import cn.future.common.service.impl.BeanFactoryHelper;
import cn.future.customer.dto.DCustomer;
import cn.future.util.SessionUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 客户session状态拦截器，拦截ajax请求
 * @author Tony
 *
 */
public class ManageWebSessionJsonInterceptor extends AbstractInterceptor {


	/**
	 * 
	 */
	private static final long serialVersionUID = 8133538936976874727L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		if(SessionUtil.isCustomerActive(request)){
			DCustomer cus = SessionUtil.getSessionCustomer(request);
			BeanFactory bf = BeanFactoryHelper.getFactory();
			SessionManageService sessionManageService = bf.getBean("sessionManageService", SessionManageService.class);
			if(sessionManageService.isOld(cus)){
				SessionUtil.clearSessionCustomer(request);
				return "sessionFail";
			}else{
				return arg0.invoke();
			}
		}else{
			return "sessionFail";
		}
	}

}
