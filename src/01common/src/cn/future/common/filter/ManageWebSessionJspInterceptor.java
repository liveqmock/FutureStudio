package cn.future.common.filter;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.BeanFactory;

import cn.future.common.service.SessionManageService;
import cn.future.common.service.impl.BeanFactoryHelper;
import cn.future.customer.dto.DCustomer;
import cn.future.util.SessionUtil;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 用户session状态拦截器，拦截Jsp页面
 * @author Tony
 *
 */
public class ManageWebSessionJspInterceptor extends AbstractInterceptor {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1693107857318007954L;

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
			ActionContext ac = arg0.getInvocationContext();
			Map<String,Object> session = ac.getSession();
			
			String url = request.getRequestURI()+(request.getQueryString()==null?"":("?"+request.getQueryString()));
			System.out.println(url);
			
			session.put("callBackUrl", url);
			
			return "sessionFail";
		}
	}

}
