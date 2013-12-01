package cn.future.util;

import java.util.HashMap;
import java.util.Map;

public class StringMapUtil {
	/**
	 * 将数据转为map
	 * @param data   xxx=1&yy=2
	 * @param dataSparate1  &
	 * @param dataSparate12 =
	 * @return
	 */
	public static Map<String,String> getMap(String data, String dataSparate1, String dataSparate2){
		Map<String,String> map = new HashMap<String,String>();
		if(null!=data){
			String[] d1 = data.split(dataSparate1);
			if(null!=d1){
				for(String d2:d1){
					String[] items = d2.split(dataSparate2);
					if(null!=items && items.length>1){
						map.put(items[0], items[1]);
					}
				}
			}
		}
		return map;
	}
}
