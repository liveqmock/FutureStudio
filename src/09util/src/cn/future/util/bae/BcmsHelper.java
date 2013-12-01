package cn.future.util.bae;

import cn.future.common.service.impl.ConfigHelperImpl;

import com.baidu.bae.api.bcms.BaeBcms;
import com.baidu.bae.api.bcms.client.BCMSRestClient;
import com.baidu.bae.api.bcms.core.model.CServKeyPair;
import com.baidu.bae.api.util.BaeEnv;

public class BcmsHelper {
	public static BaeBcms bcms = null;
	public static String ak;
	public static String sk;
	public static String bcmsHost;
	public static String bcmsMailQueue;
	
	public static BaeBcms getBaeBcms(){
		if(bcms==null){
			BcmsHelper.initBaeBcms();
		}
		return bcms;
	}
	
	public static void initBaeBcms(){
		ak = ConfigHelperImpl.getProperty("ak", "");
		sk = ConfigHelperImpl.getProperty("sk", "");
		bcmsHost = BaeEnv.getBaeHeader("BAE_ENV_ADDR_BCMS");
		bcmsMailQueue = ConfigHelperImpl.getProperty("bcmsMail", "");
		CServKeyPair keyPair = new CServKeyPair(ak, sk);
		bcms = new BCMSRestClient(keyPair, bcmsHost);
	}
}
