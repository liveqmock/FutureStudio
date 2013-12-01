package cn.future.weixin.util;

import java.security.MessageDigest;
import java.util.Arrays;

import cn.future.util.StringUtil;

public class WeixinMessageUtil {
	/**
	 * 验证微信消息URL
	 * @param timestamp
	 * @param nonce
	 * @param signature
	 * @return
	 */
	public static boolean validate(String timestamp,String nonce,String signature){
		if(StringUtil.notEmpty(timestamp) && StringUtil.notEmpty(nonce) && StringUtil.notEmpty(signature)){
			
		}else{
			return false;
		}
		String[] datas = {WeixinUtil.MESSAGE_TOKEN,timestamp,nonce};
		Arrays.sort(datas);
		String dataString = WeixinMessageUtil.ArrayToString(datas);
		String dataSha1 = WeixinMessageUtil.SHA1Encode(dataString);
		if(dataSha1.equalsIgnoreCase(signature)){
            return true;  
        }else{  
            return false;  
        }  
	}
	
	public static String ArrayToString(String [] arr){  
        StringBuffer bf = new StringBuffer();  
        for(int i = 0; i < arr.length; i++){  
         bf.append(arr[i]);  
        }  
        return bf.toString();  
    }  
	
	public static String SHA1Encode(String sourceString) {  
        String resultString = null;  
        try {  
           resultString = new String(sourceString);  
           MessageDigest md = MessageDigest.getInstance("SHA-1");  
           resultString = WeixinMessageUtil.byte2hexString(md.digest(resultString.getBytes()));  
        } catch (Exception ex) {  
        }  
        return resultString;  
    }  
	public static String byte2hexString(byte[] bytes) {  
        StringBuffer buf = new StringBuffer(bytes.length * 2);  
        for (int i = 0; i < bytes.length; i++) {  
            if (((int) bytes[i] & 0xff) < 0x10) {  
                buf.append("0");  
            }  
            buf.append(Long.toString((int) bytes[i] & 0xff, 16));  
        }  
        return buf.toString().toUpperCase();  
    }  
}
