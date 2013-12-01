package cn.future.pay.exception;


public class PayOverSpendException extends PayException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -64757896023538558L;
	
	public PayOverSpendException(String msg){
		super(msg);
	}

}
