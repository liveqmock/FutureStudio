package cn.future.common.filter;

import org.apache.struts2.ServletActionContext;

import cn.future.util.CookieUtil;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
/**
 * 用户Cookie状态拦截器，因为拦截的是后端Web管理系统，所以这里返回的是jsp页面
 * @author Tony
 *
 */
public class ManageWebCookieJspInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 3312890157245642274L;

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		if(CookieUtil.isCookieActive(ServletActionContext.getRequest())){
			return arg0.invoke();
		}else{
			return "sign";
		}
	}

}
