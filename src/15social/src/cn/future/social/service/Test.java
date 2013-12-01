package cn.future.social.service;

import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test t = new Test();
		t.t1();
	}
	public void t1(){
		String s = "callback( {\"client_id\":\"100516783\",\"openid\":\"8BA96EF9AA12EACD2A652D951D26AD8C\"} ); ";
		s=s.replaceAll("callback\\(", "");
		s=s.replaceAll("\\);", "");
		Gson gson = new Gson();
		TypeToken<HashMap<String,String>> tt = new TypeToken<HashMap<String,String>>(){};
		HashMap<String,String> map = gson.fromJson(s, tt.getType());
		System.out.println(map);
	}
}
