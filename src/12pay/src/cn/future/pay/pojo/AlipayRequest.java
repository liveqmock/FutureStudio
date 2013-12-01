package cn.future.pay.pojo;

import cn.future.pay.util.AlipayConfig;

public class AlipayRequest {
	private String service="create_direct_pay_by_user";
	private String partner=AlipayConfig.partner;
	private String _input_charset = AlipayConfig.charset;
	private String payment_type = "1";
	private String return_url; //前台回访
	private String notify_url; //后台回调
	private String seller_email;//卖家支付宝账户
	private String out_trade_no;
	private String subject;//订单名称
	private String total_fee;//付款金额
	private String body;//订单描述
	private String show_url;
	private String anti_phishing_key; //防钓鱼时间戳 若要使用请调用类文件submit中的query_timestamp函数
	private String exter_invoke_ip;
}
