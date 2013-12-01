package cn.future.oa.pojo;

import java.util.Date;
import java.util.Set;

import cn.future.common.pojo.BasePriority;
/**
 * 公司类，这个类是一个隐藏的类，用于未来多公司单平台的云计算
 * @author Tony soft-qiyao@foxmail.com
 *
 */
public class PCompany extends BasePriority{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8495510057199082530L;

	/**
	 * 公司名
	 */
	private String name;
	
	private Set<PDepartment> children;
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
	public Set<PDepartment> getChildren() {
		return children;
	}
	public void setChildren(Set<PDepartment> children) {
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
