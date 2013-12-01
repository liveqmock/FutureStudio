package cn.future.log.service;

public abstract interface LogService {
	public <T> void info(Class<T> t, String method, String message);
	public <T> void warning(Class<T> t, String method, String message);
	public <T> void error(Class<T> t, String method, String message);
	public <T> void throwException(Class<T> t, String sourceClass, String sourceMethod, Exception e);
}
