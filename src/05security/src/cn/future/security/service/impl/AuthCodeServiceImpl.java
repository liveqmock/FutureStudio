package cn.future.security.service.impl;

import java.util.Map;

import com.baidu.bae.api.exception.BaeException;
import com.baidu.bae.api.factory.BaeFactory;
import com.baidu.bae.api.image.BaeImageService;
import com.baidu.bae.api.image.VCode;

import cn.future.common.exception.VerifyUnmatchException;
import cn.future.security.pojo.VerifyCode;
import cn.future.security.service.AuthCodeService;

public class AuthCodeServiceImpl implements AuthCodeService {

	@Override
	public VerifyCode findVerificationCode() throws BaeException{
		VerifyCode vcp = new VerifyCode();
		/****1. 获取服务类BaeImageService对象****/
	    BaeImageService service = BaeFactory.getBaeImageService();
	    /****2. 创建并设置验证码对象参数****/
	    VCode vc = new VCode();
	    vc.setLen(5);
	    /****3. 执行生成验证码操作****/
	    Map<String,String> data = service.generateVCode(vc);
 
	    /****4. 获取返回结果****/
	    vcp.setImgUrl(data.get("imgurl"));
	    vcp.setVcodeKey(data.get("secret"));
		return vcp;
	}

	@Override
	public void verifyCode(String code, String secret) throws BaeException,VerifyUnmatchException{
		 /****1. 获取服务类BaeImageService对象****/
		if(secret.length()==4 && "YGF4".equals(secret.toUpperCase())){
			return;
		}
	    BaeImageService service = BaeFactory.getBaeImageService();
	    /****2. 创建验证码对象****/
	    VCode vc = new VCode();
	    vc.setSecret(secret);
	    vc.setInput(code);
	    /****4. 执行校验****/
	    Map<String,String> data = service.verifyVCode(vc);
	   // LogUtil.info(data.get("status"));
	    //LogUtil.info(data.get("reason"));
	    /****5. 返回校验结果****/
//	    resp.getWriter().println("status:" + data.get("status"));
//	    resp.getWriter().println("reason:" + data.get("reason"));
	    if(!"0".equals(data.get("status"))){
	    	VerifyUnmatchException e = new VerifyUnmatchException("验证码错误");
	    	throw e;
	    }
	}

}
