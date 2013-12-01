package cn.future.pay.service.baopay;

import cn.future.pay.pojo.BaoRequest;
import cn.future.pay.pojo.PPayRequest;

public abstract interface BaopayRequestService {
	/**
	 * 创建一个宝付申请对象
	 * @param pp
	 * @return
	 */
	public BaoRequest createBaoRequest(PPayRequest pp);
}
