package cn.future.pay.service.tenpay;

import java.io.UnsupportedEncodingException;

import cn.future.pay.pojo.TenpayRequest;

public interface TenpayRequestService {

	/**
	 * 获取带参数的请求URL
	 * @return String
	 * @throws UnsupportedEncodingException 
	 */
	public abstract String getRequestURL(TenpayRequest payRequest)
			throws UnsupportedEncodingException;

}