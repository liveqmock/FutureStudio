package cn.future.common.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import cn.future.common.service.ConfigHelper;
import cn.future.util.StringUtil;
/**
 * 读取配置文件信息
 *
 */
public class ConfigHelperImpl implements Serializable,ConfigHelper{
	private static final long serialVersionUID = -6002611574036061786L;
	
	private static final String FILE_PATTERN = "classpath*:/cn/future/config/file/*.properties";
	private static final String FILE_ENCODE_PATTERN = "classpath*:/cn/future/config/fileencoded/*.properties";
	
	private static HashMap<String,String> params=new HashMap<String,String>();
	private Logger logger = LoggerFactory.getLogger(ConfigHelperImpl.class);
	/**
	 * 加载配置文件
	 */
	public ConfigHelperImpl(){
		ResourcePatternResolver resoler = new PathMatchingResourcePatternResolver() ;
		//Level 1 config add
		InputStream is = this.getClass().getResourceAsStream("/cn/future/config/app.properties");
		this.resolveValue(is, false);
		//level 2 config add
		try {
			Resource[] firstRes = resoler.getResources(FILE_PATTERN);
			if(firstRes != null){
				for(Resource r : firstRes){
					this.resolveValue(r.getInputStream(), false);
				}
			}
			
			Resource[] encodeRes = resoler.getResources(FILE_ENCODE_PATTERN);
			if(encodeRes != null){
				for(Resource e : encodeRes){
					this.resolveValue(e.getInputStream(), true);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public void resolveValue(InputStream i, boolean encoded){
		try {
			@SuppressWarnings("unchecked")
			List<String> lines = IOUtils.readLines(i);
			for(String l : lines){
				if(StringUtil.notEmpty(l)){
					int k = l.indexOf("=");
					if(k==-1){
						continue;
					}
					String key = l.substring(0, k);
					String value = l.substring(k+1, l.length());
					if(key!=null && value!=null){
						if(encoded){
							value=StringUtil.UrlDecoder(value);
						}
						params.put(key, value);
						logger.info("ConfigHelperImpl : key : "+key + " , value : " + value);
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * 获取单个配置
	 * @param key
	 * @param defaultValue 对应的value不存在的时候返回这个值
	 * @return
	 */
	public static String getProperty(String key,String defaultValue){
		String res = params.get(key);
		if(res==null){
			return defaultValue;
		}else{
			return res;
		}
	}
}
