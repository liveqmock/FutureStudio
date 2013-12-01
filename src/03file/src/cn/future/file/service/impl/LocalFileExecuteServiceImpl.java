package cn.future.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import cn.future.file.pojo.PFile;
import cn.future.file.service.FileExecuteService;

public class LocalFileExecuteServiceImpl implements FileExecuteService {

	@Override
	public void uploadFile(File file, PFile dFile) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteFile(PFile dFile) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public InputStream downloadFile(PFile dFile) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void uploadFile(InputStream is, PFile dFile) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public InputStream downloadFile(String fileId, String path)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
