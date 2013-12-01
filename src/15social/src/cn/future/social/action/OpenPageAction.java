package cn.future.social.action;

import cn.future.common.action.BaseAction;

public class OpenPageAction extends BaseAction{

	private static final long serialVersionUID = -1279999482471933531L;
	//get
	private String jspPage;
	public String qqSignCallBack(){
		jspPage = "index.jsp";
		return SUCCESSJSP;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getJspPage() {
		return jspPage;
	}
	
}
