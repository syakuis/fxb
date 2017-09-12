package org.fxb.context.support.ehcache;

import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.ConfigurationFactory;
import org.fxb.context.cache.bean.factory.EhcacheFactoryBean;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 13.
 */
public class CreateXmlConfiguration {
	private final Logger logger = LoggerFactory.getLogger(CreateXmlConfiguration.class);

	private final String TEMPLATE_FILE = "ehcache.xml";
	private final String CACHE_FILE = "cache.xml";

	// todo 템플릿 파일을 읽는 다.
//	@Test
	public Document getTemplateLoader() throws Exception {
		ClassPathResource resource = new ClassPathResource(TEMPLATE_FILE);
		if (resource.exists()) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			return dbf.newDocumentBuilder().parse(resource.getFile());

//			return document.getDocumentElement();

//			Node lastNode = rootElement.getLastChild();
//
//			Element element = document.createElement("cache");
//			rootElement.appendChild(element);
//
//			NodeList list = rootElement.getChildNodes();
//			int length = list.getLength();
//
//			for (int i = 0; i < length; i++) {
//				Node node = list.item(i);
//				logger.debug(node.getNodeName());
//			}
		}

		return null;
	}

	// todo 대상 경로의 cache.xml 파일을 로드한다.
//	@Test
	public List<Node> getCacheLoader() throws Exception {
		List<Node> result = new ArrayList<>();
		ClassPathResource resource = new ClassPathResource(CACHE_FILE);
		if (resource.exists()) {
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			Document document = dbf.newDocumentBuilder().parse(resource.getFile());

			Element rootElement = document.getDocumentElement();

			NodeList list = rootElement.getChildNodes();
			int length = list.getLength();

			for (int i = 0; i < length; i++) {
				Node node = list.item(i);
				if ("cache".equals(node.getNodeName())) {
					result.add(node);
				}
			}
		}

		return result;
	}

	// todo 템플릿에 cache.xml 데이터를 삽입한다.
//	@Test
	public InputStream getInputStream() throws Exception {
		Document document = this.getTemplateLoader();
		Element rootElement = document.getDocumentElement();

		List<Node> nodes = this.getCacheLoader();

		for (Node node : nodes) {
			Node newNode = document.importNode(node, true);
			rootElement.appendChild(newNode);
		}

		NodeList list = rootElement.getChildNodes();
		int length = list.getLength();

		for (int i = 0; i < length; i++) {
			Node node = list.item(i);
			logger.debug(node.getNodeName());
		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);

		StringWriter xmlAsWriter = new StringWriter();
		StreamResult result = new StreamResult(xmlAsWriter);

		TransformerFactory.newInstance().newTransformer().transform(source, result);

		return new ByteArrayInputStream(xmlAsWriter.toString().getBytes("UTF-8"));

	}

	// todo CacheManager 생성하기
	@Test
	public void test() throws Exception {
		InputStream inputStream = this.getInputStream();
		Configuration configuration = ConfigurationFactory.parseConfiguration(this.getInputStream());

		EhcacheFactoryBean factoryBean = new EhcacheFactoryBean();
		factoryBean.setConfiguration(configuration);

		factoryBean.afterPropertiesSet();
	}
}
