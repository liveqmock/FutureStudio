package cn.future.social.service.impl;

import cn.future.common.service.impl.ConfigHelperImpl;
import cn.future.social.service.SocialConfigService;

public class SocialConfigServiceImpl implements SocialConfigService {
	private static String tenConnectId = null;
	private static String tenConnectKey = null;
	private static String webUrl = null;
	
	public SocialConfigServiceImpl(){
		if(null==tenConnectId){
			tenConnectId = ConfigHelperImpl.getProperty("tenConnectId", null);
		}
		if(null==tenConnectKey){
			tenConnectKey = ConfigHelperImpl.getProperty("tenConnectKey", null);
		}
		if(null==webUrl){
			webUrl = ConfigHelperImpl.getProperty("webUrl", null);
		}
	}
	@Override
	public String getWebUrl(){
		return webUrl;
	}
	@Override
	public String getTenConnectId() {
		return tenConnectId;
	}

	@Override
	public String getTenConnectKey() {
		return tenConnectKey;
	}

}
