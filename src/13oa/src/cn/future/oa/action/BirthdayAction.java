package cn.future.oa.action;

import java.util.ArrayList;
import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.oa.dto.DAccount;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.service.AccountService;
import cn.future.oa.service.BirthdayService;

public class BirthdayAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2097770723740448528L;
	private String type;
	private String startMonth;
	private String startDay;
	private String endMonth;
	private String endDay;
	private String months;
	private BirthdayService birthdayService;
	private AccountService accountService;
	//set -- get
	private List<DAccount> accounts;
	/**
	 * 用户生日，统计查询
	 * @return
	 */
	public String queryBirthday(){
		List<PAccount> as=new ArrayList<PAccount>();
		if("month".equals(type)){
			String[] m = months.split(",");
			for(String s:m){
				as.addAll(birthdayService.findBirthdayByMonth(s));
			}
			accounts=accountService.transferPAccount(as, 2);
		}else{
			int start=Integer.parseInt(startMonth)*100+Integer.parseInt(startDay);
			int end=Integer.parseInt(endMonth)*100+Integer.parseInt(endDay);
			accounts=accountService.transferPAccount(birthdayService.findBirthdayByRange(start, end),2);
		}
		return SUCCESS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<DAccount> getAccounts() {
		return accounts;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public void setMonths(String months) {
		this.months = months;
	}

	public void setBirthdayService(BirthdayService birthdayService) {
		this.birthdayService = birthdayService;
	}

	public void setAccountService(AccountService accountService) {
		this.accountService = accountService;
	}
	

}
