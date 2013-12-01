/**
 * Tony FutureStudio soft-qiyao@foxmail.com Dec 22, 2012 10:45:45 PM
 */

package cn.future.user.exception;

import cn.future.common.exception.BaseException;

public class UserPasswordUnmatchException extends BaseException{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6147486770637283154L;
	public UserPasswordUnmatchException(String msg){
		super(msg);
	}
}
