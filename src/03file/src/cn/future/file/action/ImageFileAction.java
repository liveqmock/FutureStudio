package cn.future.file.action;

import java.io.InputStream;

import cn.future.common.action.BaseAction;
import cn.future.file.service.WorkFlowImageService;

public class ImageFileAction extends BaseAction{
	private static final long serialVersionUID = 2968765532197592828L;
	private static final String IMAGETYPE="imageType";
	private String id;
	private WorkFlowImageService workFlowImageService;
	//set get
	private InputStream inputStream;
	public String workFlowImage(){
		inputStream=workFlowImageService.findWorkFlowImageByKey(id);
		return IMAGETYPE;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setWorkFlowImageService(WorkFlowImageService workFlowImageService) {
		this.workFlowImageService = workFlowImageService;
	}
	
	
}
