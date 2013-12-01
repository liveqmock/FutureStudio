package cn.future.pay.pojo;

import java.io.Serializable;

public class TenpayRequest implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5750725128893771372L;
	//支付参数
	private String orderId;// 业务系统核心ID，根据具体的业务类型才知道
	private String gateway;
	private	String partner; //商户号
	private String partnerName; //商户名
	private String partnerKey;
	private String out_trade_no;  //商户订单号
	private long total_fee; //总金额，单位：分
	private String return_url=""; //前台回访
	private String notify_url=""; //后台回调
	private String body;//商品描述
	private String bank_type = "DEFAULT";//银行类型(中介担保时此参数无效)
	private String spbill_create_ip;//用户ID地址 request.getRemoteAddr()
	private String fee_type = "1"; //币种：人民币
	private String subject;//中介交易必填
	
	//系统可选参数
	private String sign_type = "MD5";
	private String service_version = "1.0";
	private String input_charset = "UTF-8";
	private String sign_key_index = "1";

	//业务参数
	private String attach;//附加数据，原样返回
	private long product_fee; //商品费用，必须保证transport_fee + product_fee=total_fee
	private long transport_fee;//物流费用，必须保证transport_fee + product_fee=total_fee
	private String time_start;  //订单生成时间，格式为yyyymmddhhmmss
	private	String time_expire; //失效时间
	private String buyer_id;   //财付通帐号
	private String goods_tag;//商品标记
	private	String trade_mode = "1";//1即时到账(默认)，2中介担保，
	
	private String transport_desc;//物流说明
	private String trans_type;                //交易类型，1实物交易，2虚拟交易
	
	private String agentid;                     //平台ID
	private String agent_type;                  //代理模式，0无代理(默认)，1表示卡易售模式，2表示网店模式
	private String seller_id;                   //卖家商户号，为空则等同于partner
	
	
	public long getTotal_fee() {
		return total_fee;
	}
	public void setTotal_fee(long total_fee) {
		this.total_fee = total_fee;
	}
	public long getProduct_fee() {
		return product_fee;
	}
	public void setProduct_fee(long product_fee) {
		this.product_fee = product_fee;
	}
	public long getTransport_fee() {
		return transport_fee;
	}
	public void setTransport_fee(long transport_fee) {
		this.transport_fee = transport_fee;
	}
	public String getGateway() {
		return gateway;
	}
	public void setGateway(String gateway) {
		this.gateway = gateway;
	}
	public String getPartner() {
		return partner;
	}
	public void setPartner(String partner) {
		this.partner = partner;
	}
	
	public String getPartnerKey() {
		return partnerKey;
	}
	public void setPartnerKey(String partnerKey) {
		this.partnerKey = partnerKey;
	}
	public String getOut_trade_no() {
		return out_trade_no;
	}
	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getReturn_url() {
		return return_url;
	}
	public void setReturn_url(String return_url) {
		this.return_url = return_url;
	}
	public String getNotify_url() {
		return notify_url;
	}
	public void setNotify_url(String notify_url) {
		this.notify_url = notify_url;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getBank_type() {
		return bank_type;
	}
	public void setBank_type(String bank_type) {
		this.bank_type = bank_type;
	}
	public String getSpbill_create_ip() {
		return spbill_create_ip;
	}
	public void setSpbill_create_ip(String spbill_create_ip) {
		this.spbill_create_ip = spbill_create_ip;
	}
	public String getFee_type() {
		return fee_type;
	}
	public void setFee_type(String fee_type) {
		this.fee_type = fee_type;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getSign_type() {
		return sign_type;
	}
	public void setSign_type(String sign_type) {
		this.sign_type = sign_type;
	}
	public String getService_version() {
		return service_version;
	}
	public void setService_version(String service_version) {
		this.service_version = service_version;
	}
	public String getInput_charset() {
		return input_charset;
	}
	public void setInput_charset(String input_charset) {
		this.input_charset = input_charset;
	}
	public String getSign_key_index() {
		return sign_key_index;
	}
	public void setSign_key_index(String sign_key_index) {
		this.sign_key_index = sign_key_index;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	

	public String getTime_start() {
		return time_start;
	}
	public void setTime_start(String time_start) {
		this.time_start = time_start;
	}
	public String getTime_expire() {
		return time_expire;
	}
	public void setTime_expire(String time_expire) {
		this.time_expire = time_expire;
	}
	public String getBuyer_id() {
		return buyer_id;
	}
	public void setBuyer_id(String buyer_id) {
		this.buyer_id = buyer_id;
	}
	public String getGoods_tag() {
		return goods_tag;
	}
	public void setGoods_tag(String goods_tag) {
		this.goods_tag = goods_tag;
	}
	public String getTrade_mode() {
		return trade_mode;
	}
	public void setTrade_mode(String trade_mode) {
		this.trade_mode = trade_mode;
	}
	public String getTransport_desc() {
		return transport_desc;
	}
	public void setTransport_desc(String transport_desc) {
		this.transport_desc = transport_desc;
	}
	public String getTrans_type() {
		return trans_type;
	}
	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}
	public String getAgentid() {
		return agentid;
	}
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}
	public String getAgent_type() {
		return agent_type;
	}
	public void setAgent_type(String agent_type) {
		this.agent_type = agent_type;
	}
	public String getSeller_id() {
		return seller_id;
	}
	public void setSeller_id(String seller_id) {
		this.seller_id = seller_id;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	
}
