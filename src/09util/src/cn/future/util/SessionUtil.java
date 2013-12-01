package cn.future.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.future.customer.dto.DCustomer;
import cn.future.common.exception.CastException;
import cn.future.common.exception.NotFindException;

public class SessionUtil{
	public static final String SESSION_CUSTOMER_KEY="sessioncustomer";
	/**
	 * 添加Session数据
	 * @param request
	 * @param key
	 * @param value
	 */
	public static void setAttribute(HttpServletRequest request, String key, Object value){
		HttpSession session = request.getSession();
		Object o = session.getAttribute(key);
		if(null!=o){
			clearSessionAttribute(request, key);
		}
		session.setAttribute(key, value);
	}
	/**
	 * 设置用户信息到session，如果已经存在了，那么先清除再写
	 * @param request
	 * @param customer
	 */
	public static void setSession(HttpServletRequest request, DCustomer customer){
		HttpSession session=request.getSession();
		Object o = session.getAttribute(SESSION_CUSTOMER_KEY);
		if(o!=null){
			clearSessionCustomer(request);
		}
		session.setAttribute(SESSION_CUSTOMER_KEY, customer);
	}
	/**
	 * 判断session中的用户是否可用,用于是否登陆的判断标准
	 * @param request
	 * @return
	 */
	public static boolean isCustomerActive(HttpServletRequest request){
		DCustomer customer = SessionUtil.getSessionCustomer(request);
		if(customer == null){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 获取在session中的用户
	 * @param request
	 * @return
	 */
	public static DCustomer getSessionCustomer(HttpServletRequest request){
		DCustomer customer = null;
		try {
			customer = SessionUtil.getSessionAttr(DCustomer.class, request, SESSION_CUSTOMER_KEY);
		} catch (NotFindException e) {
		} catch (CastException e) {
		}
		return customer;
	}
	/**
	 * 清空session中的客户信息
	 * @param request
	 */
	public static void clearSessionCustomer(HttpServletRequest request){
		request.getSession().removeAttribute(SESSION_CUSTOMER_KEY);
	}
	/**
	 * 移除指定的Session信息
	 * @param request
	 * @param key
	 */
	public static void clearSessionAttribute(HttpServletRequest request, String key){
		request.getSession().removeAttribute(key);
	}
	/**
	 * 查找session中的key
	 * @param request
	 * @param key
	 * @return
	 * @throws NotFindException
	 * @throws CastException 
	 */
	public static <T> T getSessionAttr(Class<T> type, HttpServletRequest request, String key) throws NotFindException, CastException{
		HttpSession session=request.getSession();
		if(session==null){
			NotFindException  e = new NotFindException("没有找到session");
			throw e;
		}
		Object o = session.getAttribute(key);
		if(o==null){
			NotFindException  e = new NotFindException(key+"参数值在session中未找到");
			throw e;
		}
		try{
			@SuppressWarnings("unchecked")
			T t = (T)o;
			return t;
		}catch(Exception e){
			CastException e2 = new CastException("Session中对象转换出错,key:"+key);
			throw e2;
		}
	}
}
