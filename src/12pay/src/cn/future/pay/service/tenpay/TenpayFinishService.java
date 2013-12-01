package cn.future.pay.service.tenpay;

import javax.servlet.http.HttpServletRequest;

import cn.future.pay.exception.PayException;
import cn.future.pay.pojo.TenpayReturn;

public interface TenpayFinishService {

	/**
	 * 初始化信息
	 * @param request
	 */
	public abstract void initService(HttpServletRequest request);

	public abstract TenpayReturn findTenpayReturnInfo() throws PayException;

}