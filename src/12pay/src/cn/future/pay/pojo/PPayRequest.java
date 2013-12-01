package cn.future.pay.pojo;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import cn.future.util.StringUtil;
/**
 * 充值记录
 */
public class PPayRequest implements Serializable{
	public static String TRANSTYPE_ENTITY="1";
	public static String TRANSTYPE_VIRTUAL="2";
	private static final long serialVersionUID = 3807213349523695203L;
	/**
	 * 
	 * @param cashAccountId
	 * @param productNumber
	 * @param transNumber
	 * @param businessId 请保证唯一性，如果是null这采用当前id，记账的时候会进行唯一性校验
	 * @param title
	 * @param content
	 * @param userIp
	 * @param trans_type
	 * @param transport_desc
	 * @param bankId 可以是null，如果是null，走统一支付平台
	 */
	public PPayRequest(String cashAccountId, long productNumber, long transNumber,
			String businessId, String title, String content, String userIp,
			String trans_type, String transport_desc, String bankId, int payPlatformCode){
		this.id = StringUtil.serialNumber(20);
		this.version = 1;
		if(bankId==null){
			this.bankId = "";
		}else{
			this.bankId = bankId;
		}
		if(businessId == null){
			this.businessId = this.id;
		}else{
			this.businessId = businessId;
		}
		this.cashAccountId = cashAccountId;
		this.productNumber = productNumber;
		this.transNumber = transNumber;
		this.totalNumber = this.productNumber + this.transNumber;
		this.title = title;
		this.content = content;
		this.userIp = userIp;
		this.transType = trans_type;
		this.transportDesc = transport_desc;
		
		this.payPlatformCode = payPlatformCode;
		this.statusCode = 0;
		Calendar cal = Calendar.getInstance();
		Date current = new Date();
		cal.setTime(current);
		
		this.createDate = cal.getTime();
		cal.add(Calendar.HOUR_OF_DAY, 2);
		this.deadDate = cal.getTime();
		this.finishDate = new Date();
		
	}
	
	private String id;
	private int version;
	private String notifyId;//通知ID
	private String transactionId;//第三方支付订单ID
	private String bankId; //使用的银行编号
	
	private String businessId; //其他系统的业务ID,256位，较长
	private String cashAccountId;
	private long totalNumber;
	private long productNumber;
	private long transNumber;
	private String title;
	private String content;
	private String userIp;
	private String transType;
	private String transportDesc;
	
	private int payPlatformCode;
	private int statusCode;
	private Date createDate;
	private Date deadDate;
	private Date finishDate;
	
	public PPayRequest(){
		
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCashAccountId() {
		return cashAccountId;
	}
	public void setCashAccountId(String cashAccountId) {
		this.cashAccountId = cashAccountId;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public long getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(long totalNumber) {
		this.totalNumber = totalNumber;
	}

	public long getProductNumber() {
		return productNumber;
	}

	public void setProductNumber(long productNumber) {
		this.productNumber = productNumber;
	}

	public long getTransNumber() {
		return transNumber;
	}

	public void setTransNumber(long transNumber) {
		this.transNumber = transNumber;
	}

	public int getStatusCode() {
		return statusCode;
	}


	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}


	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	public Date getDeadDate() {
		return deadDate;
	}
	public void setDeadDate(Date deadDate) {
		this.deadDate = deadDate;
	}
	public String getUserIp() {
		return userIp;
	}
	public void setUserIp(String userIp) {
		this.userIp = userIp;
	}

	public String getTransType() {
		return transType;
	}
	public void setTransType(String transType) {
		this.transType = transType;
	}
	public String getTransportDesc() {
		return transportDesc;
	}
	public void setTransportDesc(String transportDesc) {
		this.transportDesc = transportDesc;
	}
	public String getNotifyId() {
		return notifyId;
	}
	public void setNotifyId(String notifyId) {
		this.notifyId = notifyId;
	}
	public String getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
	}


	public int getPayPlatformCode() {
		return payPlatformCode;
	}


	public void setPayPlatformCode(int payPlatformCode) {
		this.payPlatformCode = payPlatformCode;
	}

}
