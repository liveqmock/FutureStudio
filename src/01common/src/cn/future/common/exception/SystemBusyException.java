package cn.future.common.exception;


import org.hibernate.StaleObjectStateException;

public class SystemBusyException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7739607558043800809L;
	
	public SystemBusyException(String msg,StaleObjectStateException oe) {
		super(msg);
		oe.printStackTrace();
	}

}
