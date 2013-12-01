package cn.future.weixin.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;

import cn.future.common.action.BaseAction;
import cn.future.weixin.exception.WeixinButtonCreateException;
import cn.future.weixin.exception.WeixinButtonLengthException;
import cn.future.weixin.exception.WeixinTokenFetchException;
import cn.future.weixin.pojo.PWeixinButton;
import cn.future.weixin.pojo.PWeixinSubButton;
import cn.future.weixin.service.WeixinButtonService;

public class MenuAction extends BaseAction{

	private static final long serialVersionUID = -10809459175168142L;

	//set
	private WeixinButtonService weixinButtonService;
	private PWeixinButton button1;
	private PWeixinButton button2;
	private PWeixinButton button3;
	private ArrayList<PWeixinSubButton> subButton1=new ArrayList<PWeixinSubButton>();
	private ArrayList<PWeixinSubButton> subButton2=new ArrayList<PWeixinSubButton>();
	private ArrayList<PWeixinSubButton> subButton3=new ArrayList<PWeixinSubButton>();
	//get
	private String message;
	private String queryButton;
	/**
	 * 查询当前的按钮菜单
	 * @return
	 */
	public String query(){
		try {
			queryButton = weixinButtonService.findMenu();
		} catch (WeixinTokenFetchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	/**
	 * 创建微信菜单
	 * @return
	 */
	public String create(){
		try {
			response.setStatus(500);
			ArrayList<PWeixinButton> buttons = new ArrayList<PWeixinButton>();
			if(null!=button1 && null!=subButton1 && subButton1.size()>0){
				button1.setSub_button(subButton1);
				buttons.add(button1);
			}
			if(null!=button2 && null!=subButton2 && subButton2.size()>0){
				button2.setSub_button(subButton2);
				buttons.add(button2);
			}
			if(null!=button3 && null!=subButton3 && subButton3.size()>0){
				button3.setSub_button(subButton3);
				buttons.add(button3);
			}
			weixinButtonService.createMenu(buttons);
			response.setStatus(200);
		} catch (WeixinButtonLengthException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WeixinTokenFetchException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WeixinButtonCreateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}
	public void setWeixinButtonService(WeixinButtonService weixinButtonService) {
		this.weixinButtonService = weixinButtonService;
	}
	public void setButton1(PWeixinButton button1) {
		this.button1 = button1;
	}
	public void setButton2(PWeixinButton button2) {
		this.button2 = button2;
	}
	public void setButton3(PWeixinButton button3) {
		this.button3 = button3;
	}
	public void setSubButton1(ArrayList<PWeixinSubButton> subButton1) {
		this.subButton1 = subButton1;
	}
	public void setSubButton2(ArrayList<PWeixinSubButton> subButton2) {
		this.subButton2 = subButton2;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMessage() {
		return message;
	}
	public String getQueryButton() {
		return queryButton;
	}
	
}
