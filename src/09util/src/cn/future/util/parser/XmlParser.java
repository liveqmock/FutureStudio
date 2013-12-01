package cn.future.util.parser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;


public class XmlParser {
	public static Map<String,String> parseXml(String xml) throws DocumentException{
		Map<String, String> map = new HashMap<String, String>();  

	    Document document = DocumentHelper.parseText(xml);
	    // 得到xml根元素  
	    Element root = document.getRootElement();  
	    // 得到根元素的所有子节点  
	    @SuppressWarnings("unchecked")
		List<Element> elementList = root.elements();  
	  
	    // 遍历所有子节点  
	    for (Element e : elementList){
	    	 map.put(e.getName(), e.getText());  
	    }
	       

	    return map;  
	}
}
