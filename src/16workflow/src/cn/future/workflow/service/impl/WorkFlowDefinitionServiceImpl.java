package cn.future.workflow.service.impl;

import org.jbpm.api.RepositoryService;

import cn.future.common.service.impl.ConfigHelperImpl;
import cn.future.workflow.service.WorkFlowDefinitionService;

public class WorkFlowDefinitionServiceImpl implements WorkFlowDefinitionService {
	private RepositoryService repositoryService;
	private String path;  //resource : cn/future/business/mingwang/ **filename
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public WorkFlowDefinitionServiceImpl(){
		this.path=ConfigHelperImpl.getProperty("jpdlPath", "");
	}
	@Override
	public void addWorkFlowDefinition(String fileName) {
		repositoryService.createDeployment().addResourceFromClasspath(path+fileName).deploy();		
	}


	@Override
	public void deleteWorkFlowDefinition(String keyName) {

	}




}
