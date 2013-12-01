package cn.future.weixin.pojo;

import java.util.ArrayList;

import cn.future.weixin.exception.WeixinButtonLengthException;

public class PWeixinButton {
	private String name;
	private ArrayList<PWeixinSubButton> sub_button;
	
	public PWeixinButton(){
		super();
	}
	public PWeixinButton(String name, ArrayList<PWeixinSubButton> sub_button) throws WeixinButtonLengthException{
		super();
		this.name = name;
		if(sub_button == null || sub_button.size() < 1 || sub_button.size() > 5){
			WeixinButtonLengthException e = new WeixinButtonLengthException("子菜单数量应在1-5之间");
			throw e;
		}
		this.sub_button = sub_button;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<PWeixinSubButton> getSub_button() {
		return sub_button;
	}
	public void setSub_button(ArrayList<PWeixinSubButton> sub_button) {
		this.sub_button = sub_button;
	}
	
	
}
