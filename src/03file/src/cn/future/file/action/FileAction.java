package cn.future.file.action;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.PermissionDeclineException;
import cn.future.file.exception.FileDirectoryNotEmptyException;
import cn.future.file.pojo.PFile;
import cn.future.file.pojo.FileDirectory;
import cn.future.file.service.FileDirectoryService;
import cn.future.file.service.FileService;
import cn.future.util.StringUtil;
import cn.future.util.TimeUtil;

/**
 * 文件操作Action
 * @author Tony soft-qiyao@foxmail.com
 *
 */
public class FileAction extends BaseAction{
	private static final long serialVersionUID = 3411999586202177763L;
	private static final String FILETYPE="fileType";
	private static final String IMAGETYPE="imageType";
	
	private FileService fileService;
	private FileDirectoryService fileDirectoryService;
	private String id;
	private String data1;
	private String data2;
	private String data3;
	private String data4;
	private File file;
	private String fileFileName;  //get set
	private String fileContentType;
	private String query;
	private int page;
	private int pageSize;
	//set  -- get
	private String url;
	private String title;
	private String state;
	private String original;
	//以上是Ueditor需要的返回参数
	
	private String message;
	private InputStream inputStream;
	private InputStream imageStream;
	private int count;
	private int countPage;
	private List<FileDirectory> fileDirectories;
	private List<PFile> files;
	public String moveFile() throws NotFindException{
		//data3 是file id
		//data4 是directoryId
		PFile file=fileService.findDFile(data3);
		FileDirectory directory=fileDirectoryService.findDirectory(data4);
		String userId=this.findCookieId();
		
		if(!userId.equals(file.getAccountid()) || !userId.equals(directory.getAccountid())){
			response.setStatus(400);
			message="无操作权限";
		}else{
			fileService.updateFileDirectory(data3,data4);
			message="移动成功";
		}
		return SUCCESS;
	}
	/**
	 * 删除指定ID的图片
	 * @return
	 */
	public String deleteFile(){
		
		try {
			response.setStatus(400);
			fileService.deleteFile(this.findCookieId(), id);
			response.setStatus(200);
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			message="请求删除的文件不存在";
			e.printStackTrace();
		} catch (PermissionDeclineException e) {
			message=e.getMessage();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="删除文件时出错";
		}
		return SUCCESS;
	}
	/**
	 * 获取图片
	 * @return
	 */
	public String imageFile(){
		try {
			PFile df = fileService.findDFile(id);
			imageStream=fileService.downloadFile(df);
			fileFileName=df.getPinyin()+"."+df.getType();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="下载出错";
			response.setStatus(400);
			e.printStackTrace();
		}
		return FileAction.IMAGETYPE;
	}
	/**
	 * 下载文件
	 * @return
	 */
	public String downloadFile(){
		try {
			PFile df = fileService.findDFile(id);
			inputStream=fileService.downloadFile(df);
			fileFileName=df.getPinyin()+"."+df.getType();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			message="下载出错";
			response.setStatus(400);
			e.printStackTrace();
		}
		return FileAction.FILETYPE;
	}
	/**
	 * 上传文件， id是文件目录id，data1是文件名，data2是备注
	 * @return
	 * @throws Exception
	 */
	public String uploadFile() throws Exception{
		if(file!=null){
			String type=fileFileName.substring(fileFileName.lastIndexOf(".")+1,fileFileName.length());
			String time=TimeUtil.dateToYMDHMSString(new Date());
			PFile dFile = new PFile();
			dFile.setAccountid(this.findCookieId());
			dFile.setAddTime(time);
			data1=data1==null?"空":data1;
			data2=data2==null?"空":data2;
			dFile.setComments(data2);
			if(id==null){
				id="0";
			}
			dFile.setDirectoryid(id);
			dFile.setLastRead(time);
			dFile.setName(data1);
			dFile.setPinyin(StringUtil.getPinyinString(data1));
			int size=(int)file.length()/1024;
			dFile.setSize(size);
			dFile.setType(type);
			
			fileService.addDFile(dFile, file);
			message=dFile.getId();
			this.url="imageUnfilter_image.action?id="+dFile.getId()+".jpg";
			this.title=dFile.getId();
			this.state="SUCCESS";
			this.original=this.fileFileName;
		}
		
		return SUCCESS;
	}
	/**
	 * 查找用户的文件，参数是文件夹id，不过会一并校验用户的id。这个地方不做权限管控
	 * @return
	 */
	public String findFileByDirectory(){
		files=new ArrayList<PFile>();
		count=fileService.findFileByDirectoryIdUserId(page,pageSize,files,id, this.findCookieId(),query);
		countPage=(int)count/pageSize+1;
		return SUCCESS;
	}
	/**
	 * 查找用户目录
	 * @return
	 */
	public String findDirectory(){
		fileDirectories=fileDirectoryService.findFileDirectoryByUserId(this.findCookieId());
		return SUCCESS;
	}
	/**
	 * 删除目录
	 * @return
	 * @throws NotFindException 
	 */
	public String deleteDirectory() throws NotFindException{
			try {
				fileDirectoryService.deleteFileDirectory(id);
				message="删除成功";
			} catch (FileDirectoryNotEmptyException e) {
				// TODO Auto-generated catch block
				this.response.setStatus(400);
				message=e.getMessage();
				e.printStackTrace();
			}

		return SUCCESS;
	}
	/**
	 * 更新目录信息
	 * @return
	 */
	public String updateDirectory(){
		try{
		FileDirectory d = fileDirectoryService.findDirectory(id);
		d.setName(data1);
		d.setComments(data2);
		fileDirectoryService.updateDirectory(d);
		message="修改成功";
		}catch(Exception e){
			this.response.setStatus(400);
			message="修改失败,"+e.getMessage();
		}
		return SUCCESS;
	}
	/**
	 * 新增文件目录
	 * data1 是名字，data2是备注
	 * @return
	 */
	public String addDirectory(){
		int priority=fileDirectoryService.findFileDirectoryMaxPriority(this.findCookieId());
		
		FileDirectory directory = new FileDirectory();
		directory.setId(StringUtil.getUuid());
		directory.setAccountid(this.findCookieId());
		directory.setAddTime(new Date());
		directory.setComments(data2);
		directory.setName(data1);
		directory.setPriority(priority+1000);
		fileDirectoryService.addFileDirectory(directory);
		
		return SUCCESS;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<FileDirectory> getFileDirectories() {
		return fileDirectories;
	}
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
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
	public void setData3(String data3) {
		this.data3 = data3;
	}
	public void setData4(String data4) {
		this.data4 = data4;
	}
	public void setFile(File file) {
		this.file = file;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}
	public List<PFile> getFiles() {
		return files;
	}
	public String getMessage() {
		return message;
	}
	public InputStream getInputStream() {
		return inputStream;
	}
	public InputStream getImageStream() {
		return imageStream;
	}
	public String getFileFileName() {
		return fileFileName;
	}
	public void setFileDirectoryService(FileDirectoryService fileDirectoryService) {
		this.fileDirectoryService = fileDirectoryService;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	public int getPage() {
		return page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getCount() {
		return count;
	}
	public int getCountPage() {
		return countPage;
	}
	public String getUrl() {
		return url;
	}
	public String getTitle() {
		return title;
	}
	public String getState() {
		return state;
	}
	public String getOriginal() {
		return original;
	}	
	
}
