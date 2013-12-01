/**
 * Tony FutureStudio soft-qiyao@foxmail.com Dec 22, 2012 10:20:27 PM
 */

package cn.future.common.exception;

public class NotFindException extends BaseException{
	/**
	 * 自定义，用户未找到异常
	 */
	private static final long serialVersionUID = -2933309686665174203L;

	public NotFindException(String msg){
		super(msg);
	}
}
