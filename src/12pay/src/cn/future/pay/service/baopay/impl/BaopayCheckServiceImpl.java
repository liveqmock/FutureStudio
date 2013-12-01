package cn.future.pay.service.baopay.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.StringTokenizer;

import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.*;

import cn.future.common.dao.BaseDao;
import cn.future.common.exception.NotFindException;
import cn.future.common.exception.SystemBusyException;
import cn.future.common.service.impl.ConfigHelperImpl;
import cn.future.network.service.HttpService;
import cn.future.pay.exception.PayBusinessIdUnuquieException;
import cn.future.pay.exception.PayCheckRemoteFailException;
import cn.future.pay.pojo.BaoReturn;
import cn.future.pay.pojo.PPayRequest;
import cn.future.pay.service.PayRequestManageService;
import cn.future.pay.service.baopay.BaopayCheckService;
import cn.future.pay.util.BaoMD5Util;

public class BaopayCheckServiceImpl implements BaopayCheckService {
	private HttpService httpService;
	private BaseDao baseDao;
	private PayRequestManageService payRequestManageService;
	private Logger logger = LoggerFactory.getLogger(BaopayCheckServiceImpl.class);

	public void setHttpService(HttpService httpService) {
		this.httpService = httpService;
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	public void setPayRequestManageService(
			PayRequestManageService payRequestManageService) {
		this.payRequestManageService = payRequestManageService;
	}

	@Override
	public BaoReturn checkResult(BaoReturn bs) throws NotFindException, PayBusinessIdUnuquieException, SystemBusyException, PayCheckRemoteFailException, ParseException, ClientProtocolException, IOException {
		return this.checkResult(bs.getTransID());

	}
	@Override
	public BaoReturn checkResult(String transId) throws NotFindException, PayBusinessIdUnuquieException, SystemBusyException, PayCheckRemoteFailException, ParseException, ClientProtocolException, IOException {
		BaoReturn br = this.checkResultRemote(transId);
		if(!"Y".equals(br.getResult().toUpperCase())){
			PayCheckRemoteFailException e = new PayCheckRemoteFailException("支付不成功");
			throw e;
		}
		String Md5key = ConfigHelperImpl.getProperty("baoKey", "");
		String WaitSign = BaoMD5Util.getMD5ofStr(br, Md5key);//计算MD5值
		if(WaitSign.compareTo(br.getMd5Sign())==0){
			//校验通过开始处理订单
			return br;
		}else{
			PayCheckRemoteFailException e = new PayCheckRemoteFailException("支付不成功");
			throw e;
		}

	}
	@Override
	public BaoReturn checkResultRemote(String transID)throws ParseException, ClientProtocolException, IOException, PayCheckRemoteFailException {
		String Md5key = ConfigHelperImpl.getProperty("baoKey", "");
		String url=ConfigHelperImpl.getProperty("baoCheckUrl", "");
		String merchantID = ConfigHelperImpl.getProperty("baoId", "");
		String str=new String(merchantID + transID + Md5key);
		String Md5Sign = BaoMD5Util.getMD5ofStr(str);
		HashMap<String,String> params = new HashMap<String,String>();
		params.put("MerchantID", merchantID);
		params.put("TransID", transID);
		params.put("Md5Sign", Md5Sign);
		String res = httpService.httpPostString(url, params);
		logger.info("宝付检查返回"+res);
		
		String[] s;
		StringTokenizer st = new StringTokenizer(res,"\\|");
		s=new String[st.countTokens()];
		for(int i=0;st.hasMoreTokens();i++)
			{
				s[i]=st.nextToken();
			}
		if(s.length<5){
			PayCheckRemoteFailException e = new PayCheckRemoteFailException("远端检查失败");
			throw e;
		}
		String MerchantID = s[0]; //商户号
		String TransID = s[1]; //商户流水号
		String CheckResult = s[2]; //支付结果
		String factMoney = s[3];//实际成功金额,分为单位
		String SuccTime = s[4];//支付完成时间
		String WaitSign = s[5];//MD5签名
		String MD5 = new String(MerchantID+TransID+CheckResult+factMoney+SuccTime+Md5key);//MD5签名格式
		String MD5Sign = BaoMD5Util.getMD5ofStr(MD5);//计算MD5值
		
		BaoReturn baoCheck = new BaoReturn(MerchantID, TransID, CheckResult,
				"", factMoney, "",
				SuccTime, WaitSign);
		if(WaitSign.compareTo(MD5Sign)==0){			
				return baoCheck;
		}else{
			PayCheckRemoteFailException e = new PayCheckRemoteFailException("远端检查失败");
			throw e;
		}
	}
	@Override
	public BaoReturn checkResultRemote(BaoReturn br) throws ParseException, ClientProtocolException, IOException, PayCheckRemoteFailException {
		return this.checkResultRemote(br.getTransID());
	}

	@Override
	public void updateResult(BaoReturn br) throws NotFindException, PayBusinessIdUnuquieException, SystemBusyException {
		PPayRequest pojo = baseDao.findById(PPayRequest.class, br.getTransID());
		if((pojo.getTotalNumber()+"").compareTo(br.getFactMoney())==0){
			//卡面金额与用户提交金额一致
			//提示：这个int类型的 如果存在小数的话，这个 除法算出的金额可能会不正确，如果存在小数 推荐使用BigDecimal
			//全部正确了输出OK
			payRequestManageService.updatePayRequestSuccess(pojo.getId(), pojo.getId(), pojo.getId(), "宝付支付");
		}else{
			pojo.setTotalNumber(Long.parseLong(br.getFactMoney()));//进行金额修正
			payRequestManageService.updatePayRequestSuccess(pojo, pojo.getId(), pojo.getId(), "宝付支付");
		}
		
	}
}
