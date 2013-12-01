package cn.future.common.exception;

/**
 * 这是一个公共的异常类
 * @author Tony
 * 不能继承runtime exception 这会导致事务回滚
 */
public class BaseException extends Exception {
	private static final long serialVersionUID = 5007067167348277817L;
	public BaseException(String msg){
		super(msg);
	}

}
