package cn.future.file.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import cn.future.file.pojo.PFile;

public abstract interface FileExecuteService {
	/**
	 * 保存上传的文件
	 * @param is
	 * @param dFile
	 * @throws IOException 
	 */
	public void uploadFile(InputStream is,PFile dFile) throws IOException;
	/**
	 * 保存上传的文件
	 * @param file
	 * @param dFile
	 * @throws Exception
	 */
	public void uploadFile(File file, PFile dFile) throws Exception;
	/**
	 * 删除已经存在的文件
	 * @param dFile
	 * @throws Exception
	 */
	public void deleteFile(PFile dFile)throws Exception;
	/**
	 * 下载已经存在的文件
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public InputStream downloadFile(PFile dFile)throws Exception;
	
	/**
	 * 下载已经存在的文件
	 * @param fileId
	 * @param path
	 * @return
	 * @throws Exception
	 */
	public InputStream downloadFile(String fileId,String path)throws Exception;
}
