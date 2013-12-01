package cn.future.pay.service.tenpay.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;

import cn.future.pay.pojo.TenpayRequest;
import cn.future.util.MD5Util;
import cn.future.util.StringUtil;
import cn.future.pay.service.tenpay.TenpayRequestService;
import cn.future.pay.util.TenpayConfig;


public class TenpayRequestServiceImpl implements TenpayRequestService {
	public void genericRequest(){
		
	}
	/** 网关url地址 */
//	private String gateUrl = TenpayConfig.tenpayGateway;
//	private String key = TenpayConfig.partnerKey;
	private String enc="UTF-8";
	/** 密钥 */
	
	/** 请求的参数 */
	private SortedMap parameters = new TreeMap();

	public void setPaymentParams(TenpayRequest payReuqest){
		this.setParameter("partner", payReuqest.getPartner());		        //商户号
		this.setParameter("out_trade_no", payReuqest.getOut_trade_no());		//商家订单号
		this.setParameter("total_fee", String.valueOf(payReuqest.getTotal_fee()));			        //商品金额,以分为单位
		this.setParameter("return_url", payReuqest.getReturn_url());		    //交易完成后跳转的URL
		this.setParameter("notify_url", payReuqest.getNotify_url());		    //接收财付通通知的URL
		this.setParameter("body", payReuqest.getBody());	                    //商品描述
		this.setParameter("bank_type", payReuqest.getBank_type());// "DEFAULT");		    //银行类型(中介担保时此参数无效)
		this.setParameter("spbill_create_ip",payReuqest.getSpbill_create_ip());   //用户的公网ip，不是商户服务器IP
		this.setParameter("fee_type", payReuqest.getFee_type());                    //币种，1人民币
		this.setParameter("subject", payReuqest.getSubject());              //商品名称(中介交易时必填)

		//系统可选参数
		this.setParameter("sign_type", payReuqest.getSign_type());                //签名类型,默认：MD5
		this.setParameter("service_version", payReuqest.getService_version());			//版本号，默认为1.0
		this.setParameter("input_charset", payReuqest.getInput_charset());            //字符编码
		this.setParameter("sign_key_index", payReuqest.getSign_key_index());             //密钥序号


		//业务可选参数
		this.setParameter("attach", payReuqest.getAttach());                      //附加数据，原样返回
		this.setParameter("product_fee", String.valueOf(payReuqest.getProduct_fee()));                 //商品费用，必须保证transport_fee + product_fee=total_fee
		this.setParameter("transport_fee", String.valueOf(payReuqest.getTransport_fee()));               //物流费用，必须保证transport_fee + product_fee=total_fee
		this.setParameter("time_start", payReuqest.getTime_start());            //订单生成时间，格式为yyyymmddhhmmss
		this.setParameter("time_expire", payReuqest.getTime_expire());                 //订单失效时间，格式为yyyymmddhhmmss
		this.setParameter("buyer_id", payReuqest.getBuyer_id());                    //买方财付通账号
		this.setParameter("goods_tag", payReuqest.getGoods_tag());                   //商品标记
		this.setParameter("trade_mode", payReuqest.getTrade_mode());                 //交易模式，1即时到账(默认)，2中介担保，3后台选择（买家进支付中心列表选择）
		this.setParameter("transport_desc", payReuqest.getTransport_desc());              //物流说明
		this.setParameter("trans_type", payReuqest.getTrans_type());                  //交易类型，1实物交易，2虚拟交易
		this.setParameter("agentid", payReuqest.getAgentid());                     //平台ID
		this.setParameter("agent_type", payReuqest.getAgent_type());                  //代理模式，0无代理(默认)，1表示卡易售模式，2表示网店模式
		this.setParameter("seller_id", payReuqest.getSeller_id());                   //卖家商户号，为空则等同于partner
		
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
	
	/**
	 * 设置参数值
	 * @param parameter 参数名称
	 * @param parameterValue 参数值
	 */
	public void setParameter(String parameter, String parameterValue) {
		if(StringUtil.notEmpty(parameterValue)){
			String v = "";
			if(null != parameterValue) {
				v = parameterValue.trim();
			}
			this.parameters.put(parameter, v);
		}
		
	}
	
	/**
	 * 返回所有的参数
	 * @return SortedMap
	 */
	public SortedMap getAllParameters() {		
		return this.parameters;
	}
	
	/* (non-Javadoc)
	 * @see cn.future.pay.service.impl.TenpayRequestService#getRequestURL(cn.future.pay.pojo.TenpayRequest)
	 */
	@Override
	public String getRequestURL(TenpayRequest payRequest) throws UnsupportedEncodingException {
		this.setPaymentParams(payRequest);
		this.createSign(payRequest);
		
		StringBuffer sb = new StringBuffer();
		String enc = "UTF-8";
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			sb.append(k + "=" + URLEncoder.encode(v, enc) + "&");
		}
		
		//去掉最后一个&
		String reqPars = sb.substring(0, sb.lastIndexOf("&"));
		
		return payRequest.getGateway()+ "?" + reqPars;
		
	}
	
	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	protected void createSign(TenpayRequest payRequest) {
		StringBuffer sb = new StringBuffer();
		Set es = this.parameters.entrySet();
		Iterator it = es.iterator();
		while(it.hasNext()) {
			Map.Entry entry = (Map.Entry)it.next();
			String k = (String)entry.getKey();
			String v = (String)entry.getValue();
			if(null != v && !"".equals(v) 
					&& !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + payRequest.getPartnerKey());
		String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();
		
		this.setParameter("sign", sign);
		
		//debug信息  TODO log
		
	}
}
