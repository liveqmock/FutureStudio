package cn.future.pay.service.tenpay.impl;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.*;

import cn.future.common.service.impl.ConfigHelperImpl;
import cn.future.pay.exception.PayException;
import cn.future.pay.pojo.TenpayReturn;
import cn.future.util.EncodingUtil;
import cn.future.util.MD5Util;
import cn.future.pay.service.tenpay.TenpayFinishService;
import cn.future.pay.util.TenpayConfig;


public class TenpayFinishServiceImpl implements TenpayFinishService {
	
	//set -- get
	/** 密钥 */
	private String key = ConfigHelperImpl.getProperty("tenpayKey", "");
	private String enc = TenpayConfig.charset;
	private Logger logger = LoggerFactory.getLogger(TenpayFinishServiceImpl.class);
	/** 应答的参数 */
	private SortedMap parameters; 
	
	private HttpServletRequest request;
	
	private String uriEncoding;
	private TenpayReturn tenpayReturn = new TenpayReturn();
	
	/* (non-Javadoc)
	 * @see cn.future.pay.service.impl.TenpayFinishService#initService(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public void initService(HttpServletRequest request)  {
		this.request = request;

		this.parameters = new TreeMap();

		Map m = this.request.getParameterMap();
		Iterator it = m.keySet().iterator();
		while (it.hasNext()) {
			String k = (String) it.next();
			String v = ((String[]) m.get(k))[0];			
			this.setParameter(k, v);
		}
		enc = EncodingUtil.getCharacterEncoding(request);
	}

	/**
	 * 获取参数值
	 * @param parameter 参数名称
	 * @return String 
	 */
	public String getParameter(String parameter) {
		String s = (String)this.parameters.get(parameter); 
		return (null == s) ? "" : s;
	}
	/* (non-Javadoc)
	 * @see cn.future.pay.service.impl.TenpayFinishService#findTenpayReturnInfo()
	 */
	@Override
	public TenpayReturn findTenpayReturnInfo() throws PayException{
		if(this.isTenpaySign()){
			tenpayReturn.setId(this.getParameter("out_trade_no"));
			tenpayReturn.setNotifyId(this.getParameter("notify_id"));
			tenpayReturn.setTransactionId(this.getParameter("transaction_id"));
//			tenpayReturn.setPayTotalFee(this.getParameter("total_fee"));
//			tenpayReturn.setDiscount(this.getParameter("discount"));
//			tenpayReturn.setTradeStateCode(this.getParameter("trade_state"));
			tenpayReturn.setTradeStateName(this.getParameter(""));
			tenpayReturn.setTradeModeCode(this.getParameter("trade_mode"));
			tenpayReturn.setReceiveTime(new Date());
			logger.info("pay"+this.getParameter("attach"));
			logger.info("pay signed");
			//TODO baseDao.saveOrUpdate(tenpayReturn); 写入数据库
			return tenpayReturn;
		}else{
			logger.error("sign fail");
			PayException e = new PayException("支付失败");
			throw e;
		}
		
	}
	/**
	 * 设置参数值
	 * @param parameter 参数名称
	 * @param parameterValue 参数值
	 */
	public void setParameter(String parameter, String parameterValue) {
		String v = "";
		if(null != parameterValue) {
			v = parameterValue.trim();
		}
		this.parameters.put(parameter, v);
	}
	
	/**
	 * 返回所有的参数
	 * @return SortedMap
	 */
	public SortedMap getAllParameters() {
		return this.parameters;
	}
	
	/**
	 * 是否财付通签名,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 * @return boolean
	 */
	public boolean isTenpaySign() {
		StringBuffer sb = new StringBuffer();
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if(!"sign".equals(k) && null != v && !"".equals(v)) {
//				if(!k.equals("PcacheTime")){
					sb.append(k + "=" + v + "&");
//				}
			}
		}
		
		sb.append("key=" + this.key);
		
		//算出摘要
		String sign1 = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();
		
		String tenpaySign = this.getParameter("sign").toLowerCase();
		
		//debug信息
		logger.debug(("sign1:"+sb.toString() + " => sign:" + sign1 +
				" tenpaySign:" + tenpaySign));
		return tenpaySign.equals(sign1);
	}
	
	/**
	 * 返回处理结果给财付通服务器。
	 * @param msg: Success or fail。
	 * @throws IOException 
	 */
	public void sendToCFT(String msg) throws IOException {
		//TODO
//		String strHtml = msg;
//		PrintWriter out = this.getHttpServletResponse().getWriter();
//		out.println(strHtml);
//		out.flush();
//		out.close();

	}
	
	/**
	 * 获取uri编码
	 * @return String
	 */
	public String getUriEncoding() {
		return uriEncoding;
	}

	/**
	 * 设置uri编码
	 * @param uriEncoding
	 * @throws UnsupportedEncodingException
	 */
	public void setUriEncoding(String uriEncoding)
			throws UnsupportedEncodingException {
		if (!"".equals(uriEncoding.trim())) {
			this.uriEncoding = uriEncoding;

			// 编码转换
			Iterator it = this.parameters.keySet().iterator();
			while (it.hasNext()) {
				String k = (String) it.next();
				String v = this.getParameter(k);
				v = new String(v.getBytes(uriEncoding.trim()), enc);
				this.setParameter(k, v);
			}
		}
	}
	
	protected HttpServletRequest getHttpServletRequest() {
		return this.request;
	}
	
}
