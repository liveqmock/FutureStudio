package cn.future.common.action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import cn.future.util.CookieUtil;

import com.opensymphony.xwork2.ActionSupport;

public abstract class BaseAction extends ActionSupport implements ServletRequestAware,ServletResponseAware{
	private static final long serialVersionUID = -1075763262481578015L;
	/**
	 * 错误 响应状态码
	 */
	public static final int FS_ERROR = 500;
	/**
	 * 正确 响应状态码
	 */
	public static final int FS_SUCCESS = 200;
	/**
	 * 返回JSP页面
	 */
	public static final String SUCCESSJSP="successjsp";
	/**
	 * 跳转到其他页面
	 */
	public static final String REDIRECTURL="redirecturl";
	public HttpServletRequest request;
	public HttpServletResponse response;
	
	/**
	 * 清除所有的cookie
	 */
	public void clearCookie(){
		CookieUtil.clearCookie(request, response);
	}
	public String findCookieName(){
		return CookieUtil.findCookies(request).get(CookieUtil.USERNAME);
	}
	/**
	 * 获取用户id
	 * @return
	 */
	public String findCookieId(){
		request.getCookies();
		return CookieUtil.findCookies(request).get(CookieUtil.USERID);
	}
	/**
	 * 获取用户账户
	 * @return
	 */
	public String findCookieAccount(){
		return CookieUtil.findCookies(request).get(CookieUtil.USERACCOUNT);
	}
	/**
	 * cookie时间判断
	 * @return
	 */
	public boolean findCookieTime(){
		long t = Long.parseLong(CookieUtil.findCookies(request).get(CookieUtil.USERTIME));
		if(System.currentTimeMillis()>t){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 预置 返回状态码
	 */
	public void beforeResponse(){
		response.setStatus(FS_ERROR);
	}
	/**
	 * 修改 状态码为成功
	 */
	public void successResponse(){
		response.setStatus(FS_SUCCESS);
	}
	/**
	 * 设置cookie
	 * @param map
	 */
	public void setCookie(HashMap<String,String> map){
		CookieUtil.addCookie(response, request, map);
	};

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		// TODO Auto-generated method stub
		this.response = arg0;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
