package cn.future.util;

import javax.servlet.http.HttpServletRequest;

import cn.future.common.pojo.ClientInfo;

public class ClientUtil {
	/**
	 * 获取访客的信息
	 * @param request
	 * @return
	 */
	public static ClientInfo getClientInfo(HttpServletRequest request){
		ClientInfo ci = new ClientInfo();
		ci.setId(StringUtil.getUuid());
		ci.setAddressIp(ClientUtil.getIpAddr(request));
		ci.setRemoteBrowser(ClientUtil.getUserAgent(request));
		return ci;
	}
	public static String getUserAgent(HttpServletRequest request){
		String userAgent = request.getHeader("User-Agent");
		if(!StringUtil.notEmpty(userAgent)){
			userAgent="";
		}
		return userAgent;
	}
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
