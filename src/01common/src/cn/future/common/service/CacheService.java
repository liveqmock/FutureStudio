package cn.future.common.service;

import java.util.HashMap;
/**
 * 内存缓存,用全局静态HashMap<String,Object>对象,将数据进行缓存。
 * 不直接使用，通过具体的服务使用。服务在自身关键重复数据位置使用。
 * @author Tony
 */
public class CacheService {
	private static HashMap<String,Object> cache=new HashMap<String,Object>();
	/**
	 * 将对象存入缓存
	 * @param key 键
	 * @param o 数据对象
	 */
	public static void add(String key,Object o){
		cache.put(key, o);
	}
	/**
	 * 取出缓存对象
	 * @param key
	 * @return
	 */
	public static Object find(String key){
		return cache.get(key);
	}
}
