package cn.future.security.dto;

import cn.future.security.pojo.PSecurityCode;
import cn.future.util.TimeUtil;

public class DSecurityCode {
	public DSecurityCode(){
		
	}
	public DSecurityCode(PSecurityCode p){
		this.id = p.getId();
		this.code = p.getCode();
		this.createDate = TimeUtil.date2ShowYMDHMS(p.getCreateDate());
		this.deadDate = TimeUtil.date2ShowYMDHMS(p.getDeadDate());
		this.available = p.getAvailable() > 0 ? "可用":"已失效";
	}
	private String id;
	private String code;
	private String createDate;
	private String deadDate;
	private String available;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getDeadDate() {
		return deadDate;
	}
	public void setDeadDate(String deadDate) {
		this.deadDate = deadDate;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	
	
}
