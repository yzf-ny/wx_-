package com.liu.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XMLUtil {

	public static Map<String, String>xmlRead( ServletInputStream input){
	
		try {
			//读取微信服务器发送的xml格式字符串数据
			 SAXReader reader=new SAXReader();
			 //读取输出流的文档对象
			
			  Document document = reader.read(input);
			 //获得根节点对象
			 Element root = document.getRootElement();
			 List<Element> es = root.elements();
			 Map<String, String> map=new HashMap<>();
			 for(Element e:es) {
				 String NodeName=e.getName();//节点名称
				 String NodeValue=e.getText();//节点内容
				 map.put(NodeName, NodeValue);
			 }
			 return map;
		} catch (DocumentException e1) {
			
			e1.printStackTrace();
		}
		return null;
		
	}
}
