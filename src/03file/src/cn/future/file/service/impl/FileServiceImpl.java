package cn.future.file.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.PermissionDeclineException;
import cn.future.file.pojo.PFile;
import cn.future.file.service.FileExecuteService;
import cn.future.file.service.FileService;
import cn.future.util.StringUtil;

public class FileServiceImpl implements FileService {
	private BaseDao baseDao;
	private FileExecuteService fileExecuteService;

	@Override
	public void addDFile(PFile file, File is)
			throws Exception {
		file.setId(StringUtil.getUuid());
		fileExecuteService.uploadFile(is, file);
		baseDao.save(file);
	}
	@Override
	public void addDFile(PFile file,InputStream is) throws IOException{
		file.setId(StringUtil.getUuid());
		fileExecuteService.uploadFile(is, file);
		baseDao.save(file);
	}
	@Override
	public PFile findDFile(String id) throws NotFindException {
		return baseDao.findById(PFile.class, id);
	}

	@Override
	public InputStream downloadFile(PFile dfile) throws Exception {
		return fileExecuteService.downloadFile(dfile);
	}

	@Override
	public void deleteFile(String userid, String fileid)
			throws Exception {
		PFile file = baseDao.findById(PFile.class, fileid);
		if(file==null){
			NotFindException e = new NotFindException(fileid+"文件，未找到");
			throw e;
		}
		if(!file.getAccountid().equals(userid)){
			PermissionDeclineException e = new PermissionDeclineException("非文件所有者，不能删除文件");
			throw e;
		}		
		try {
			fileExecuteService.deleteFile(file);
			baseDao.delete(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw e;
		}
		
	}


	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setFileExecuteService(FileExecuteService fileExecuteService) {
		this.fileExecuteService = fileExecuteService;
	}
	@Override
	public int findFileByDirectoryIdUserId(int page, int pageSize,List<PFile> list, String directoryId, String userId, String query) {
		String hql="from cn.future.file.pojo.PFile as d where d.accountid=:accountid and d.directoryid=:directoryid";		
		HashMap<String,Object> params=new HashMap<String,Object>();
		params.put("accountid", userId);
		params.put("directoryid",directoryId);
		if(query!=null && !"".equals(query)){
			hql+=" and ( d.name like :query or d.comments like :query )";
			params.put("query", "%"+query+"%");
		}
		hql+=" order by d.addTime desc";
		List<PFile> files = baseDao.findAll(PFile.class, hql, page, pageSize, params);
		list.addAll(files);
		int k=baseDao.findCount("select count(*) "+hql, params);
		return k;
	}
	@Override
	public void updateFileDirectory(String fileId, String directoryId) throws NotFindException {
		PFile file=baseDao.findById(PFile.class, fileId);
		file.setDirectoryid(directoryId);
		baseDao.update(file);
	}
	@Override
	public InputStream downloadFile(String fileName, String path) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}



}
