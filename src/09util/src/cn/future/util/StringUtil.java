package cn.future.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.UUID;

import org.apache.shiro.codec.Base64;
import org.apache.shiro.crypto.hash.Sha512Hash;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class StringUtil {
	public static int count = 10000000;
	/**
	 * 校验字符串长度
	 * @param src
	 * @param min
	 * @param max
	 * @return
	 */
	public static boolean validateLength(String src,int min,int max){
		if(src!=null){
			if(min <= src.length() && src.length() <= max){
				return true;
			}
		}
		return false;
	}

	/**
	 * 把对象转换成字符串
	 * @param obj
	 * @return String 转换成字符串,若对象为null,则返回空字符串.
	 */
	public static String toString(Object obj) {
		if(obj == null)
			return "";
		
		return obj.toString();
	}
	/**
	 * string转html代码
	 * @param in
	 * @return
	 */
	public static String toHTMLString(String in){   
	   StringBuffer out = new StringBuffer();   
	     for(int i = 0; in != null && i < in.length(); i++)   
	     {   
	             char   c   =   in.charAt(i);   
	             if   (c   ==   '\'')   
	                     out.append("&#039;");   
	             else   if   (c == '\"')   
	                     out.append("&#034;");   
	             else   if   (c == '<')   
	                     out.append("&lt;");   
	             else   if   (c == '>') 
	                     out.append("&gt;");   
	             else   if   (c == '&')   
	                     out.append("&amp;");   
	             else   if   (c == ' ')   
	                     out.append("&nbsp;");   
	             else   if   (c   ==   '\n')   
	                     out.append("<br>");   
	             else   
	                     out.append(c);   
	     }   
	     return   out.toString();   
	}
	/**
	 * stream 到 string
	 * @param is
	 * @return
	 */
	public static String inputStreamToString(InputStream is){
		BufferedReader reader;
		StringBuilder sb = new StringBuilder();    
		try {
			reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
	        String line = null;    
	       try {    
	           while ((line = reader.readLine()) != null) {    
	                sb.append(line + "\n");    
	            }    
	        } catch (IOException e) {    
	            e.printStackTrace();    
	        } finally {    
	           try {    
	                is.close();    
	            } catch (IOException e) {    
	                e.printStackTrace();    
	            }    
	        }    

		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}    
	    return sb.toString();
	}
	/**
	 * 生成指定长度的流水号
	 * @param length
	 * @return
	 */
	public static String serialNumber(int length){
		count++;//3位
		if(count>999){
			count = 100;
		}
		//当前时间 yyyyMMddHHmmss 14位
		String currTime = TimeUtil.getCurrTime();
		//四位随机数
		String strRandom = IntUtil.buildRandom(length-17) + "";
		//10位序列号,可以自行调整。
		String strReq = currTime +count + strRandom;
		//订单号，此处用时间加随机数生成，商户根据自己情况调整，只要保持全局唯一就行
		return strReq;
	}
	/**
	 * 流水ID生成方法
	 * @return
	 */
	public static String serialNumber(){
		return StringUtil.serialNumber(32);
	}
	/**
	 * 字符串加密
	 * @param src 源字符串
	 * @param mix 混淆字符串
	 * @return
	 */
	public static String sha512Encrypt(String src, String mix){
		return new Sha512Hash(src,mix,1).toBase64();
	}
	/**
	 * 密码加密机制
	 * @param password
	 * @return
	 */
	public static String sha512Encrypt(String password){
		return StringUtil.sha512Encrypt(password, "studio");
	}
	/**
	 * 获取解码的URL字符串
	 * @param s
	 * @return
	 */
	public static String UrlDecoder(String s){
		String rs = "";
		if(s==null){
			return rs;
		}
		try {
			rs = URLDecoder.decode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return rs;
	}
	/**
	 * URLEncoding 编码字符串
	 * @param s
	 * @return
	 */
	public static String UrlEncoder(String s){
		String rs="";
		if(s==null){
			return rs;
		}
		try {
			rs=URLEncoder.encode(s, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	
	/**
	 * 按照base64进行解码
	 * @param value
	 * @return
	 */
	public static String base64Decode(String value){
		if(null==value){
			return "";
		}
		return Base64.decodeToString(value);
	}
	/**
	 * 按照base64进行编码
	 * @param key
	 * @return
	 */
	public static String base64Encode(String key){
		if(null==key){
			return "";
		}
		return Base64.encodeToString(key.getBytes());		
	}
	/**
	 * 生成指定个数的32位uuid
	 * @param n 生成的id个数
	 * @return uuid数组，长度位n
	 */
	public static String[] getUuid(int n){
		String uuid[] = new String[n];
		for(int i=0; i<n; i++){
			uuid[i]=getUuid();
		}
		return uuid;
	}
	/**
	 * 生成32位uuid
	 * @return 32位String类型的uuid
	 */
	public static String getUuid(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	/**
	 * 获取拼音的字符串，但是多音字会被一起添加进来,如果超过250个字符，会被截断。
	 * @param src
	 * @return
	 */
	public static String getPinyinString(String src){
		String py="";
		String pys[][] = StringUtil.getPinyin(src);
		if(pys!=null){
			for(String s1[]:pys){
				if(s1!=null){
					for(String s:s1){
						py+=s;
					}
				}
			}
		}
		if(py.length()>250){
			py=py.substring(0, 249);
		}
		return py;
	}
	/**
	 * 汉字转拼音。
	 * @param src 汉字字符串，不应当有空格。
	 * @return 每个汉字的多音，所以返回的是数组的数组。每个汉字返回一个数组，每个数组里面每个多音是一个String
	 */
	public static String[][] getPinyin(String src){
		if(src!=null && !src.trim().equalsIgnoreCase("")){
			char[] srcChar = src.toCharArray();
			HanyuPinyinOutputFormat pinyinFormat = new HanyuPinyinOutputFormat();
			pinyinFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
			pinyinFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
			pinyinFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
			
			String[][] temp = new String[src.length()][];
			for(int i=0; i<srcChar.length; i++){
				char c = srcChar[i];
				if(String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")){  
				     try{  
				      temp[i] = PinyinHelper.toHanyuPinyinStringArray(srcChar[i], pinyinFormat);  
				     }catch(BadHanyuPinyinOutputFormatCombination e) {  
				      e.printStackTrace();  
				     }  
				}else if(((int)c>=65 && (int)c<=90) || ((int)c>=97 && (int)c<=122)){  
				     temp[i] = new String[]{String.valueOf(srcChar[i])};  
				}else{  
				     temp[i] = new String[]{""};  
				}  
				   
			}
			return temp;
		}else{
			return null;
		}
		
	}
	/**
	 * 不是空
	 * @param src
	 * @return  true 非空 || false 空orNull
	 */
	public static boolean notEmpty(String src){
		if(src!=null && !"".equals(src)){
			return true;
		}else{
			return false;
		}
	}
}
