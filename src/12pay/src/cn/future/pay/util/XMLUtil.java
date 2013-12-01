package cn.future.pay.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class XMLUtil {

	/**
	 * 解析xml,返回第一级元素键值对。如果第一级元素有子节点，则此节点的值是子节点的xml数据。
	 * @param strxml
	 * @return
	 * @throws JDOMException
	 * @throws IOException
	 */
	public static Map doXMLParse(String strxml) throws Exception {
		Document doc = DocumentHelper.parseText(strxml);
		Element root = doc.getRootElement();
		Iterator<Element> itr = root.elementIterator();
		HashMap<String,String> map = new HashMap<String,String>();
		while(itr.hasNext()){
			Element t = itr.next();
			map.put(t.getName(),t.getText());
		}
		return map;
	}
	
	
	
}
