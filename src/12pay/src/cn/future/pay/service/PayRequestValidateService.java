package cn.future.pay.service;

import java.io.IOException;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;

import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.pay.exception.PayBusinessIdUnuquieException;
import cn.future.pay.exception.PayCheckRemoteFailException;

public abstract interface PayRequestValidateService {
	/**
	 * 远端验证结果，验证成功之后，修改信息
	 * @param id
	 * @return
	 * @throws NotFindException
	 * @throws PayCheckRemoteFailException
	 * @throws ParseException
	 * @throws ClientProtocolException
	 * @throws IOException
	 * @throws PayBusinessIdUnuquieException
	 * @throws SystemBusyException
	 */
	public String validatePayRequestRemote(String id) throws NotFindException, PayCheckRemoteFailException, ParseException, ClientProtocolException, IOException, PayBusinessIdUnuquieException, SystemBusyException;

}
