package cn.future.finance.dto;

import cn.future.finance.pojo.PProjectReturnPlan;
import cn.future.util.TimeUtil;

public class DProjectReturnPlan {
	public DProjectReturnPlan(){
		
	}
	public DProjectReturnPlan(PProjectReturnPlan p){
		this.id = p.getId();
		this.projectId = p.getProjectId();
		this.yearRate = p.getYearRate();
		this.projectMonth = p.getProjectMonth();
		this.currentMonth = p.getCurrentMonth();
		this.returnDate = TimeUtil.date2ShowYMDHMS(p.getReturnDate());
		this.planReturnDate = TimeUtil.date2ShowYMD(p.getPlanReturnDate());
		this.comments = p.getComments();
		this.statusCode = p.getStatusCode();
		switch(p.getStatusCode()){
		case PProjectReturnPlan.RETURN_FAIL:
			this.statusName="还款失败";
			break;
		case PProjectReturnPlan.RETURN_PLAN:
			this.statusName="计划还款";
			break;
		case PProjectReturnPlan.RETURN_RETURNED:
			this.statusName="已还款";
			break;
		}
		
		switch(p.getTypeCode()){
		case 1:
			this.typeName="本金";
			break;
		case 2:
			this.typeName="利息";
			break;
		}
		this.percentShow = p.getPercentShow();
	}
	private String id;
	private String projectId;
	private String projectFlowId;
	private String yearRate;
	private int projectMonth;
	private int currentMonth;
	private String returnDate;
	private String planReturnDate;
	private String comments;
	private int statusCode;
	private String statusName;
	
	/**
	 * 1本金 2利息
	 */
	private String typeName; 
	private String percentShow;//还款比例

	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(String returnDate) {
		this.returnDate = returnDate;
	}
	public String getPlanReturnDate() {
		return planReturnDate;
	}
	public void setPlanReturnDate(String planReturnDate) {
		this.planReturnDate = planReturnDate;
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
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getPercentShow() {
		return percentShow;
	}
	public void setPercentShow(String percentShow) {
		this.percentShow = percentShow;
	}
	public String getProjectFlowId() {
		return projectFlowId;
	}
	public void setProjectFlowId(String projectFlowId) {
		this.projectFlowId = projectFlowId;
	}
	
	
}
