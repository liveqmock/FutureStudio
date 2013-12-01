package cn.future.common.pojo;

import java.io.Serializable;
/**
 * priority 优先级基础类，
 * 并提供了依托于该基础类的排序实现
 */
public class Priority implements Serializable{

	private static final long serialVersionUID = 6406043288172910727L;
	protected int priority;
	
	public Priority(){
		
	}
	public Priority(int priority){
		this.priority = priority;
	}
	public int getPriority() {
		return priority;
	}
	public void setPriority(int priority) {
		this.priority = priority;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
