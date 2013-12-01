package cn.future.file.pojo;

import java.io.Serializable;
import java.util.Date;

import cn.future.util.StringUtil;
import cn.future.util.TimeUtil;

/**
 * 采用DFile是为了和java io的file区别开来
 * @author Tony soft-qiyao@foxmail.com
 *
 */
public class PFile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8761234808193667932L;
	private String id;  //文件的id，这个id也对应于BCS的文件ID
	private String accountid; //文件属主
	private String directoryid;//文件所在目录
	private String name;
	private String pinyin;
	private String comments;
	private String addTime;//添加时间
	private String lastRead;//最后读取时间
	private String type;//文件类型，根据后缀名来确定的
	private long size; //文件大小
	
	public PFile(){
		
	}
	public PFile(String accountId,String directoryId, String name, String comments, String type, long size){
		this.id = StringUtil.getUuid();
		this.accountid = accountId;
		this.name = name;
		this.pinyin = StringUtil.getPinyinString(name);
		this.comments = comments;
		this.addTime = TimeUtil.dateToYMDHMSString(new Date());
		this.lastRead = this.addTime;
		this.type = type;
		this.size = size;
	}
	

	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccountid() {
		return accountid;
	}
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}
	public String getDirectoryid() {
		return directoryid;
	}
	public void setDirectoryid(String directoryid) {
		this.directoryid = directoryid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPinyin() {
		return pinyin;
	}
	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getAddTime() {
		return addTime;
	}
	public void setAddTime(String addTime) {
		this.addTime = addTime;
	}
	public String getLastRead() {
		return lastRead;
	}
	public void setLastRead(String lastRead) {
		this.lastRead = lastRead;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
