package cn.future.common.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public class BeanFactoryHelper implements BeanFactoryAware {
	private static BeanFactory factory;
	@Override
	public void setBeanFactory(BeanFactory arg0) throws BeansException {
		factory=arg0;
	}
	public static BeanFactory getFactory(){
		return factory;
	}

}
