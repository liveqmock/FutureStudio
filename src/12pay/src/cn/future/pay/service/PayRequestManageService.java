package cn.future.pay.service;

import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.pay.exception.PayBusinessIdUnuquieException;
import cn.future.pay.pojo.PPayRequest;

public abstract interface PayRequestManageService {
	/**
	 * 添加一个付款申请
	 * @param pojo
	 * @return
	 */
	public PPayRequest addPayRequest(PPayRequest pojo);
	/**
	 * 付款成功
	 * @param pojo
	 * @return
	 * @throws SystemBusyException 
	 * @throws NotFindException 
	 * @throws PayBusinessIdUnuquieException 
	 */
	public PPayRequest updatePayRequestSuccess(PPayRequest pojo, String notifyId, String transId,String title) throws PayBusinessIdUnuquieException, NotFindException, SystemBusyException;
	/**
	 * 付款成功；
	 * @param pojoId
	 * @param notifyId
	 * @param transId
	 * @param title
	 * @return
	 * @throws PayBusinessIdUnuquieException
	 * @throws NotFindException
	 * @throws SystemBusyException
	 */
	public PPayRequest updatePayRequestSuccess(String pojoId, String notifyId, String transId,String title) throws PayBusinessIdUnuquieException, NotFindException, SystemBusyException;

}
