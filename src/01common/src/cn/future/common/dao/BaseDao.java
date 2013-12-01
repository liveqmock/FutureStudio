/**
 * Tony FutureStudio soft-qiyao@foxmail.com Dec 13, 2012 11:33:06 PM
 */

package cn.future.common.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import org.hibernate.ObjectNotFoundException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;

import cn.future.common.exception.NotFindException;
import cn.future.common.exception.NotUniqueException;

/**
 * BaseDao 只能用以下开头：find update save delete
 * 
 * @author Tony
 */
public class BaseDao {
	private SessionFactory sessionFactory;

	/**
	 * 查找table中的最大的priority
	 * 
	 * @param table
	 * @return
	 */
	public int findMaxPriority(String table) {
		String sql = ("select max(priority) from " + table);
		int t = 0;
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		@SuppressWarnings("rawtypes")
		List list = sqlQuery.list();
		if (list != null) {
			Integer k = (Integer) list.get(0);
			t = k.intValue();
		}
		return t;
	}

	/**
	 * 查询单个对象
	 * 
	 * @param c
	 *            对象类型
	 * @param id
	 *            对象主键id
	 * @return
	 * @throws NotFindException
	 */
	@SuppressWarnings("unchecked")
	public <T> T findById(Class<T> type, String id) throws NotFindException {
		try {
			T t = (T) getSession().get(type, id);// (T)getSession().load(type,
													// id);
			if (t == null) {
				NotFindException e = new NotFindException("类型："
						+ type.getName() + ",id:" + id + "未找到");
				throw e;
			}
			return t;
		} catch (ObjectNotFoundException e1) {
			NotFindException e = new NotFindException("ID:" + id + "未找到");
			throw e;
		}
	}

	/**
	 * 查找唯一对象
	 * 
	 * @param type
	 * @param hql
	 * @param page
	 * @param size
	 * @param params
	 * @return
	 * @throws NotFindException
	 * @throws NotUniqueException
	 */
	public <T> T findUnique(Class<T> type, String hql,
			HashMap<String, Object> params) throws NotFindException,
			NotUniqueException {
		List<T> list = findAll(type, hql, null, null, params);
		if (list == null || list.size() == 0) {
			NotFindException e = new NotFindException("查询记录为空");
			throw e;
		} else if (list.size() > 1) {
			NotUniqueException e = new NotUniqueException("查询记录不唯一");
			throw e;
		} else {
			return list.get(0);
		}
	}

	/**
	 * 执行更新操作的sql
	 * 
	 * @param sql
	 */
	public void updateSql(String sql) {
		getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 执行删除操作的sql
	 * 
	 * @param sql
	 */
	public void deleteSql(String sql) {
		getSession().createSQLQuery(sql).executeUpdate();
	}

	/**
	 * 执行统计操作的sql
	 * 
	 * @param sql
	 * @return
	 */
	public int findCount(String sql) {
		int t = 0;
		SQLQuery sqlQuery = getSession().createSQLQuery(sql);
		Object b = sqlQuery.uniqueResult();
		try {
			Long l = (Long) b;
			t = l.intValue();
			System.out.println("count long ");
		} catch (Exception e) {
			t = ((BigInteger) b).intValue();
			System.out.println("count big integer");

		}
		return t;
	}

	/**
	 * 统计数量
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public int findCount(String hql, HashMap<String, Object> params) {
		int count = 0;
		Query query = getSession().createQuery(hql);
		if (params != null) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		Object b = query.uniqueResult();
		try {
			Long l = (Long) b;
			count = l.intValue();
			System.out.println("count long ");
		} catch (Exception e) {
			count = ((BigInteger) b).intValue();
			System.out.println("count big integer");
		}
		return count;
	}
	/**
	 * 统计数量
	 * 
	 * @param hql
	 * @param params
	 * @return
	 */
	public int findCount(String hql, HashMap<String, Object> params, @SuppressWarnings("rawtypes") HashMap<String,Collection> paramsList) {
		int count = 0;
		Query query = getSession().createQuery(hql);
		if (null != params) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		if(null!=paramsList){
			for (String key : paramsList.keySet()) {
				query.setParameterList(key, paramsList.get(key));
			}
		}
		Object b = query.uniqueResult();
		try {
			Long l = (Long) b;
			count = l.intValue();
			System.out.println("count long ");
		} catch (Exception e) {
			count = ((BigInteger) b).intValue();
			System.out.println("count big integer");
		}
		return count;
	}

	/**
	 * 分页查询
	 * @param hql  hql查询语句
	 * @param page  分页页码，从1到N
	 * @param size 每页查询的数量，一般是20个。
	 * @param os 可以设置的参数，object数组
	 * @return
	 */
	public <T> List<T> findAll(Class<T> type, String hql, Integer page,
			Integer pageSize, HashMap<String, Object> params) {
		return this.findAll(type, hql, page, pageSize, params, null);
	}
	
	/**
	 * 分页查询
	 * @param hql  hql查询语句
	 * @param page  分页页码，从1到N
	 * @param size 每页查询的数量，一般是20个。
	 * @param os 可以设置的参数，object数组
	 * @return
	 */
	public <T> List<T> findAll(Class<T> type, String hql, Integer page,
			Integer pageSize, HashMap<String, Object> params, @SuppressWarnings("rawtypes") HashMap<String, Collection> paramsList) {
		Session session = getSession();
		if (page == null) {
			page = new Integer(1);
		}
		if (pageSize == null) {
			pageSize = new Integer(20);
		}
		Query query = session.createQuery(hql);
		if (null != params) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		if(null!=paramsList){
			for(String key : paramsList.keySet()){
				query.setParameterList(key, paramsList.get(key));
			}
		}
		if (page != null && pageSize != null) {
			query.setFirstResult((page - 1) * pageSize).setMaxResults(pageSize);
		}
		@SuppressWarnings("unchecked")
		List<T> list = query.list();
		return list;
	}
	
	/**
	 * 使用SQL进行查询
	 * @param type
	 * @param sql
	 * @return
	 */
	public <T> List<T> findAll(Class<T> type, String sql, Integer page, Integer pageSize){
		Session session = getSession();
		SQLQuery query = session.createSQLQuery(sql);
		if(null!=page && null!=pageSize){
			query.setFirstResult(page);
			query.setMaxResults(pageSize);
		}
		@SuppressWarnings("unchecked")
		List<T> list = (List<T>)query.list();
		return list;
	}
	public <T> List<T> findAll(Class<T> type, List<String> ids) {
		Session session = getSession();
		List<T> list = new ArrayList<T>();
		if (ids != null) {
			for (String id : ids) {
				try {
					@SuppressWarnings("unchecked")
					T t = (T) session.get(type, id);
					if (t == null) {
						NotFindException e = new NotFindException("ID:" + id
								+ "未找到");
						e.printStackTrace();
					}
					list.add(t);
				} catch (ObjectNotFoundException e1) {
					NotFindException e = new NotFindException("ID:" + id
							+ "未找到");
					e1.printStackTrace();
					e.printStackTrace();
				}
			}
		}
		return list;
	}

	/**
	 * 删除对象,这个对象最少要有id这个主键
	 * 
	 * @param o
	 */
	public void delete(Object o) {
		getSession().delete(o);
	}

	/**
	 * 删除一组id的对象
	 * 
	 * @param c
	 * @param ids
	 */
	public <T> void deleteAll(Class<T> c, String[] ids) {
		Session session = getSession();
		for (String id : ids) {
			session.delete(session.load(c, id));
		}
	}

	/**
	 * 删除对象结合，结合的对象最少要有ID主键字段
	 * 
	 * @param c
	 */
	public <T> void deleteAll(Collection<T> c) {
		for (T t : c) {
			delete(t);
		}
	}

	/**
	 * 更新对象数据
	 * 
	 * @param o
	 *            对象数据全部
	 */
	public void update(Object o) {
		getSession().update(o);
	}

	/**
	 * 插入数据，
	 * @param o 数据对象，必须是全字段都要有。
	 * @throws ConstraintViolationException Key 或者 唯一字段重复
	 */
	public void save(Object o) throws ConstraintViolationException{
		getSession().save(o);
	}

	/**
	 * 保存或者更新实体
	 * 
	 * @param o
	 */
	public void saveOrUpdate(Object o) {
		getSession().saveOrUpdate(o);
	}

	private Session getSession() {
		Session session = sessionFactory.getCurrentSession();
		if (session == null) {
			session = sessionFactory.openSession();
		}
		return session;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
