package cn.future.pay.service.tenpay.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.*;

import cn.future.common.service.impl.ConfigHelperImpl;
import cn.future.pay.handler.tenpay.ClientResponseHandler;
import cn.future.pay.pojo.TenpayReturn;
import cn.future.util.MD5Util;
import cn.future.pay.service.tenpay.TenpayCheckService;
import cn.future.pay.util.TenpayConfig;
import cn.future.pay.util.TenpayHttpClient;

public class TenpayCheckServiceImpl implements TenpayCheckService {
	/** 网关url地址 */
	private String gateUrl = TenpayConfig.checkGateway;
	private String enc = "utf-8";
	/** 密钥 */
	private String key = ConfigHelperImpl.getProperty("tenpayKey", "");
	private String partnerId = ConfigHelperImpl.getProperty("tenpayId", "");
	/** 请求的参数 */
	private SortedMap parameters;
	private Logger logger = LoggerFactory.getLogger(TenpayCheckServiceImpl.class);

	/* (non-Javadoc)
	 * @see cn.future.pay.service.impl.TenpayCheckService#initCheck(cn.future.pay.pojo.TenpayReturn)
	 */
	@Override
	public boolean initCheck(TenpayReturn tenpayReturn)
			throws Exception {
		String notifyId = tenpayReturn.getNotifyId();
		this.parameters = new TreeMap();
		this.setParameter("partner", partnerId);
		this.setParameter("notify_id", notifyId);

		TenpayHttpClient httpClient = new TenpayHttpClient();
		// 通信对象
		httpClient.setTimeOut(10);
		// 设置请求内容
		String url = this.getRequestURL();
		httpClient.setReqContent(url);
		if (httpClient.call()) {
			ClientResponseHandler queryRes = new ClientResponseHandler(
					this.key, httpClient.getResContent());

			logger.info(("验证ID返回字符串:" + httpClient.getResContent()));

			// 获取id验证返回状态码，0表示此通知id是财付通发起
			String retcode = queryRes.getParameter("retcode");

			// 判断签名及结果
			if (queryRes.isTenpaySign() && "0".equals(retcode)) {
				System.out.println("id验证成功");
				if ("0".equals(String.valueOf(tenpayReturn.getTradeStateCode()))) {
					

					logger.info("即时到账支付成功");
					// resHandler.sendToCFT("success");
					return true;
				} else {
					logger.info("即时到账支付失败");
					return false;// resHandler.sendToCFT("fail");
				}

			} else {
				// 错误时，返回结果未签名，记录retcode、retmsg看失败详情。
				logger.error(("查询验证签名失败或id验证失败" + ",retcode:"
						+ queryRes.getParameter("retcode")));
				return false;
			}
		} else {
			logger.error("后台调用通信失败");
			logger.error(httpClient.getResponseCode()+"");
			logger.error(httpClient.getErrInfo());
			// 有可能因为网络原因，请求已经处理，但未收到应答。
			return false;
		}
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

		return this.gateUrl + "?" + reqPars;

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
