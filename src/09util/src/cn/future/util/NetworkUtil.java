package cn.future.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class NetworkUtil {
	private static HttpClient httpClient = new DefaultHttpClient();
	public static InputStream httpGet(String hostUrl,Map<String,String> params) throws ClientProtocolException, IOException{
		if(params!=null){
			hostUrl=hostUrl+"?";
			for(String s: params.keySet()){
				hostUrl=hostUrl+s+"="+params.get(s)+"&";
			}
		}
		HttpGet httpGet = new HttpGet(hostUrl);
		HttpResponse response = httpClient.execute(httpGet);
		HttpEntity entity = response.getEntity();
		InputStream instream;
		if (entity != null) {
			instream = entity.getContent();
			httpGet.abort();
			return instream;
		}else{
			httpGet.abort();
			return null;
		}
	}
	public void httpPost(){
		
	}
	/**
	 * map 参数转为http参数
	 * @param map
	 * @return
	 */
	public static List<NameValuePair> transferParams(Map<String,String> map){
		List<NameValuePair> params = new ArrayList<NameValuePair>();
		if(map!=null){
			for(String key:map.keySet()){
				params.add(new BasicNameValuePair(key, map.get(key)));
			}
		}       
        return params;
	}
}
