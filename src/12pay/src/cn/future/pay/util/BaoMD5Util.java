package cn.future.pay.util;

import java.security.MessageDigest;

import cn.future.pay.pojo.BaoRequest;
import cn.future.pay.pojo.BaoReturn;

public class BaoMD5Util {
	/**
	 * 签名算法
	 * @param po
	 * @param md5Key
	 * @return
	 */
	public static String getMD5ofStr(BaoReturn po, String md5Key){
		String md5 =new String(
				po.getMerchantID()+
				po.getTransID()+
				po.getResult()+
				po.getResultDesc()+
				po.getFactMoney()+
				po.getAdditionalInfo()+
				po.getSuccTime()+
				md5Key);//MD5签名格式
		String Md5Sign = BaoMD5Util.getMD5ofStr(md5);//计算MD5值
		return Md5Sign;
	}
	/**
	 * 获取md5加密数据
	 * @param po
	 * @param md5Key
	 * @return
	 */
	public static String getMD5ofStr(BaoRequest po, String md5Key){
		String md5 =new String(
				po.getMerchantID()+
				po.getPayID()+
				po.getTradeDate()+
				po.getTransID()+
				po.getOrderMoney()+
				po.getMerchant_url()+
				po.getReturn_url()+
				po.getNoticeType()+md5Key);//MD5签名格式
		String Md5Sign = BaoMD5Util.getMD5ofStr(md5);//计算MD5值
		return Md5Sign;
	}
	/**
	 * 获取宝付MD5数据
	 * @param str
	 * @return
	 */
	public static String getMD5ofStr(String str){
		return BaoMD5Util.getMD5ofStr(str,"utf-8");
	}
	
	public static String getMD5ofStr(String str, String encode) {
		try{
		MessageDigest md5 = MessageDigest.getInstance("MD5");
		md5.update(str.getBytes(encode));
		byte[] digest = md5.digest();

		StringBuffer hexString = new StringBuffer();
		String strTemp;
		for (int i = 0; i < digest.length; i++) {
			// byteVar &
			// 0x000000FF的作用是，如果digest[i]是负数，则会清除前面24个零，正的byte整型不受影响。
			// (...) | 0xFFFFFF00的作用是，如果digest[i]是正数，则置前24位为一，
			// 这样toHexString输出一个小于等于15的byte整型的十六进制时，倒数第二位为零且不会被丢弃，这样可以通过substring方法进行截取最后两位即可。
			strTemp = Integer.toHexString(
					(digest[i] & 0x000000FF) | 0xFFFFFF00).substring(6);
			hexString.append(strTemp);
		}
			return hexString.toString();
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}

	}
	/**
	 * 返回信息校验
	 * @param result
	 * @param resultDesc
	 * @return
	 */
	public static String getErrorInfo(String result, String resultDesc){
		String retInfo = "";
		int a;
		int b;
		if (!"".equals(result)) 
		{
			a = Integer.parseInt(result);
		}else{
			a = 0;
		}
		if(!"".equals(resultDesc)){
		b = Integer.parseInt(resultDesc);
		}else{
			b = 0;
		}	

		if(a == 1){
			retInfo ="支付成功";
			return retInfo;
		}
		else
			{
				switch(b){
					case 0:
						retInfo = "充值失败";
						break;
					case 1:
						retInfo = "系统错误";
						break;
					case 2:
						retInfo = "订单超时";
						break;
					case 11:
						retInfo = "系统维护";
						break;
					case 12:
						retInfo = "无效商户";
						break;
					case 13:
						retInfo = "余额不足";
						break;
					case 14:
						retInfo = "超过支付限额";
						break;
					case 15:
						retInfo = "卡号或卡密错误";
						break;
					case 16:
						retInfo = "不合法的IP地址";
						break;
					case 17:
						retInfo = "重复订单金额不符";
						break;
					case 18:
						retInfo = "卡密已被使用";
						break;
					case 19:
						retInfo = "订单金额错误";
						break;
					case 20:
						retInfo = "支付的类型错误";
						break;
					case 21:
						retInfo = "卡类型有误";
						break;
					case 22:
						retInfo = "卡信息不完整";
						break;
					case 23:
						retInfo = "卡号，卡密，金额不正确";
						break;
					case 24:
						retInfo = "不能用此卡继续做交易";
						break;
					case 25:
						retInfo = "订单无效";
						break;
					case 26:
						retInfo = "卡无效";
						break;
					default:
						retInfo = "支付失败";
						break;
					}

					return retInfo;
				}
			}
}
