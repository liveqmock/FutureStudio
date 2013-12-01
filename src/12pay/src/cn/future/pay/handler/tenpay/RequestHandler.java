package cn.future.pay.handler.tenpay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import cn.future.util.MD5Util;
import cn.future.pay.util.TenpayConfig;


public class RequestHandler {
	/**
	 * 请求处理类 请求处理类继承此类，重写createSign方法即可。
	 */

	/** 网关url地址 */
	private String gateUrl;

	/** 密钥 */
	private String key;

	/** 请求的参数 */
	private SortedMap parameters;
	private String enc = TenpayConfig.charset;

	/**
	 * 构造函数
	 * 
	 * @param request
	 * @param response
	 */
	public RequestHandler(String key, String gateUrl) {

		this.gateUrl = gateUrl;
		this.key = key;
		this.parameters = new TreeMap();
	}

	/**
	 * 设置入口地址,不包含参数值
	 */
	public void setGateUrl(String gateUrl) {
		this.gateUrl = gateUrl;
	}


	/**
	 * 获取参数值
	 * 
	 * @param parameter
	 *            参数名称
	 * @return String
	 */
	public String getParameter(String parameter) {
		String s = (String) this.parameters.get(parameter);
		return (null == s) ? "" : s;
	}

	/**
	 * 设置参数值
	 * 
	 * @param parameter
	 *            参数名称
	 * @param parameterValue
	 *            参数值
	 */
	public void setParameter(String parameter, String parameterValue) {
		String v = "";
		if (null != parameterValue) {
			v = parameterValue.trim();
		}
		this.parameters.put(parameter, v);
	}

	/**
	 * 返回所有的参数
	 * 
	 * @return SortedMap
	 */
	public SortedMap getAllParameters() {
		return this.parameters;
	}

	/**
	 * 获取带参数的请求URL
	 * 
	 * @return String
	 * @throws UnsupportedEncodingException
	 */
	public String getRequestURL() throws UnsupportedEncodingException {

		this.createSign();

		StringBuffer sb = new StringBuffer();
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			sb.append(k + "=" + URLEncoder.encode(v, enc) + "&");
		}

		// 去掉最后一个&
		String reqPars = sb.substring(0, sb.lastIndexOf("&"));

		return this.gateUrl+ "?" + reqPars;

	}

	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	protected void createSign() {
		StringBuffer sb = new StringBuffer();
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k)
					&& !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + this.key);
		String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();

		this.setParameter("sign", sign);
	}
}
