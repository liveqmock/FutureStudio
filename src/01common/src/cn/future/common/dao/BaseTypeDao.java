package cn.future.common.dao;

import java.util.HashMap;
import java.util.List;

import org.hibernate.Query;

public class BaseTypeDao extends Dao {
	/**
	 * 求和
	 * @param hql
	 * @param params
	 * @return
	 */
	public Double sumOne(String hql, HashMap<String, Object> params) {
		Query query = session.createQuery(hql);
		if (params != null) {
			for (String key : params.keySet()) {
				query.setParameter(key, params.get(key));
			}
		}
		@SuppressWarnings("rawtypes")
		List p = query.list();
		Object o = p.get(0);
		if(null==o){
			return new Double(0);
		}else{
			try{
				Long l = (Long)o;
				return new Double(l.doubleValue());
			}catch(Exception e){
				return (Double)o;
			}
		}
	}
}
