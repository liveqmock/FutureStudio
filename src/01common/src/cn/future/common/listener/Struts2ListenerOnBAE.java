package cn.future.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import ognl.OgnlRuntime;

/**
 * 取消OGNL的安全管理
 * @author Administrator
 *
 */
public class Struts2ListenerOnBAE implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		OgnlRuntime.setSecurityManager(null);
	}

}
