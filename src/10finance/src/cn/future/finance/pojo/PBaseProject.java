package cn.future.finance.pojo;

import java.io.Serializable;
import java.util.Date;

import cn.future.finance.util.FinanceCodeUtil;
import cn.future.oa.dto.DAccount;
import cn.future.util.StringUtil;

public class PBaseProject implements Serializable {
	
	private static final long serialVersionUID = -7471569812688616804L;
	private String id;  //real key 
	private int version; //db transaction
	/**
	 * 业务ID,也提供查询
	 */
	private String flowId;
	private String userId;
	/**
	 * 短期  长期
	 */
	private String timeType;
	/**
	 * 项目标题
	 */
	private String title;
	/**
	 * 项目金额,单位:分
	 */
	private long financeNumber;
	private long alreadyNumber;
	private int bidTimes; 

	private String content;
	private String contentComments;
	private String comments;
	
	private String yearRate;//年化利率
	private int  projectMonth;//结款多少月？

	private int returnTypeCode;//还款类型
	private String returnInfo;//还款信息   每月利息1%之类
	
	private int projectTypeCode; // 抵押 1 , 担保 2 , 自主信用 3,
	
	private long minNumber=50; //0 不控制
	private long stepNumber=1; //100 一百一跳
	private long maxNumber=0; //0 不控制
	
	private Date startDate;
	private Date finishDate;
	private Date deadDate;
	/**
	 * 关闭状态， 对内显示
	 * 10 满标，系统关闭    
	 * 5时间到系统关闭   
	 * 1管理员关闭
	 */
	private String closedBy;// 
	/**
	 * 谁关闭的项目
	 * 0 系统  
	 * 或者userid
	 */
	private String closedId;
	/**
	 * 项目状态，对外显示
	 * 初始化为招标中
	 */
	private int statusCode = FinanceCodeUtil.BIDDING;
	
	public PBaseProject(){
		
	}
	/**
	 * 新建项目基础信息
	 * @param cus 客户
	 * @param timeType 短期or长期
	 * @param title 标题
	 * @param financeNumber 项目总金额
	 * @param content 文本内容
	 * @param contentComments 文本内容2
	 * @param comments 备注
	 * @param yearRate 年化利率，正整数
	 * @param projectMonth 借款月数
	 * @param returnTypeCode 还款类型编码
	 * @param returnInfo 还款信息说明
	 * @param projectTypeCode 项目类型编码
	 * @param minNumber 最小投资额
	 * @param stepNumber 步进
	 * @param maxNumber 最大投资额
	 * @param startDate 开始时间
	 * @param deadDate 结束时间
	 * @param statusCode 项目状态
	 */
	public PBaseProject(DAccount account, String timeType, String title, Long financeNumber,
			String content, String contentComments, String comments, String yearRate, int projectMonth,
			int returnTypeCode, String returnInfo, int projectTypeCode, long minNumber, long stepNumber,
			long maxNumber, Date startDate, Date deadDate, int statusCode
			){
		
		this.userId=account.getId();
		this.timeType = timeType;
		this.title=title;
		this.financeNumber=financeNumber;
		
		this.content=content;
		this.contentComments=contentComments;
		this.comments=comments;
		
		this.yearRate=yearRate;//年化利率
		this.projectMonth=projectMonth;//结款多少月？

		this.returnTypeCode=returnTypeCode;//还款类型
		this.returnInfo=returnInfo;//还款信息   每月利息1%之类
		this.projectTypeCode=projectTypeCode; // 抵押 1 , 担保 2 , 自主信用 3,
		
		this.minNumber=minNumber; //0 不控制
		this.stepNumber=stepNumber; //100 一百一跳
		this.maxNumber=maxNumber; //0 不控制
		
		this.startDate=startDate;
		this.finishDate=null;
		this.deadDate=deadDate;
		
		this.statusCode = statusCode;
		this.initBase();
	}
	public void initBase(){
		this.id=StringUtil.getUuid(); 
		this.version=1;
		this.flowId=StringUtil.serialNumber();
		this.alreadyNumber=0;
		this.bidTimes=0; 
		this.closedBy="";
		this.closedId="";
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getFlowId() {
		return flowId;
	}
	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public long getFinanceNumber() {
		return financeNumber;
	}
	public void setFinanceNumber(long financeNumber) {
		this.financeNumber = financeNumber;
	}
	public long getAlreadyNumber() {
		return alreadyNumber;
	}
	public void setAlreadyNumber(long alreadyNumber) {
		this.alreadyNumber = alreadyNumber;
	}
	public int getBidTimes() {
		return bidTimes;
	}
	public void setBidTimes(int bidTimes) {
		this.bidTimes = bidTimes;
	}
	public String getTimeType() {
		return timeType;
	}
	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}
	public String getYearRate() {
		return yearRate;
	}
	public void setYearRate(String yearRate) {
		this.yearRate = yearRate;
	}
	public int getProjectMonth() {
		return projectMonth;
	}
	public void setProjectMonth(int projectMonth) {
		this.projectMonth = projectMonth;
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
	public String getContentComments() {
		return contentComments;
	}
	public void setContentComments(String contentComments) {
		this.contentComments = contentComments;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getReturnTypeCode() {
		return returnTypeCode;
	}
	public void setReturnTypeCode(int returnTypeCode) {
		this.returnTypeCode = returnTypeCode;
	}
	public String getReturnInfo() {
		return returnInfo;
	}
	public void setReturnInfo(String returnInfo) {
		this.returnInfo = returnInfo;
	}
	public int getProjectTypeCode() {
		return projectTypeCode;
	}
	public void setProjectTypeCode(int projectTypeCode) {
		this.projectTypeCode = projectTypeCode;
	}
	
	public long getMinNumber() {
		return minNumber;
	}
	public void setMinNumber(long minNumber) {
		this.minNumber = minNumber;
	}
	public long getStepNumber() {
		return stepNumber;
	}
	public void setStepNumber(long stepNumber) {
		this.stepNumber = stepNumber;
	}
	public long getMaxNumber() {
		return maxNumber;
	}
	public void setMaxNumber(long maxNumber) {
		this.maxNumber = maxNumber;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getFinishDate() {
		return finishDate;
	}
	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}
	public Date getDeadDate() {
		return deadDate;
	}
	public void setDeadDate(Date deadDate) {
		this.deadDate = deadDate;
	}
	public String getClosedBy() {
		return closedBy;
	}
	public void setClosedBy(String closedBy) {
		this.closedBy = closedBy;
	}
	public String getClosedId() {
		return closedId;
	}
	public void setClosedId(String closedId) {
		this.closedId = closedId;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	
	
}
