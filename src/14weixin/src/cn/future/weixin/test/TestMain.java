package cn.future.weixin.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.HashMap;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import cn.future.network.service.HttpService;
import cn.future.network.service.impl.HttpServiceImpl;
import cn.future.weixin.util.WeixinUtil;

public class TestMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		HttpService hs = new HttpServiceImpl();
		Gson gson = new Gson();
		try {
			String res = hs.httpGetString("", null);
			HashMap<String,String> k = gson.fromJson(res, new TypeToken<HashMap<String,String>>(){}.getType());
			System.out.println(k.get("errcode"));
			System.out.println(res);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
