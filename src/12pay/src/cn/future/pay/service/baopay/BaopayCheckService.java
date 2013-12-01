package cn.future.pay.service.baopay;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;

import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.pay.exception.PayBusinessIdUnuquieException;
import cn.future.pay.exception.PayCheckRemoteFailException;
import cn.future.pay.pojo.BaoReturn;

public abstract interface BaopayCheckService {
	/**
	 * 校验返回信息
	 * @param br
	 * @throws NotFindException 
	 * @throws SystemBusyException 
	 * @throws PayBusinessIdUnuquieException 
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws ParseException 
	 * @throws PayCheckRemoteFailException 
	 */
	public BaoReturn checkResult(BaoReturn br) throws NotFindException, PayBusinessIdUnuquieException, SystemBusyException, PayCheckRemoteFailException, ParseException, ClientProtocolException, IOException;
	/**
	 * 
	 * @param transId
	 * @return
	 * @throws NotFindException
	 * @throws PayBusinessIdUnuquieException
	 * @throws SystemBusyException
	 * @throws PayCheckRemoteFailException
	 * @throws ParseException
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public BaoReturn checkResult(String transId) throws NotFindException, PayBusinessIdUnuquieException, SystemBusyException, PayCheckRemoteFailException, ParseException, ClientProtocolException, IOException;

	/**
	 * 远端校验数据
	 * @param transID
	 * @return
	 * @throws ParseException
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws PayCheckRemoteFailException
	 */
	public BaoReturn checkResultRemote(String transID)throws ParseException, ClientProtocolException, IOException, PayCheckRemoteFailException;
	/**
	 * 远端检查数据正确性, 获取从远端返回的真实数据
	 * @param br
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 * @throws ParseException 
	 * @throws PayCheckRemoteFailException 
	 */
	public BaoReturn checkResultRemote(BaoReturn br) throws ParseException, ClientProtocolException, IOException, PayCheckRemoteFailException;

	/**
	 *   支付成功
	 * @param tr
	 * @throws NotFindException 
	 * @throws SystemBusyException 
	 * @throws PayBusinessIdUnuquieException 
	 */
	public void updateResult(BaoReturn tr) throws NotFindException, PayBusinessIdUnuquieException, SystemBusyException;
}
