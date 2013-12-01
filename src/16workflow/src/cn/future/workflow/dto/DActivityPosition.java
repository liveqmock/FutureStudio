package cn.future.workflow.dto;

import java.io.Serializable;

import org.jbpm.api.model.ActivityCoordinates;

public class DActivityPosition implements Serializable{
	private static final long serialVersionUID = 715827170128768929L;
	
	public DActivityPosition(){
		
	}
	public DActivityPosition(ActivityCoordinates d){
		this.x=d.getX();
		this.y=d.getY();
		this.width=d.getWidth();
		this.height=d.getHeight();
	}
	private int x;
	private int y;
	private int width;
	private int height;
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	


}
