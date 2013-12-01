package cn.future.pay.service.tenpay;

import java.io.UnsupportedEncodingException;

import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.customer.dto.DCustomer;
import cn.future.pay.exception.PayBusinessIdUnuquieException;
import cn.future.pay.pojo.TenpayReturn;

public abstract interface TenpayPayService {
	/**
	 * 更新数据为支付成功，写入cashAccount信息
	 * @param tr
	 * @return
	 * @throws NotFindException 
	 * @throws SystemBusyException 
	 * @throws PayBusinessIdUnuquieException 
	 */
	public void updatePaySuccess(TenpayReturn tr) throws NotFindException, PayBusinessIdUnuquieException, SystemBusyException;
	/**
	 * 用户充值
	 * @param customer
	 * @param cashNumber
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String initRedirectUrl(DCustomer customer, long productNumber, long transNumber,
			String businessId, String title, String content, String userIp,
			String trans_type, String transport_desc,String returnUrl) throws UnsupportedEncodingException;
}
