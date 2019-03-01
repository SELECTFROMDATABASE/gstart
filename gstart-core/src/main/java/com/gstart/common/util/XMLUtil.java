package com.gstart.common.util;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  XML Util
 * @author Jerry
 * 
 */

public class XMLUtil {
	private static Logger log = LoggerFactory.getLogger(XMLUtil.class);
	/**
	 *  parseXML by XMLTextStream
	 * @param in XMLTextStream
	 * @return ����map
	 * @throws IOException
	 */

	public static Map<Object, Object> parseXML(InputStream in)
			 {
		if (isEmpty(in) == true) {
			log.debug("Method : parseXML -> InputStream is Empty");
			return null;
		}

		SAXReader sreader = new SAXReader();
		Document document;
		try {
			document = sreader.read(in);
			Element root = document.getRootElement();
			List<Element> elist = root.elements();
			Map<Object, Object> map = new HashMap<Object, Object>();
			getNode(elist, map);
			return map;
		} catch (DocumentException e1) {
			e1.printStackTrace();
			return null;
		}

	}

	/**
	 * getNode from parent then put into map
	 * @param elist
	 *            �ݹ�Ľڵ�
	 * @param map
	 *            ��Žڵ���Ϣ��map
	 */
	private static void getNode(List<Element> elist, Map map) {
		if (isEmpty(elist, map) == true) {
			log.debug("Method : parseXML -> elist or map is Empty");
		}
		for (Element e : elist) {
			if (e.isTextOnly()) {
				map.put(e.getName(), e.getText());
			} else {
				getNode(e.elements(), map);
			}
		}
	}
	/**
	 *
	 *  Object to XML
	 * 
	 * @param o
	 *            �������ɶ����xml
	 * @param map
	 *            �ı�ڵ������
	 */
	public static String parseObject2XML(Object o, Map<String, Class> map) {
		if(isEmpty(o, map)==true){
			log.debug("Method : parseXML -> Object or Map is Empty");
			return null;
		}

		XStream s = new XStream(new DomDriver());
		for(Map.Entry<String, Class> e:map.entrySet()){
			s.alias(e.getKey(), e.getValue());
		}
		return s.toXML(o);
	}

	private static boolean isEmpty(InputStream in) {
		if (null == in)
			return true;
		else
			return false;

	}

	private static boolean isEmpty(List elist, Map map) {
		if (null == elist || elist.size() == 0 || null == map)
			return true;
		else
			return false;
	}
	private static boolean isEmpty(Object o, Map map) {
		if (null==o||null==map)
			return true;
		else
			return false;
	}
}
