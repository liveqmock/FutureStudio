package cn.future.finance.pojo;

import java.io.Serializable;
import java.util.Date;

import cn.future.util.StringUtil;
/**
 * 根据项目生成返款计划
 */
public class PProjectReturnPlan implements Serializable{

	private static final long serialVersionUID = 2893991118768622926L;
	/**
	 * 计划还款
	 */
	public static final int RETURN_PLAN = 0;
	/**
	 * 已还款
	 */
	public static final int RETURN_RETURNED = 10;
	/**
	 * 还款失败
	 */
	public static final int RETURN_FAIL = -10;
	/**
	 * 新增构造函数
	 * @param p 项目
	 * @param currentMonth 还款周期月
	 * @param returnDate 还款时间
	 * @param statusCode 状态
	 * @param comments 备注信息
	 */
	public PProjectReturnPlan(PBaseProject p,
			int currentMonth,
			Date returnDate,
			int statusCode,
			int typeCode,
			double percentNumber,
			String percentShow,
			String comments){
		this.id = StringUtil.getUuid();
		this.version = 1;
		this.projectId = p.getId();
		this.yearRate = p.getYearRate();
		this.projectMonth = p.getProjectMonth();
		this.currentMonth = currentMonth;
		this.returnDate = returnDate;
		this.planReturnDate = returnDate;
		this.percentNumber = percentNumber;
		this.percentShow = percentShow;
		this.comments = comments;
		this.statusCode = statusCode;
		this.typeCode = typeCode;
	}
	public PProjectReturnPlan(){
		
	}
	private String id;
	private int version;
	private String projectId;
	private String yearRate;
	private int projectMonth;
	private int currentMonth;
	private Date returnDate;
	private Date planReturnDate;
	private String comments;
	private int statusCode;
	/**
	 * 1本金 2利息
	 */
	private int typeCode; 
	/**
	 * 还款比例，没有百分号
	 */
	private double percentNumber; 
	private String percentShow;


	public double getPercentNumber() {
		return percentNumber;
	}
	public void setPercentNumber(double percentNumber) {
		this.percentNumber = percentNumber;
	}
	public String getPercentShow() {
		return percentShow;
	}
	public void setPercentShow(String percentShow) {
		this.percentShow = percentShow;
	}
	public Date getPlanReturnDate() {
		return planReturnDate;
	}
	public void setPlanReturnDate(Date planReturnDate) {
		this.planReturnDate = planReturnDate;
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
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
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
	public int getCurrentMonth() {
		return currentMonth;
	}
	public void setCurrentMonth(int currentMonth) {
		this.currentMonth = currentMonth;
	}
	public Date getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public int getTypeCode() {
		return typeCode;
	}
	public void setTypeCode(int typeCode) {
		this.typeCode = typeCode;
	}
	
	
}
