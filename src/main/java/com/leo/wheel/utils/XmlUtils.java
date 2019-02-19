package com.leo.wheel.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * xml相关的工具类
 * XML的解析方式分为四种：1、DOM解析；2、SAX解析；3、JDOM解析；4、DOM4J解析。
 * 其中前两种属于基础方法，是官方提供的平台无关的解析方式；后两种属于扩展方法，它们是在基础的方法上扩展出来的，只适用于java平台。
 * DOM4J性能最好，连Sun的JAXM也在用DOM4J。目前许多开源项目中大量采用DOM4J，例如大名鼎鼎的Hibernate也用DOM4J来读取XML配置文件。如果不考虑可移植性，那就采用DOM4J。
 * JDOM和DOM在性能测试时表现不佳，在测试10M文档时内存溢出。在小文档情况下还值得考虑使用DOM和JDOM。
 * 其中DOM仍是一个非常好的选择。DOM实现广泛应用于多种编程语言。它还是许多其它与XML相关的标准的基础，因为它正式获得W3C推荐（与基于非标准的Java模型相对），所以在某些类型的项目中可能也需要它（如在JavaScript中使用DOM）。
 * SAX表现较好，这要依赖于它特定的解析方式－事件驱动。一个SAX检测即将到来的XML流，但并没有载入到内存（当然当XML流被读入时，会有部分文档暂时隐藏在内存中）。
 * 
 * @author leo
 *
 */
public class XmlUtils {

	private static final StringBuffer SSS = new StringBuffer("<select>select id from wheel WHERE 1=1  ")
			.append("<if test=\"id !== null\"> and wheel.id = #{id}</if>")
			.append("<if test=\"name !== null\"> and wheel.name = #{name}</if>").append("</select>");

	/**
	 * xml字符串转换为Dom4j的Document
	 * @param xml
	 * @return
	 * @throws DocumentException
	 */
	public static Document parseString(String xml) throws DocumentException {
		System.out.println(xml);
		return DocumentHelper.parseText(xml);
	}

	public static Document createDocument() {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("root");

		Element author1 = root.addElement("author").addAttribute("name", "James").addAttribute("location", "UK")
				.addText("James Strachan");

		Element author2 = root.addElement("author").addAttribute("name", "Bob").addAttribute("location", "US")
				.addText("Bob McWhirter");

		return document;
	}

	/**
	 * TODO ?
	 * @param document
	 */
	public static void treeWalk(Document document) {
		treeWalk(document.getRootElement());
	}

	public static void treeWalk(Element element) {
		for (int i = 0, size = element.nodeCount(); i < size; i++) {
			Node node = element.node(i);
			if (node instanceof Element) {
				treeWalk((Element) node);
			} else {
				// do something…
			}
		}
	}

	public static void findLinks(Document document) throws DocumentException {

		List<Node> list = document.selectNodes("//author");

		for (Iterator<Node> iter = list.iterator(); iter.hasNext();) {
			// Attribute attribute = (Attribute) iter.next();
			// String url = attribute.getValue();
			// System.out.println(url);
		}
	}

	public static void WriteDoc2File(Document document) throws IOException {
		// lets write to a file
		FileWriter fileWriter = new FileWriter("output.xml");
		XMLWriter writer = new XMLWriter(fileWriter);
		writer.write(document);
		writer.close();

		// Pretty print the document to System.out
		OutputFormat format = OutputFormat.createPrettyPrint();
		writer = new XMLWriter(System.out, format);
		writer.write(document);

		// Compact format to System.out
		format = OutputFormat.createCompactFormat();
		writer = new XMLWriter(System.out, format);
		writer.write(document);
		writer.close();

	}

	public static void main(String[] args) throws IOException, DocumentException {
		Document document = XmlUtils.parseString(XmlUtils.SSS.toString());
		List<Node> whereNodes = document.selectNodes("//select/if");
		for (Node node : whereNodes) {
			node.setText(node.getStringValue());
			// String text = node.getText();
			// System.out.println(text);
		}
		Element root = document.getRootElement();
		String text = root.getStringValue();
		System.out.println(text);
	}
}
