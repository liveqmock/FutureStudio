package cn.future.finance.dto;

import cn.future.finance.pojo.PCreditRequest;
import cn.future.finance.util.CreditRequestCodeUtil;
import cn.future.util.TimeUtil;

public class DCreditRequest {
	public DCreditRequest(){
		
	}
	public DCreditRequest(PCreditRequest p){
		this.id = p.getId();
		this.userId = p.getUserId();
		this.position = p.getPosition();
		this.comments = p.getComments();
		this.title = p.getTitle();
		this.useFor = p.getUseFor();
		this.number = p.getNumber();
		this.month = p.getMonth();
		this.yearRate = p.getYearRate();
		this.returnTypeName = p.getReturnTypeName();
		this.bidTime = p.getBidTime();
		this.approvalComments = p.getApprovalComments();
		this.bankCard = p.getBankCard();
		this.addDate = TimeUtil.date2ShowYMDHMS(p.getAddDate());
		this.statusCode = p.getStatusCode();
		this.statusName = CreditRequestCodeUtil.getName(this.statusCode);
		
	}
	private String id;
	private String userId; //创建人ID
	private String position;//c客户 or u用户
	private String comments;//备注信息
	private String title;//标题
	private String useFor;//借款用途
	private String number;//借款金额
	private String month;//借款期限
	private String yearRate;//年利率
	private String returnTypeName;//还款方式 等额本息|每月付息到期还本
	private String bidTime;//筹措时间
	private String approvalComments;//审批意见
	private String bankCard;//银行卡
	
	private String addDate;
	private int statusCode;
	private String statusName;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getApprovalComments() {
		return approvalComments;
	}
	public void setApprovalComments(String approvalComments) {
		this.approvalComments = approvalComments;
	}
	public String getBankCard() {
		return bankCard;
	}
	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}
	public String getAddDate() {
		return addDate;
	}
	public void setAddDate(String addDate) {
		this.addDate = addDate;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getUseFor() {
		return useFor;
	}
	public void setUseFor(String useFor) {
		this.useFor = useFor;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getYearRate() {
		return yearRate;
	}
	public void setYearRate(String yearRate) {
		this.yearRate = yearRate;
	}
	public String getReturnTypeName() {
		return returnTypeName;
	}
	public void setReturnTypeName(String returnTypeName) {
		this.returnTypeName = returnTypeName;
	}
	public String getBidTime() {
		return bidTime;
	}
	public void setBidTime(String bidTime) {
		this.bidTime = bidTime;
	}
	
}
