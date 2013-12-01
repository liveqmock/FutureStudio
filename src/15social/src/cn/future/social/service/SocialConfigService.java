package cn.future.social.service;

public abstract interface SocialConfigService {
	/**
	 * 获取web地址
	 * @return
	 */
	public String getWebUrl();
	/**
	 * 获取腾讯互联ID
	 * @return
	 */
	public String getTenConnectId();
	/**
	 * 获取腾讯互联KEY
	 * @return
	 */
	public String getTenConnectKey();
}
