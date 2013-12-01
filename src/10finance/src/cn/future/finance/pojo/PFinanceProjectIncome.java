package cn.future.finance.pojo;

import java.io.Serializable;
import java.util.Date;

import cn.future.util.StringUtil;
/**
 * 理财收入类
 */
public class PFinanceProjectIncome implements Serializable{

	private static final long serialVersionUID = -5176336623354317383L;
	
	public PFinanceProjectIncome(){
		
	}
	public PFinanceProjectIncome(PProjectReturnPlan plan, PFinanceProjectBidDetail bid, String customerId){
		this.id = StringUtil.getUuid();
		this.version = 1;
		this.financeProjectId = plan.getProjectId();
		this.financeProjectBidId = bid.getId();
		this.projectReturnPlanId = plan.getId();
		this.customerId = customerId;
		this.bidNumber = bid.getCashNumber();
		
		this.percentNumber = plan.getPercentNumber();
		this.percentShow = plan.getPercentShow();
		
		this.typeCode = plan.getTypeCode();
		
		double realIncome =  this.bidNumber * this.percentNumber;
		String incomeString = String.valueOf(realIncome);
		incomeString = incomeString.substring(0, incomeString.indexOf("."));
		this.incomeNumber = Long.parseLong(incomeString);
		
		this.projectMonth = plan.getProjectMonth();
		this.currentMonth = plan.getCurrentMonth();
		this.incomeDate = new Date();
	}
	/**
	 * id 生成策略
	 */
	private String id;
	private int version;
	
	//三个联合主键
	private String financeProjectId;
	private String financeProjectBidId;
	private String projectReturnPlanId;
	
	private String customerId;
	
	private long bidNumber;
	private long incomeNumber;
	
	private int projectMonth;
	private int currentMonth;
	/**
	 * 还款比例，没有百分号
	 */
	private double percentNumber; 
	private String percentShow;
	private int typeCode; //收入类型   1本金 2利息
	private Date incomeDate;

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
	public long getBidNumber() {
		return bidNumber;
	}
	public void setBidNumber(long bidNumber) {
		this.bidNumber = bidNumber;
	}
	public long getIncomeNumber() {
		return incomeNumber;
	}
	public void setIncomeNumber(long incomeNumber) {
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
	public Date getIncomeDate() {
		return incomeDate;
	}
	public void setIncomeDate(Date incomeDate) {
		this.incomeDate = incomeDate;
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
