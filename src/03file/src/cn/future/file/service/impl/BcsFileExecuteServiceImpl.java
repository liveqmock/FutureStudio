package cn.future.file.service.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.slf4j.*;

import com.baidu.inf.iis.bcs.BaiduBCS;
import com.baidu.inf.iis.bcs.auth.BCSCredentials;
import com.baidu.inf.iis.bcs.model.DownloadObject;
import com.baidu.inf.iis.bcs.model.Empty;
import com.baidu.inf.iis.bcs.model.ObjectMetadata;
import com.baidu.inf.iis.bcs.request.GetObjectRequest;
import com.baidu.inf.iis.bcs.request.PutObjectRequest;
import com.baidu.inf.iis.bcs.response.BaiduBCSResponse;

import cn.future.file.pojo.PFile;
import cn.future.file.service.FileExecuteService;
import cn.future.common.service.impl.ConfigHelperImpl;
import cn.future.util.TmpFilePathUtil;

public class BcsFileExecuteServiceImpl implements FileExecuteService {
	
	private Logger logger = LoggerFactory.getLogger(BcsFileExecuteServiceImpl.class);
	public String host;//=ConfigHelper.bscHost;
	public String bucket;//=ConfigHelper.bucket;
	public String accessKey;//=ConfigHelper.ak;
	public String secretKey;//=ConfigHelper.sk;
	
	private BCSCredentials credentials;
	private BaiduBCS baiduBCS;
	
	public BcsFileExecuteServiceImpl(){
		//System.out.println(bucket+accessKey+secretKey);
		this.host=ConfigHelperImpl.getProperty("bcsHost", "bcs.duapp.com");
		this.bucket=ConfigHelperImpl.getProperty("bucket", "futurestudio");
		this.accessKey=ConfigHelperImpl.getProperty("ak", "paQrtN6iLLAxgClNTnXusta8");
		this.secretKey=ConfigHelperImpl.getProperty("sk", "toh0C8Sm0ZIMn4UUFhWwyNtGgdWNlyXU");
		
		credentials = new BCSCredentials(accessKey, secretKey);
		baiduBCS = new BaiduBCS(credentials, host);
		baiduBCS.setDefaultEncoding("UTF-8"); // Default UTF-8
	}
	@Override
	public void uploadFile(File file, PFile dFile) throws Exception {
		String object="/"+dFile.getId()+"."+dFile.getType();
		PutObjectRequest request=new PutObjectRequest(bucket,object,file);
		
		ObjectMetadata metadata=new ObjectMetadata();
		request.setMetadata(metadata);
		BaiduBCSResponse<ObjectMetadata> response=baiduBCS.putObject(request);
		ObjectMetadata objectMetadata=response.getResult();
		logger.debug(objectMetadata.getContentType());
	}
	@Override
	public void uploadFile(InputStream is, PFile dFile) throws IOException {
		String object="/"+dFile.getId()+"."+dFile.getType();
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentType("text/html");
		
		String tmp=TmpFilePathUtil.findTmpFs();
		File file=new File(tmp+dFile.getId()+"."+dFile.getType());
		try{
			OutputStream os = new FileOutputStream(file);
			int bytesRead = 0;
			byte[] buffer = new byte[1024];
			while ((bytesRead = is.read(buffer, 0, 1024)) != -1) {
			   os.write(buffer, 0, bytesRead);
			}
			os.close();
			is.close();
		} catch (Exception e) {
		  	e.printStackTrace();
		  	logger.error("focus error:"+e.getMessage());	
		}
		  
		objectMetadata.setContentLength(is.available());
		PutObjectRequest request = new PutObjectRequest(bucket, object, is, objectMetadata);
		//ObjectMetadata result = 
		baiduBCS.putObject(request).getResult();		
	}

	@Override
	public void deleteFile(PFile dFile) throws Exception {
		String object="/"+dFile.getId()+"."+dFile.getType();
		Empty result = baiduBCS.deleteObject(bucket, object).getResult();
		logger.debug(result.toString());
	}

	@Override
	public InputStream downloadFile(PFile dFile) throws Exception {
		String object="/"+dFile.getId()+"."+dFile.getType();
		//File file=new File(dFile.getId()+"."+dFile.getType());
		//baiduBCS.getObject(new GetObjectRequest(bucket, object), file);
		BaiduBCSResponse<DownloadObject> bd = baiduBCS.getObject(new GetObjectRequest(bucket, object));
		return bd.getResult().getContent();
	}
	@Override
	public InputStream downloadFile(String fileId, String path)
			throws Exception {
		String object="/"+fileId;
		//File file=new File(dFile.getId()+"."+dFile.getType());
		//baiduBCS.getObject(new GetObjectRequest(bucket, object), file);
		BaiduBCSResponse<DownloadObject> bd = baiduBCS.getObject(new GetObjectRequest(path, object));
		return bd.getResult().getContent();
	}


}
