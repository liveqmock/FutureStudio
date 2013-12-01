package cn.future.social.action;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.common.service.impl.ConfigHelperImpl;
import cn.future.customer.dto.DCustomer;
import cn.future.customer.pojo.PCustomer;
import cn.future.customer.service.CustomerQueryService;
import cn.future.social.exception.SocialQQSignException;
import cn.future.social.pojo.PSocialBind;
import cn.future.social.service.SocialBindQueryService;
import cn.future.social.service.SocialQQService;
import cn.future.social.util.SocialUtil;
import cn.future.util.SessionUtil;
import cn.future.util.StringUtil;

public class QQSignAction extends BaseAction{

	private static final long serialVersionUID = -7492536935085493384L;
	//set
	private String code;
	private String state;
	private SocialQQService socialQQService;
	private SocialBindQueryService socialBindQueryService;
	private CustomerQueryService customerQueryService;
	
	//get
	private String jspPage;
	private String params;
	/**
	 * 
	 * @return
	 */
	public String in(){
		//如果，open ID 已经绑定，转到客户页面，如果没有绑定，转到绑定页面
		try {
			String token = socialQQService.findAccessTokenByAuthorizationCode(code, state);
			
			String openId = socialQQService.findOpenIdByAccessToken(token);
			int count = socialBindQueryService.findCustomerQqBindUnique(openId);
			if(count==0){
				String nickName = socialQQService.findQqNickName(token, openId);
				//去绑定账户or注册账户页面
				jspPage = ConfigHelperImpl.getProperty("socialBindPage", "");
				params = "bindType=qq";
				HashMap<String,String> sessionParams = new HashMap<String,String>();
				sessionParams.put("accessToken", token);
				sessionParams.put("openId", openId);
				sessionParams.put("nickName", nickName);
				SessionUtil.setAttribute(request, SocialUtil.SOCIAL_SESSION, sessionParams);
				return REDIRECTURL;
			}else{
				//去我的用户
				jspPage = "";
				PSocialBind sb = socialBindQueryService.findPSocialBindByQQ(openId);
				String userId = sb.getUserId();
				PCustomer pojo = customerQueryService.findCustomerById(userId);
				DCustomer dto = customerQueryService.transfer(pojo);
				SessionUtil.setSession(request, dto);
				jspPage = ConfigHelperImpl.getProperty("socialSignSuccess", "");
				return REDIRECTURL;//"redirecturl";
			}
		} catch (SocialQQSignException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//绑定失败，退回原页面
		jspPage = ConfigHelperImpl.getProperty("socialSignSuccess", "");
		params = "reason="+StringUtil.UrlEncoder("绑定失败");
		return REDIRECTURL;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setCode(String code) {
		this.code = code;
	}
	public String getJspPage() {
		return jspPage;
	}
	public void setState(String state) {
		this.state = state;
	}
	public void setSocialQQService(SocialQQService socialQQService) {
		this.socialQQService = socialQQService;
	}
	public void setSocialBindQueryService(
			SocialBindQueryService socialBindQueryService) {
		this.socialBindQueryService = socialBindQueryService;
	}
	public void setCustomerQueryService(CustomerQueryService customerQueryService) {
		this.customerQueryService = customerQueryService;
	}
	public String getParams() {
		return params;
	}
}
