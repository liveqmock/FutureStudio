package cn.future.weixin.action;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import org.apache.commons.io.IOUtils;
import org.dom4j.DocumentException;
import org.slf4j.*;

import cn.future.common.action.BaseAction;
import cn.future.util.StringUtil;
import cn.future.weixin.service.WeixinMessageFactory;
import cn.future.weixin.util.WeixinMessageUtil;

public class MessageAction extends BaseAction {

	private static final long serialVersionUID = 3816904958033477213L;
	private Logger logger = LoggerFactory.getLogger(MessageAction.class);
	private String signature = null;
	private String timestamp;
	private String nonce;
	private String echostr = "";
	private String dataString = null;
	private WeixinMessageFactory weixinMessageFactory;

	// set -- get

	/**
	 * 微信消息处理， 分别按照 GET 和 POST 处理
	 * 
	 * @return
	 */
	public String in() {
		PrintWriter pw;
		String method = request.getMethod();
		try {
			request.setCharacterEncoding("UTF-8");  
	        response.setCharacterEncoding("UTF-8");  
	        
			pw = this.response.getWriter();
			if ("GET".equalsIgnoreCase(method)) {
				logger.info("weixin signature:" + signature + ",nonce:" + nonce + ",timestamp:" + timestamp);
				if (WeixinMessageUtil.validate(timestamp, nonce, signature)) {
					pw.print(echostr);
				}
			} else {
				if (null == dataString) {
					InputStream dataStream = this.request.getInputStream();
					dataString = IOUtils.toString(dataStream);
				}
				logger.debug("weixin data:" + dataString);
				if (StringUtil.notEmpty(dataString)) {
					String res = weixinMessageFactory
							.createCustomerResponseMessage(dataString);
					logger.info("weixin res:" + res);
					pw.print(res);
				} else {
					pw.print("");
				}
			}
			pw.flush();
			pw.close();
			pw=null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

	public void setWeixinMessageFactory(
			WeixinMessageFactory weixinMessageFactory) {
		this.weixinMessageFactory = weixinMessageFactory;
	}

	public void setDataString(String dataString) {
		this.dataString = dataString;
	}

}
