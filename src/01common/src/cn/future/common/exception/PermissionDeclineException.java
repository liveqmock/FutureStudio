/**
 * Tony FutureStudio soft-qiyao@foxmail.com Dec 22, 2012 10:20:27 PM
 */

package cn.future.common.exception;

public class PermissionDeclineException extends BaseException{
	/**
	 * 自定义，权限不足
	 */
	private static final long serialVersionUID = -2933309686665174203L;

	public PermissionDeclineException(String msg){
		super(msg);
	}
}
