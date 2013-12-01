package cn.future.pay.dto;

import cn.future.finance.util.FinanceMoneyUtil;
import cn.future.pay.pojo.PCashAccountDetail;
import cn.future.util.TimeUtil;

public class DCashAccountDetail {
	public DCashAccountDetail(){
		
	}
	public DCashAccountDetail(PCashAccountDetail p){
		this.id = p.getId();
		this.businessId = p.getBusinessId();
		this.oldNumber = FinanceMoneyUtil.formatThreeSplitFen(p.getOldNumber());
		this.cashNumber = FinanceMoneyUtil.formatThreeSplitFen(p.getCashNumber());
		this.newNumber = FinanceMoneyUtil.formatThreeSplitFen(p.getNewNumber());
		this.cashOperate = p.getCashOperate();
		this.updateDate = TimeUtil.date2ShowYMDHMS(p.getUpdateDate());
		this.title = p.getTitle();
		this.content = p.getContent();
	}
	// 需要事务
	private String id; // 系统ID -- 记账系统id
	private String businessId; // 唯一键
	// 进行入账的id, 不同的id有不同的生成策略
	// 长度暂定是256 不能超过256位
	// 各自使用不同的业务ID策略进行唯一校验

	private String oldNumber;
	private String cashNumber; // 都是正数 单位分
	private String newNumber;
	
	private String cashOperate; // - +
	private String updateDate;
	private String title;
	private String content;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBusinessId() {
		return businessId;
	}
	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getOldNumber() {
		return oldNumber;
	}
	public void setOldNumber(String oldNumber) {
		this.oldNumber = oldNumber;
	}
	public String getCashNumber() {
		return cashNumber;
	}
	public void setCashNumber(String cashNumber) {
		this.cashNumber = cashNumber;
	}
	public String getNewNumber() {
		return newNumber;
	}
	public void setNewNumber(String newNumber) {
		this.newNumber = newNumber;
	}
	public String getCashOperate() {
		return cashOperate;
	}
	public void setCashOperate(String cashOperate) {
		this.cashOperate = cashOperate;
	}
	public String getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
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
	
}
