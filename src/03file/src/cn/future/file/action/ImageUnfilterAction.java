package cn.future.file.action;

import java.io.InputStream;

import org.slf4j.*;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.file.pojo.PFile;
import cn.future.file.service.FileService;

public class ImageUnfilterAction extends BaseAction{
	private Logger logger = LoggerFactory.getLogger(ImageUnfilterAction.class);
	private static final long serialVersionUID = -788737667675246536L;
	private static final String IMAGETYPE="imageType";
	private String id;
	private String bucket="";
	private FileService fileService;
	private String from;
	//set get
	private InputStream imageStream;
	/**
	 * 指定buket，指定文件id的文件浏览
	 * @return
	 */
	public String bucketImage(){
		if("".equals(bucket)){
			bucket="futurestudio";
		}
		if(this.protectCheck() ){
			try{
				imageStream=fileService.downloadFile(id, bucket);
			}catch(Exception e){
				imageStream=null;
			}
		}else{
			//TODO
			//LogUtil.error("盗链："+from);
			imageStream=null;
		}
		return IMAGETYPE;
	}
	/**
	 * 检查请求源，进行请求保护
	 * @return
	 */
	private boolean protectCheck(){
		from = request.getHeader("Referer");
		if(from==null){
			from="duapp";
		}
		if(from.contains("localhost")||from.contains("duapp")||from.contains("mw")||from.contains("dgh")||from.contains("mingwang")){
			return true;
		}else{
			logger.info("focus image 盗链 : " + from);
			return false;
		}
	}
	/**
	 * 普通图片浏览
	 * @return
	 * @throws NotFindException 
	 */
	public String image() throws NotFindException{
		String fileId = id.replace(".jpg", "");
		logger.debug("focus image id : " + fileId);
		PFile dfile = fileService.findDFile(fileId);
		String type=dfile.getType().toLowerCase();

		if(this.protectCheck() && (type.contains("jpg")||type.contains("png")||type.contains("jpeg")||type.contains("gif"))){
			try{
				imageStream=fileService.downloadFile(dfile);
			}catch(Exception e){
				logger.debug("focus image 获取文件异常 :" + e.getMessage());
				imageStream=null;
			}
		}else{
			logger.debug("focus image : 检查失败 ，设置为 null");
			imageStream=null;
		}
		return IMAGETYPE;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public InputStream getImageStream() {
		if(imageStream == null){
			logger.debug("focus image : null");
		}else{
			logger.debug("focus image : available");
		}
		return imageStream;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setFileService(FileService fileService) {
		this.fileService = fileService;
	}
	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

}
