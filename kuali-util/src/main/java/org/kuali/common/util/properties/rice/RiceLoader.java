package org.kuali.common.util.properties.rice;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.apache.commons.io.IOUtils;
import org.kuali.common.util.LocationUtils;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class RiceLoader {

	public static Properties load(File file) {
		checkNotNull(file, "'file' cannot be null");
		return load(file.getAbsolutePath());
	}

	public static Properties load(String location) {
		checkNotNull(location, "'location' cannot be null");
		Config config = getConfig(location);
		return convert(config);
	}

	protected static Properties convert(Config config) {
		checkNotNull(config, "'config' cannot be null");
		checkNotNull(config.getParams(), "'params' cannot be null");
		Properties properties = new Properties();
		for (Param param : config.getParams()) {
			String key = param.getName();
			String val = param.getValue();
			properties.setProperty(key, val);
		}
		return properties;
	}

	protected static Config getConfig(String location) {
		InputStream in = null;
		try {
			in = LocationUtils.getInputStream(location);
			return unmarshal(Config.class, in);
		} catch (IOException e) {
			throw new IllegalStateException(String.format("unexpected io error -> [%s]", location));
		} finally {
			IOUtils.closeQuietly(in);
		}
	}

	@SuppressWarnings("unchecked")
	protected static <T> T unmarshal(Class<T> type, InputStream in) throws IOException {
		try {
			JAXBContext context = JAXBContext.newInstance(type);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			UnmarshallerHandler unmarshallerHandler = unmarshaller.getUnmarshallerHandler();
			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			xr.setContentHandler(unmarshallerHandler);
			InputSource xmlSource = new InputSource(in);
			xr.parse(xmlSource);
			return (T) unmarshallerHandler.getResult();
		} catch (SAXException e) {
			throw new IllegalStateException("Unexpected SAX error", e);
		} catch (ParserConfigurationException e) {
			throw new IllegalStateException("Unexpected parser configuration error", e);
		} catch (JAXBException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
		}
	}

}
