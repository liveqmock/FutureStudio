package cn.future.util;

import java.util.HashMap;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	public static final String USERID="ci";
	public static final String USERACCOUNT="ca";
	public static final String USERTOKEN="ct";  //src: ci mix:t.
	public static final String USERTIME="t";
	public static final String USERNAME="cn";
	public static final String USERCOMPANYNAME="cc";
	/**
	 * 通过id和时间生成token值
	 * @param ci
	 * @param usertime
	 * @return
	 */
	public static String findUserToken(String ci,String usertime){
		return StringUtil.sha512Encrypt(ci, usertime);
	}
	/**
	 * 检查cookie是否还有效
	 * @param request
	 * @return
	 */
	public static boolean isCookieActive(HttpServletRequest request){
		HashMap<String,String> cs = CookieUtil.findCookies(request);
		if(cs.get(CookieUtil.USERID)!=null && 
				cs.get(CookieUtil.USERACCOUNT)!=null &&
				cs.get(CookieUtil.USERTOKEN)!=null &&
				cs.get(CookieUtil.USERTIME)!=null
				){
			if(cs.get(CookieUtil.USERTOKEN).equals(StringUtil.sha512Encrypt(cs.get(CookieUtil.USERID), cs.get(CookieUtil.USERTIME))) && 
					!"".equals(cs.get(CookieUtil.USERID)) &&
					!"".equals(cs.get(CookieUtil.USERACCOUNT)) &&
					!"".equals(cs.get(CookieUtil.USERTIME)) &&
					System.currentTimeMillis()<Long.valueOf(cs.get(CookieUtil.USERTIME))
					){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	/**
	 * 当前时间增加30天的值
	 * @return
	 */
	public static String maxTime(){
		long t = System.currentTimeMillis();
		System.out.println(t);
		long x=1000;
		x=x*60;
		x=x*60;
		x=x*24;
		x=x*30;
		long k = t+x;
		String s = String.valueOf(k);
		return s;
	}
	/**
	 * 一次性加入5个cookie变量，通过ci和ca计算ct值
	 * @param response
	 * @param request
	 * @param ci 账户id
	 * @param ca 账户account
	 * @param cn 账户名
	 * @param cc 公司名
	 */
	public static void addAllCookie(HttpServletResponse response, HttpServletRequest request, String ci,String ca, String cn,String cc){
		HashMap<String,String> map = new HashMap<String,String>();
		map.put(CookieUtil.USERACCOUNT, ca);
		map.put(CookieUtil.USERID, ci);
		map.put(CookieUtil.USERNAME, cn);
		map.put(CookieUtil.USERCOMPANYNAME, cc);
		map.put(CookieUtil.USERTOKEN, StringUtil.sha512Encrypt(ca, ci));
		CookieUtil.addCookie( response,  request, map);
	}
	/**
	 * 批量新增cookie, 名字和值，都会被简单加密
	 * @param response
	 * @param map
	 * @return
	 */
	public static void addCookie(HttpServletResponse response, HttpServletRequest request, HashMap<String,String> map){
		for(String s:map.keySet()){			
			response.addCookie(CookieUtil.addCookie(s, map.get(s)));
		}
		
	}
	/**
	 * 获取所有的cookie的值
	 * @param request
	 * @return
	 */
	public static HashMap<String,String> findCookies(HttpServletRequest request){
		HashMap<String,String> map = new HashMap<String,String>();
		Cookie[] cs = request.getCookies();
		if(cs != null){
			for(Cookie c:cs){
				String name=c.getName();
				String value=c.getValue();
				String mn = StringUtil.UrlDecoder(name);
				String mv = StringUtil.UrlDecoder(value);
				map.put(mn,mv);
			}
		}
		return map;
	}
	/**
	 * 新增单个cookie, cookie的有效期定为1个月
	 * @param name
	 * @param value
	 * @return
	 */
	public static Cookie addCookie(String name,String value){
		Cookie c = new Cookie(StringUtil.UrlEncoder(name),StringUtil.UrlEncoder(value));
		c.setMaxAge(60*60*24*30); //30天
		return c;	
	}
	/**
	 * 删除所有的cookie
	 * @param request
	 */
	public static void clearCookie(HttpServletRequest request,HttpServletResponse response){
		Cookie[] cs = request.getCookies();
		if(cs!=null){
			for(Cookie c:cs){
				c.setValue("");
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		
 	}
	/**
	 * 删除指定名称的cookie	
	 * @param request
	 * @param name
	 */
	public static void delCookie(HttpServletRequest request, String name, HttpServletResponse response){
		Cookie[] cs=request.getCookies();
		if(cs!=null){
			for(Cookie c:cs){
			if(name.equals(StringUtil.UrlDecoder(c.getName()))){
				c.setValue("");
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		}
		
	}
}
