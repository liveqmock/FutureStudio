/**
 * Tony FutureStudio soft-qiyao@foxmail.com Dec 23, 2012 10:33:14 AM
 */

package cn.future.user.exception;

import cn.future.common.exception.BaseException;

public class UserNotAllowedAccess extends BaseException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5965956685221184010L;

	public UserNotAllowedAccess (String msg){
		super(msg);
	}
}
