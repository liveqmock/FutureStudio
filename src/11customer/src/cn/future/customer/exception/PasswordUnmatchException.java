package cn.future.customer.exception;
/**
 * 客户密码不匹配
 * @author TonyHp
 *
 */
public class PasswordUnmatchException extends CustomerException {

	private static final long serialVersionUID = 106587112006400404L;

	public PasswordUnmatchException(String msg) {
		super(msg);
		// TODO Auto-generated constructor stub
	}

}
