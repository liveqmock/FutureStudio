package cn.future.file.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.file.pojo.PFile;

public abstract interface FileService {
	
	/**
	 * 添加一个文件到云上，先保存is，如果is保存成功的基础上，才保存file信息。
	 * @param file
	 * @param is
	 * @throws Exception
	 */
	public void addDFile(PFile file,File is) throws Exception;
	/**
	 * 添加文件到云上
	 * @param file
	 * @param is
	 * @throws IOException 
	 */
	public void addDFile(PFile file,InputStream is) throws IOException;
	/**
	 * 查找数据库中的数据信息
	 * @param id
	 * @return
	 * @throws NotFindException 
	 */
	public PFile findDFile(String id) throws NotFindException;
	/**
	 * 指定文件id，指定文件路径的文件下载。 BCS path就是buket，普通就是文件相对路径，是 配置的文件根目录/ 下的路径
	 * @param fileName
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public InputStream downloadFile(String fileName,String path)throws Exception;
	/**
	 * 下载文件
	 * @param fileid
	 * @return
	 */
	public InputStream downloadFile(PFile dfile) throws Exception ;
	/**
	 * 删除对应的文件，只有文件属主才有这个删除的权利
	 * @param userid
	 * @param fileid
	 * @throws NotFindException
	 */
	public void deleteFile(String userid,String fileid) throws Exception;
	/**
	 * 通过用户的id和对应目录查找文件，这样设计的服务无需权限认证。 
	 * @param page
	 * @param pageSize
	 * @param list
	 * @param directoryId
	 * @param userId
	 * @param query
	 * @return
	 */
	public int findFileByDirectoryIdUserId(int page,int pageSize, List<PFile> list,String directoryId,String userId,String query);
	/**
	 * 移动文件到指定文件夹
	 * @param data3
	 * @param data4
	 * @throws NotFindException 
	 */
	public void updateFileDirectory(String fileId, String directoryId) throws NotFindException;
}
