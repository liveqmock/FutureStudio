package cn.future.notification.service;

import java.util.Date;
import java.util.List;

import cn.future.notification.dto.DSysNotification;
import cn.future.notification.pojo.PSysNotification;

public abstract interface NotificationQueryService {
	/**
	 * 查询可用通知信息
	 * @param userId  可选参数，如果没有id则查询所有的用户id
	 * @param title  可选参数，模糊搜索通知标题，
	 * @param content  可选参数，模糊搜索通知内容
	 * @param isAll  可选参数，是否显示已经已读和删除的信息，查看你是信息
	 * @param startDate  可选参数，开始时间，大于这个时间的数据
	 * @param page   可选，第几页，如果没有，就似乎获取全部
	 * @param pageSize   可选，每页数量，建议20，如果没有页数，那么数量不生效
	 * @return
	 */
	public abstract int findUnreadNotification(List<PSysNotification> list, String userId,String searchText,Date startDate,int page,int pageSize);	
	/**
	 * 查询历史通知信息
	 * @param userId  可选参数，如果没有id则查询所有的用户id
	 * @param title  可选参数，模糊搜索通知标题，
	 * @param content  可选参数，模糊搜索通知内容
	 * @param isAll  可选参数，是否显示已经已读和删除的信息，查看你是信息
	 * @param startDate  可选参数，开始时间，大于这个时间的数据
	 * @param page   可选，第几页，如果没有，就似乎获取全部
	 * @param pageSize   可选，每页数量，建议20，如果没有页数，那么数量不生效
	 * @return
	 */
	public abstract int findHistoryNotification(List<PSysNotification> list, String userId,String searchText,Date startDate,int page,int pageSize);
	/**
	 * 查询所有信息
	 * @param userId  可选参数，如果没有id则查询所有的用户id
	 * @param title  可选参数，模糊搜索通知标题，
	 * @param content  可选参数，模糊搜索通知内容
	 * @param isAll  可选参数，是否显示已经已读和删除的信息，查看你是信息
	 * @param startDate  可选参数，开始时间，大于这个时间的数据
	 * @param page   可选，第几页，如果没有，就似乎获取全部
	 * @param pageSize   可选，每页数量，建议20，如果没有页数，那么数量不生效
	 * @return
	 */
	public abstract int findAllNotification(List<PSysNotification> list, String userId,String searchText,Date startDate,int page,int pageSize);
	/**
	 * 将domain转成P对象
	 * @param list
	 * @return
	 */
	public List<DSysNotification> transfer(List<PSysNotification> list);
	/**
	 * 将domain转成P对象
	 * @param list
	 * @return
	 */
	public DSysNotification transfer(PSysNotification domain);
}
