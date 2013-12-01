package cn.future.pay.service.tenpay;

import cn.future.pay.pojo.TenpayReturn;

public interface TenpayCheckService {

	/**
	 * 构造函数
	 * 
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	public abstract boolean initCheck(TenpayReturn tenpayReturn)
			throws Exception;

}