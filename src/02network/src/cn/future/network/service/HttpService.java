package cn.future.network.service;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;

public interface HttpService {

	public abstract String httpGetString(String url, Map<String, String> params)
			throws ClientProtocolException, IOException, URISyntaxException;

	public abstract byte[] httpGetByte(String url, Map<String, String> params)
			throws ClientProtocolException, IOException, URISyntaxException;
	
	public abstract InputStream httpGetInputStream(String url, Map<String, String> params)
			throws ClientProtocolException, IOException, URISyntaxException;
	
	public abstract String httpPostString(String url,HttpEntity entity)
			throws ParseException, ClientProtocolException, IOException;
	

	public abstract String httpPostString(String url, Map<String, String> params)
			throws ParseException, ClientProtocolException, IOException;

	public abstract byte[] httpPostByte(String url, Map<String, String> params)
			throws ParseException, ClientProtocolException, IOException;
	
	public abstract InputStream httpPostInputStream(String url, Map<String, String> params)
			throws ParseException, ClientProtocolException, IOException;

}