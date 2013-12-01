package cn.future.file.action;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.file.pojo.FileDirectory;
import cn.future.file.service.FileDirectoryService;

public class FileDirectoryAction extends BaseAction{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3821363317431181528L;
	private FileDirectoryService fileDirectoryService;
	private String id;
	private String data1;
	private String data2;
	//set get
	private FileDirectory directory;
	/**
	 * 调整优先级，data1为下移目录，data2为上移目录
	 * @return
	 * @throws NotFindException 
	 */
	public String moveDirectory() throws NotFindException{
		fileDirectoryService.updateFileDirectoryPriority(data1,data2);
		return SUCCESS;
	}
	/**
	 * 通过Id查询文件目录
	 * @return
	 * @throws NotFindException 
	 */
	public String findDirectory() throws NotFindException{
		directory=fileDirectoryService.findDirectory(id);
		return SUCCESS;
	}

	public FileDirectory getDirectory() {
		return directory;
	}

	public void setFileDirectoryService(FileDirectoryService fileDirectoryService) {
		this.fileDirectoryService = fileDirectoryService;
	}

	public void setId(String id) {
		this.id = id;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	
}
