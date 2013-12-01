package cn.future.pay.service;

import cn.future.pay.pojo.PWithdrawAccount;

public interface WithdrawAccountManageService {
	/**
	 * 添加一个绑定账户, 初始化的时候，为不可用账户
	 * @param pojo
	 * @return
	 */
	public PWithdrawAccount addWithdrawAccount(PWithdrawAccount pojo);
}
