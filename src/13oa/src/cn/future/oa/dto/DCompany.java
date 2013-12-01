package cn.future.oa.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import cn.future.common.pojo.BasePriority;
import cn.future.oa.pojo.PCompany;
import cn.future.oa.pojo.PDepartment;

public class DCompany extends BasePriority{
	public DCompany(PCompany d, boolean fetchChildren){
		if(fetchChildren){
			Set<PDepartment> setD = d.getChildren();
			this.children=new ArrayList<DDepartment>();
			for(PDepartment i : setD){
				this.children.add(new DDepartment(i,true));
			}
		}
		this.id=d.getId();
		this.bossId=d.getBossId();
		this.priority=d.getPriority();
		this.comments=d.getComments();
		this.name=d.getName();
		this.serviceEnd=d.getServiceEnd();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 6640928918308837147L;

	private String name;
	
	private List<DDepartment> children;
	private Date serviceEnd;
	private String bossId;
	
	
	
	public String getBossId() {
		return bossId;
	}
	public void setBossId(String bossId) {
		this.bossId = bossId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DDepartment> getChildren() {
		return children;
	}
	public void setChildren(List<DDepartment> children) {
		this.children = children;
	}

	public Date getServiceEnd() {
		return serviceEnd;
	}
	public void setServiceEnd(Date serviceEnd) {
		this.serviceEnd = serviceEnd;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
