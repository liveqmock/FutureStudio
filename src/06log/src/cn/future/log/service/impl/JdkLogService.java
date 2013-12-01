package cn.future.log.service.impl;

import java.util.logging.Level;
import java.util.logging.Logger;

import cn.future.log.service.LogService;

public class JdkLogService implements LogService{
	private Logger logger;
	
	public JdkLogService(){
		logger = Logger.getLogger("");
	}
	@Override
	public <T> void info(Class<T> t, String method, String msg) {
		logger.logp(Level.INFO, t.getName(), method, msg);
	}

	@Override
	public <T> void warning(Class<T> t, String method, String msg) {
		logger.logp(Level.WARNING, t.getName(), method, msg);
	}

	@Override
	public <T> void error(Class<T> t, String method, String msg) {
		logger.logp(Level.SEVERE, t.getName(), method, msg);
	}

	@Override
	public <T> void throwException(Class<T> t, String sourceClass, String sourceMethod, Exception e) {
		logger.logp(Level.SEVERE, sourceClass, sourceMethod, e.getMessage(), e);
	}
	
}
