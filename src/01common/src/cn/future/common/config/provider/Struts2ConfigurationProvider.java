package cn.future.common.config.provider;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.opensymphony.xwork2.config.ConfigurationException;
import com.opensymphony.xwork2.config.providers.XmlConfigurationProvider;
import com.opensymphony.xwork2.inject.ContainerBuilder;
import com.opensymphony.xwork2.util.location.LocatableProperties;

public class Struts2ConfigurationProvider extends XmlConfigurationProvider{
	private static final String FILE_FIRST_PATTERN = "classpath*:struts.xml";
	private static final String FILE_PATTERN = "classpath*:/cn/future/struts/struts.xml" ;// classpath*:**/struts-*.xml
	private static final String FILE_LAST_PATTERN="classpath:/cn/future/config/struts/struts*.xml";
	private final Logger logger = LoggerFactory.getLogger(Struts2ConfigurationProvider.class);

	public Struts2ConfigurationProvider(){
		Map<String, String> mappings = new HashMap<String, String>();
		mappings.put("-//OpenSymphony Group//XWork 2.1.3//EN", "xwork-2.1.3.dtd");
		mappings.put("-//OpenSymphony Group//XWork 2.1//EN", "xwork-2.1.dtd");
		mappings.put("-//OpenSymphony Group//XWork 2.0//EN", "xwork-2.0.dtd");
		mappings.put("-//OpenSymphony Group//XWork 1.1.1//EN", "xwork-1.1.1.dtd");
		mappings.put("-//OpenSymphony Group//XWork 1.1//EN", "xwork-1.1.dtd");
		mappings.put("-//OpenSymphony Group//XWork 1.0//EN", "xwork-1.0.dtd");
		mappings.put("-//Apache Software Foundation//DTD Struts Configuration 2.0//EN", "struts-2.0.dtd");
		mappings.put("-//Apache Software Foundation//DTD Struts Configuration 2.1//EN", "struts-2.1.dtd");
		mappings.put("-//Apache Software Foundation//DTD Struts Configuration 2.1.7//EN", "struts-2.1.7.dtd");
		mappings.put("-//Apache Software Foundation//DTD Struts Configuration 2.3//EN", "struts-2.3.dtd");
		
		
		setDtdMappings(mappings);
	}

	/* (non-Javadoc)
	* @see com.opensymphony.xwork2.config.ContainerProvider#needsReload()
	*/
	@Override
	public boolean needsReload() {
	return true;
	}

	/* (non-Javadoc)
	* @see com.opensymphony.xwork2.config.ContainerProvider#register(com.opensymphony.xwork2.inject.ContainerBuilder, com.opensymphony.xwork2.util.location.LocatableProperties)
	*/
	@Override
	public void register(ContainerBuilder containerBuilder, LocatableProperties props) throws ConfigurationException {
	super.register(containerBuilder, props) ;
	}

	/* (non-Javadoc)
	* @see com.opensymphony.xwork2.config.PackageProvider#loadPackages()
	*/
	@Override
	public void loadPackages() throws ConfigurationException {
	super.loadPackages() ;
	}

	@Override
	protected Iterator<URL> getConfigurationUrls(String fileName) throws IOException {
		List<URL> urls = getAllResourcesUrl() ;
		return urls.iterator() ;
	}
	/**
	* 获取系统中需要搜寻的struts的配置
	* @return
	* @throws IOException 
	*/
	private List<URL> getAllResourcesUrl() {
		
		List<URL> list = new ArrayList<URL>();
		
		try {
			
			Resource[] firstRes = this.getPatternResource(FILE_FIRST_PATTERN);
			if(firstRes != null){
				logger.info("FutureFocus - first struts2 config:"+ firstRes.length);
				for(Resource r : firstRes){
					logger.debug("FutureFocus - first struts2 config:"+r.getURL().toString());
					list.add(r.getURL());
				}
			}
			
			Resource[] secondRes = getPatternResource(FILE_PATTERN) ;
			if(secondRes != null){
				logger.info("FutureFocus - second struts2 config:"+ secondRes.length);
				for(Resource r : secondRes){
					logger.debug("FutureFocus - second struts2 config:"+r.getURL().toString());
					list.add(r.getURL());
				}
			}
			
			Resource[] lastRes = getPatternResource(FILE_LAST_PATTERN) ;
			if(lastRes != null){
				logger.info("FutureFocus - last struts2 config:"+ lastRes.length);
				for(Resource r : lastRes){
					logger.debug("FutureFocus - last struts2 config:"+r.getURL().toString());
					list.add(r.getURL());
				}
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	public Resource[] getPatternResource(String pattern){
		ResourcePatternResolver resoler = new PathMatchingResourcePatternResolver() ;
		Resource[] res = null;
		try {
			res = resoler.getResources(pattern);
		}catch(FileNotFoundException e1){
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}
}
