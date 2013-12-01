package cn.future.workflow.pojo;

import java.util.HashMap;
/**
 * 构造工作流实例最少需要的参数
 * @author Tony
 *
 */
public class PWorkFlowParams {
	private HashMap<String,Object> params = new HashMap<String,Object>();
	/**
	 * 必须提供的构造参数
	 * @param requestId
	 * @param instanceId 格式：key.businessId
	 * @param title
	 * @param forms (formPath formId)
	 */
	public PWorkFlowParams(String requestId,String instanceId,String title,String formPath,String formId){
		this.params.put("requestId", requestId);
		this.params.put("instanceId", instanceId);
		this.params.put("title", title);
		if(formPath!=null && formId!=null){
			this.params.put("forms",formPath+":"+formId);
		}
		
		
		//this.addForm(formPath,formId);
	}
	public String getRequestId(){
		return (String)this.params.get("requestId");
	}
	public void addForm(String formPath,String formId){
		if(formPath!=null && formId!=null){
			String forms = (String)this.params.get("forms");
			if(forms!=null && forms.length()>0){
				forms+=","+formPath+":"+formId;
			}else{
				forms+=formPath+":"+formId;
			}		
		}
	}
	public HashMap<String,Object> getParams(){
		return this.params;
	}
	public void put(String s, Object o){
		this.params.put(s, o);
	}
	public Object get(String key){
		return this.params.get(key);
	}
}
