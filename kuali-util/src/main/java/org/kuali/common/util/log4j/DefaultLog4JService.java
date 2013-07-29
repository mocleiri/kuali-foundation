package org.kuali.common.util.log4j;

import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.xml.DOMConfigurator;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.log4j.model.Log4JContext;
import org.kuali.common.util.log4j.model.Log4JDebug;
import org.kuali.common.util.log4j.model.Log4JLogger;
import org.kuali.common.util.log4j.model.Log4JParam;
import org.kuali.common.util.log4j.model.Log4JThreshold;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.sun.org.apache.xerces.internal.impl.xs.opti.DefaultDocument;

public class DefaultLog4JService implements Log4JService {

	public static final String RESET_KEY = "log4.reset";
	public static final String THRESHOLD_KEY = "log4.threshold";

	@Override
	public void reset() {
		LogManager.resetConfiguration();
	}

	@Override
	public void configure(Properties properties) {
		PropertyConfigurator.configure(properties);
	}

	@Override
	public void configure(Log4JContext context) {
		// Do something smart
	}

	@Override
	public void configure(Element element) {
		DOMConfigurator.configure(element);
	}

	protected Element getElement(Log4JContext context) {
		Document document = new DefaultDocument();
		return document.getDocumentElement();
	}

	protected Properties getProperties(Log4JContext context) {
		Properties properties = new Properties();
		if (Log4JDebug.TRUE.equals(context.getDebug())) {
			properties.setProperty(LogLog.DEBUG_KEY, "true");
		}
		if (context.isReset()) {
			properties.setProperty(RESET_KEY, "true");
		}
		if (!Log4JThreshold.NULL.equals(context.getThreshold())) {
			properties.setProperty(THRESHOLD_KEY, context.getThreshold().name());
		}
		return properties;
	}

	protected Log4JParam getRootLoggerProperty(Log4JContext context) {
		Log4JLogger root = context.getRoot();
		String name = "log4j.rootLogger";
		StringBuilder sb = new StringBuilder();
		sb.append(root.getLevel());
		int count = CollectionUtils.toEmptyList(root.getAppenders()).size();
		for (int i = 0; i < count; i++) {
			if (i != 0) {
				sb.append(", ");
			}
			String appender = root.getAppenders().get(i);
			sb.append(appender);
		}
		return new Log4JParam(name, sb.toString());
	}
}
