package cn.future.file.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.file.exception.FileDirectoryNotEmptyException;
import cn.future.file.pojo.FileDirectory;

public abstract interface FileDirectoryService {
	/**
	 * 通过用户id查找最大优先级的目录
	 * @param userId
	 * @return
	 */
	public int findFileDirectoryMaxPriority(String userId);
	
	
	/**
	 * 新增文件目录，注意id和accountid的使用
	 * @param directory
	 */
	public void addFileDirectory(FileDirectory directory);
	/**
	 * 通过用户的id，查找文件目录
	 * @param userid
	 * @return
	 */
	public List<FileDirectory> findFileDirectoryByUserId(String userid);
	/**
	 * 对文件目录的位置进行调整
	 * idA为下移，大优先级
	 * idB为上移，目录
	 * @throws NotFindException 
	 */
	public void updateFileDirectoryPriority(String idA, String idB) throws NotFindException;
	/**
	 * 删除文件目录，首先查看文件目录下是否还有文件，如果还有文件则不能执行删除操作，如果没有文件了，则执行删除操作
	 * @throws NotFindException 
	 */
	public void deleteFileDirectory(String directoryId) throws FileDirectoryNotEmptyException, NotFindException;

	/**
	 * 查找指定ID的目录
	 * @param id
	 * @return
	 * @throws NotFindException 
	 */
	public FileDirectory findDirectory(String id) throws NotFindException;
	/**
	 * 更新目录信息
	 * @param directory
	 */
	public void updateDirectory(FileDirectory directory);
}
