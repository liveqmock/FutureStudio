package cn.future.pay.action;

import java.io.IOException;

import cn.future.common.action.BaseAction;
import cn.future.customer.dto.DCustomer;
import cn.future.pay.pojo.PPayRequest;
import cn.future.pay.service.tenpay.TenpayPayService;
import cn.future.util.SessionUtil;


public class TenpayAction extends BaseAction{
	private static final long serialVersionUID = -2621714188214260628L;
	
	//set
	private TenpayPayService tenpayPayService;
	//付款完成后的回调地址
	private String returnUrl;
	//商品价格
	private long productNumber=0;
	//物流价格
	private long transNumber=0;
	//业务ID
	private String businessId="";
	//标题
	private String title="";
	//商品描述
	private String content="";
	//商品分类，无需
	private String trans_type=PPayRequest.TRANSTYPE_ENTITY;
	//物流描述
	private String transport_desc="";
	//get
	private String requestUrl;
	private String paySystem;
	private String jspPage;
	
	public String init() throws IOException{
		paySystem="tenpay";
		jspPage="pay_request.jsp";
		String userIp = request.getRemoteAddr();
		DCustomer customer = SessionUtil.getSessionCustomer(request);
		requestUrl = tenpayPayService.initRedirectUrl(customer, productNumber, transNumber,
				businessId, title, content, userIp,
				trans_type, transport_desc, returnUrl);
		return REDIRECTURL;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getPaySystem() {
		return paySystem;
	}
	public String getJspPage() {
		return jspPage;
	}
	public String getRequestUrl() {
		return requestUrl;
	}
	public void setTenpayPayService(TenpayPayService tenpayPayService) {
		this.tenpayPayService = tenpayPayService;
	}
	public void setProductNumber(long productNumber) {
		this.productNumber = productNumber;
	}
	public void setTransNumber(long transNumber) {
		this.transNumber = transNumber;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setTrans_type(String trans_type) {
		this.trans_type = trans_type;
	}
	public void setTransport_desc(String transport_desc) {
		this.transport_desc = transport_desc;
	}
	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

}
