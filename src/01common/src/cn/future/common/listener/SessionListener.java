package cn.future.common.listener;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.springframework.beans.factory.BeanFactory;

import cn.future.common.service.SessionManageService;
import cn.future.common.service.impl.BeanFactoryHelper;
import cn.future.customer.dto.DCustomer;
import cn.future.util.SessionUtil;

public class SessionListener  implements HttpSessionListener,HttpSessionAttributeListener{

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {
		if (SessionUtil.SESSION_CUSTOMER_KEY.equals(se.getName())) {  
			DCustomer memberInfo = (DCustomer) se.getValue();  
			BeanFactory bf = BeanFactoryHelper.getFactory();
			SessionManageService sessionManageService = bf.getBean("sessionManageService", SessionManageService.class);
			sessionManageService.addSessionCustomer(memberInfo);
            se.getSession().setMaxInactiveInterval(60 * 30);// 失效时间SEC * MINS  
        }  
		
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {
		if (SessionUtil.SESSION_CUSTOMER_KEY.equals(se.getName())) {  
			
			DCustomer memberInfo = (DCustomer) se.getValue();  
			BeanFactory bf = BeanFactoryHelper.getFactory();
			SessionManageService sessionManageService = bf.getBean("sessionManageService", SessionManageService.class);
			sessionManageService.removeSessionCustomer(memberInfo);
            memberInfo = null;  
        }  
		
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
