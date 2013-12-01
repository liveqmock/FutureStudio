package cn.future.network.service.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import cn.future.network.service.HttpService;

public class HttpServiceImpl implements HttpService{
	
	//是否统一上下文
	private HttpClient httpClient;
	private CookieStore cookieStore;
	private HttpContext httpContext;
	
	public HttpServiceImpl(){
		httpClient =  new DefaultHttpClient();
		cookieStore = new BasicCookieStore();
		httpContext = new BasicHttpContext();
		httpContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
	}
	public HttpEntity getHttpEntity(Map<String,String> params) throws UnsupportedEncodingException{
		return new UrlEncodedFormEntity(this.getParams(params));
	}
	public HttpEntity httpPost(String url, Map<String,String> params) throws ClientProtocolException, IOException{
		HttpEntity entity = this.getHttpEntity(params);
		return this.httpPost(url, entity);
	}
	public HttpEntity httpPost(String url, HttpEntity entity) throws ClientProtocolException, IOException{
		HttpPost httppost = new HttpPost(url);
		httppost.setEntity(entity);
		return this.doMethod(httppost);
	}
	
	public HttpEntity httpGet(String url, Map<String,String> params) throws ClientProtocolException, IOException, URISyntaxException{
		HttpGet method = new HttpGet(url);
		if(params!=null){
			String str = EntityUtils.toString(new UrlEncodedFormEntity(this.getParams(params)));
			method.setURI(new URI(method.getURI().toString() + "?" + str));
		}
		return this.doMethod(method);
	}
	/* (non-Javadoc)
	 * @see cn.future.network.service.impl.HttpService#httpGetString(java.lang.String, java.util.Map)
	 */
	@Override
	public String httpGetString(String url, Map<String,String> params) throws ClientProtocolException, IOException, URISyntaxException{
		HttpEntity entity = this.httpGet(url, params);
		return EntityUtils.toString(entity);
	}
	/* (non-Javadoc)
	 * @see cn.future.network.service.impl.HttpService#httpGetByte(java.lang.String, java.util.Map)
	 */
	@Override
	public byte[] httpGetByte(String url, Map<String,String> params) throws ClientProtocolException, IOException, URISyntaxException{
		HttpEntity entity = this.httpGet(url, params);
		return EntityUtils.toByteArray(entity);
	}

	/* (non-Javadoc)
	 * @see cn.future.network.service.impl.HttpService#httpPostString(java.lang.String, java.util.Map)
	 */
	@Override
	public String httpPostString(String url, Map<String,String> params) throws ParseException, ClientProtocolException, IOException{
		return EntityUtils.toString(this.httpPost(url, params));
	}
	/* (non-Javadoc)
	 * @see cn.future.network.service.impl.HttpService#httpPostByte(java.lang.String, java.util.Map)
	 */
	@Override
	public byte[] httpPostByte(String url, Map<String,String> params) throws ParseException, ClientProtocolException, IOException{
		return EntityUtils.toByteArray(this.httpPost(url, params));
	}
	public List<NameValuePair> getParams(Map<String,String> params){
		List<NameValuePair> values = new ArrayList<NameValuePair>();
		if(params!=null && params.keySet().size()>0){
			for(String key : params.keySet()){
				values.add(new BasicNameValuePair(key, params.get(key)));
			}
		}
		return values;
	}

	public HttpEntity doMethod(HttpUriRequest method) throws ClientProtocolException, IOException{
		HttpResponse httpresponse = httpClient.execute(method, httpContext);
		HttpEntity entity = httpresponse.getEntity();
		
		return entity;
	}

	@Override
	public InputStream httpGetInputStream(String url, Map<String, String> params)
			throws ClientProtocolException, IOException, URISyntaxException {
		InputStream is = new ByteArrayInputStream(this.httpGetByte(url, params));
		return is;
	}

	@Override
	public InputStream httpPostInputStream(String url, Map<String, String> params)
			throws ParseException, ClientProtocolException, IOException {
		InputStream is = new ByteArrayInputStream(this.httpPostByte(url, params));
		return is;
	}

	@Override
	public String httpPostString(String url, HttpEntity entity)
			throws ParseException, ClientProtocolException, IOException {
		HttpEntity responseEntity = this.httpPost(url, entity);
		return EntityUtils.toString(responseEntity);
	}
}
