package cn.future.pay.action;

import java.io.UnsupportedEncodingException;

import org.slf4j.*;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.pay.exception.PayException;
import cn.future.pay.pojo.TenpayReturn;
import cn.future.pay.service.tenpay.TenpayCheckService;
import cn.future.pay.service.tenpay.TenpayFinishService;
import cn.future.pay.service.tenpay.TenpayPayService;


public class CallBackAction extends BaseAction {
	private TenpayFinishService tenpayFinishService;
	private TenpayCheckService tenpayCheckService;
	private TenpayPayService tenpayPayService;
	// set -- get
	private static final long serialVersionUID = -1542058482174136172L;
	private String paySystem;
	private String jspPage;
	private Logger logger = LoggerFactory.getLogger(CallBackAction.class);
	/**
	 * 财付通服务器通知
	 * @return
	 */
	public String tenpay() {
		tenpayFinishService.initService(request);
		paySystem = "tenpay";
		TenpayReturn tenpayReturn;
		try {
			jspPage="notify_fail.jsp";
			tenpayReturn = tenpayFinishService.findTenpayReturnInfo();
			if(tenpayCheckService.initCheck(tenpayReturn)){
				logger.info("付款验证成功");
				tenpayPayService.updatePaySuccess(tenpayReturn);
				logger.info("付款保存数据成功");
				jspPage="notify_success.jsp";
			}
		} catch (PayException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SystemBusyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "successjsp";
	}

	public String getPaySystem() {
		return paySystem;
	}

	public String getJspPage() {
		return jspPage;
	}

	public void setTenpayFinishService(cn.future.pay.service.tenpay.TenpayFinishService tenpayFinishService) {
		this.tenpayFinishService = tenpayFinishService;
	}

	public void setTenpayCheckService(TenpayCheckService tenpayCheckService) {
		this.tenpayCheckService = tenpayCheckService;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setTenpayPayService(TenpayPayService tenpayPayService) {
		this.tenpayPayService = tenpayPayService;
	}

}
