package cn.future.workflow.service;

public abstract interface WorkFlowDefinitionService {
	/**
	 * 部署一个流程定义，这里只需要文件名即可，路径由spring注入
	 * @param fileName
	 */
	public void addWorkFlowDefinition(String fileName);
	/**
	 * 删除一个流程定义，但是需要的是key的名称（这个服务暂时不暴露给外部)
	 * @param keyName
	 */
	public void deleteWorkFlowDefinition(String keyName);
}
