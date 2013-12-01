package cn.future.notification.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.notification.dto.DSysNotification;
import cn.future.notification.pojo.PSysNotification;
import cn.future.notification.service.NotificationQueryService;

public class NotificationQueryServiceImpl implements NotificationQueryService {

	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}
	public int findNotification(List<PSysNotification> list,String userId,String searchText,Date startDate,int page,int pageSize,Integer statusCode){
		HashMap<String,Object> params = new HashMap<String,Object>();
		String hql="from cn.future.notification.pojo.PSysNotification as a where 1=1";
		if(statusCode!=null){
			hql=hql+" and a.statusCode=:statusCode";
			params.put("statusCode", statusCode);
		}
		if(userId!=null){
			hql=hql+" and a.accountId=:userId";
			params.put("userId", userId);
		}
		if(searchText!=null){
			hql=hql+" and (a.title like :search or a.content like :search)";
			searchText="%"+searchText+"%";
			params.put("search", searchText);
		}
		if(startDate!=null){
			hql=hql+" and a.submitDate>:startDate";
			params.put("startDate", startDate);
		}
		Integer p1=null;
		Integer p2=null;
		if(page!=0 && pageSize!=0){
			p1=new Integer(page);
			p2=new Integer(pageSize);
		}
		List<PSysNotification> qlist=baseDao.findAll(PSysNotification.class, hql, p1, p2, params);
		list.addAll(qlist);
		hql = "select count(*) "+hql;
		int count = baseDao.findCount(hql, params);
		return count;
	}
	@Override
	public int findHistoryNotification(List<PSysNotification> list, String userId,String searchText,Date startDate,int page,int pageSize) {
		return this.findNotification(list, userId, searchText, startDate, page, pageSize, new Integer(0));
	}
	@Override
	public int findUnreadNotification(List<PSysNotification> list, String userId,String searchText,Date startDate,int page,int pageSize) {
		return this.findNotification(list, userId, searchText, startDate, page, pageSize, new Integer(1));
	}
	@Override
	public int findAllNotification(List<PSysNotification> list, String userId,String searchText,Date startDate,int page,int pageSize){
		return this.findNotification(list, userId, searchText, startDate, page, pageSize, null);
	}
	@Override
	public List<DSysNotification> transfer(List<PSysNotification> list) {
		List<DSysNotification> listp = new ArrayList<DSysNotification>();
		if(list!=null){
			for(PSysNotification d: list){
				listp.add(new DSysNotification(d));
			}
		}
		return listp;
	}

	@Override
	public DSysNotification transfer(PSysNotification domain) {
		return new DSysNotification(domain);
	}

}
