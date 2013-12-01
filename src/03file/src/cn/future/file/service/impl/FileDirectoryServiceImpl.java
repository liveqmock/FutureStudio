package cn.future.file.service.impl;

import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.file.exception.FileDirectoryNotEmptyException;
import cn.future.file.pojo.FileDirectory;
import cn.future.file.service.FileDirectoryService;

public class FileDirectoryServiceImpl implements FileDirectoryService{
	private BaseDao baseDao;
	@Override
	public int findFileDirectoryMaxPriority(String userId) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("userid",userId);
		String hql="from cn.future.file.pojo.FileDirectory as a where a.accountid=:userid order by a.priority desc";
		List<FileDirectory> list = baseDao.findAll(FileDirectory.class, hql, 1, 1, params);
		if(list!=null && list.size()>0){
			FileDirectory directory=list.get(0);
			return directory.getPriority();
		}
		return 0;
	}

	@Override
	public void addFileDirectory(FileDirectory directory) {
		baseDao.save(directory);
	}

	@Override
	public List<FileDirectory> findFileDirectoryByUserId(String userid) {
		HashMap<String,Object> params = new HashMap<String,Object>();
		params.put("userid",userid);
		List<FileDirectory> list = baseDao.findAll(FileDirectory.class, "from cn.future.file.pojo.FileDirectory as a where a.accountid=:userid", 1, 9999, params);
		return list;
	}

	@Override
	public void updateFileDirectoryPriority(String idA, String idB) throws NotFindException {
		FileDirectory dA=baseDao.findById(FileDirectory.class, idA);
		FileDirectory dB=baseDao.findById(FileDirectory.class, idB);
		int pa=dA.getPriority();
		int pb=dB.getPriority();
		if(pa==pb){
			//优先级相同的情况下，大的+5，小的-5
			dA.setPriority(pa+5);
			dB.setPriority(pb-5);
		}else{
			//优先级不同的情况下，直接交换优先级
			dA.setPriority(pb);
			dB.setPriority(pa);
			baseDao.update(dB);
			baseDao.update(dA);
		}
	}

	@Override
	public void deleteFileDirectory(String directoryId)
			throws FileDirectoryNotEmptyException, NotFindException {
		// TODO Auto-generated method stub
		HashMap<String,Object> params = new HashMap<String,Object>();
		int k=baseDao.findCount("select count(*) from cn.future.file.pojo.PFile as a where a.directoryid='"+directoryId+"'",params);
		if(k>0){
			FileDirectoryNotEmptyException e = new FileDirectoryNotEmptyException("无法删除，目录不为空");
			throw e;
		}else{
			FileDirectory o = baseDao.findById(FileDirectory.class, directoryId);
			baseDao.delete(o);
		}
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public FileDirectory findDirectory(String id) throws NotFindException {
		return baseDao.findById(FileDirectory.class, id);
	}

	@Override
	public void updateDirectory(FileDirectory directory) {
		baseDao.update(directory);
	}
	
	
}
