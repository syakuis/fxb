package org.fxb.context.cache.bean.factory.support;

import net.sf.ehcache.config.Configuration;
import net.sf.ehcache.config.ConfigurationFactory;
import org.fxb.commons.io.MultiplePathMatchingResourcePatternResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.Assert;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Seok Kyun. Choi. 최석균 (Syaku)
 * @site http://syaku.tistory.com
 * @since 2017. 9. 13.
 */
public class EhcacheConfigurationLoader {
	private final Logger logger = LoggerFactory.getLogger(EhcacheConfigurationLoader.class);
	private PathMatchingResourcePatternResolver pathMatching = new PathMatchingResourcePatternResolver();
	private MultiplePathMatchingResourcePatternResolver multiplePathMatching
			= new MultiplePathMatchingResourcePatternResolver();

	private final String ehcacheLocation;
	private final String[] cacheLocation;
	private String charset = "UTF-8";

	public EhcacheConfigurationLoader(String ehcacheLocation, String[] cacheLocation) {
		this.ehcacheLocation = ehcacheLocation;
		this.cacheLocation = cacheLocation;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	private Document getEhcacheXmlLoader() throws EhcacheConfigurationException {
		try {
			Resource resource = pathMatching.getResource(ehcacheLocation);
			Assert.isTrue(resource.exists(), "ehcache xml not found.");
			logger.debug("><>< Ehcache Loader: {}", resource.getURI());
			return DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(resource.getURI().toString());
		} catch (ParserConfigurationException e) {
			throw new EhcacheConfigurationException(e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new EhcacheConfigurationException(e);
		} catch (SAXException e) {
			throw new EhcacheConfigurationException(e);
		}
	}

	private List<Node> getCacheXmlLoader() throws EhcacheConfigurationException {
		try {
			List<Node> result = new ArrayList<>();
			Resource[] resources = multiplePathMatching.getResources(cacheLocation);

			for (Resource resource : resources) {
				if (resource.exists()) {
					logger.debug("><>< Ehcache Loader: {}", resource.getURI());
					DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
					Document document = dbf.newDocumentBuilder().parse(resource.getURI().toString());

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
			}

			return result;
		} catch (ParserConfigurationException e) {
			logger.error(e.getMessage(), e);
			throw new EhcacheConfigurationException(e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			throw new EhcacheConfigurationException(e);
		} catch (SAXException e) {
			logger.error(e.getMessage(), e);
			throw new EhcacheConfigurationException(e);
		}
	}

	public Configuration create() throws EhcacheConfigurationException {
		logger.info("><>< Ehcache Setting Create XML");
		try {
			Document document = this.getEhcacheXmlLoader();
			Element rootElement = document.getDocumentElement();

			List<Node> nodes = this.getCacheXmlLoader();

			for (Node node : nodes) {
				Node newNode = document.importNode(node, true);
				rootElement.appendChild(newNode);
			}

			DOMSource source = new DOMSource(document);

			StringWriter xmlAsWriter = new StringWriter();
			StreamResult result = new StreamResult(xmlAsWriter);

			TransformerFactory.newInstance().newTransformer().transform(source, result);

			InputStream inputStream = new ByteArrayInputStream(xmlAsWriter.toString().getBytes(charset));

			return ConfigurationFactory.parseConfiguration(inputStream);
		} catch (TransformerException e) {
			logger.error(e.getMessage(), e);
			throw new EhcacheConfigurationException(e);
		} catch (UnsupportedEncodingException e) {
			logger.error(e.getMessage(), e);
			throw new EhcacheConfigurationException(e);
		}
	}
}
