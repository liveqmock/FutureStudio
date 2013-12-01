package cn.future.common.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public abstract class Dao {
	private SessionFactory sessionFactory;
	protected Session session;
	
	private Session getSession(){
		Session sessions = sessionFactory.getCurrentSession();
		if(sessions==null){
			sessions=sessionFactory.openSession();
		}
		this.session = sessions;
		return sessions;
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
		this.getSession();
	}
}
