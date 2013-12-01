package cn.future.finance.dto;

import java.io.Serializable;

import cn.future.finance.pojo.PFinanceProjectIncome;
import cn.future.finance.util.FinanceMoneyUtil;
import cn.future.util.TimeUtil;

public class DFinanceProjectIncome implements Serializable {
	private static final long serialVersionUID = -6088650606682318358L;
	
	public DFinanceProjectIncome(){
		
	}
	public DFinanceProjectIncome(PFinanceProjectIncome p){
		this.id = p.getId();
		this.financeProjectId = p.getFinanceProjectId();
		this.financeProjectBidId = p.getFinanceProjectBidId();
		this.projectReturnPlanId = p.getProjectReturnPlanId();
		this.customerId = p.getCustomerId();
		this.bidNumber = FinanceMoneyUtil.formatThreeSplitFen(p.getBidNumber());
		this.incomeNumber = FinanceMoneyUtil.formatThreeSplitFen(p.getIncomeNumber());
		this.projectMonth = p.getProjectMonth();
		this.currentMonth = p.getCurrentMonth();
		this.percentNumber = p.getPercentNumber();
		this.percentShow = p.getPercentShow();
		if(p.getTypeCode()==1){
			this.typeName = "本金";
		}else if(p.getTypeCode()==2){
			this.typeName = "利息";
		}
		this.incomeDate = TimeUtil.date2ShowYMDHMS(p.getIncomeDate());
	}
	/**
	 * id 生成策略
	 */
	private String id;
	
	//三个联合主键
	private String financeProjectId;
	private String financeProjectFlowId;
	private String financeProjectBidId;
	private String projectReturnPlanId;
	private String customerId;
	
	private String bidNumber;
	private String incomeNumber;
	
	private int projectMonth;
	private int currentMonth;
	/**
	 * 还款比例，没有百分号
	 */
	private double percentNumber; 
	private String percentShow;
	private String typeName;
	private String incomeDate;

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFinanceProjectId() {
		return financeProjectId;
	}
	public void setFinanceProjectId(String financeProjectId) {
		this.financeProjectId = financeProjectId;
	}
	public String getFinanceProjectBidId() {
		return financeProjectBidId;
	}
	public void setFinanceProjectBidId(String financeProjectBidId) {
		this.financeProjectBidId = financeProjectBidId;
	}
	public String getProjectReturnPlanId() {
		return projectReturnPlanId;
	}
	public void setProjectReturnPlanId(String projectReturnPlanId) {
		this.projectReturnPlanId = projectReturnPlanId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBidNumber() {
		return bidNumber;
	}
	public void setBidNumber(String bidNumber) {
		this.bidNumber = bidNumber;
	}
	public String getIncomeNumber() {
		return incomeNumber;
	}
	public void setIncomeNumber(String incomeNumber) {
		this.incomeNumber = incomeNumber;
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
	public String getIncomeDate() {
		return incomeDate;
	}
	public void setIncomeDate(String incomeDate) {
		this.incomeDate = incomeDate;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getFinanceProjectFlowId() {
		return financeProjectFlowId;
	}
	public void setFinanceProjectFlowId(String financeProjectFlowId) {
		this.financeProjectFlowId = financeProjectFlowId;
	}
	
	
}
