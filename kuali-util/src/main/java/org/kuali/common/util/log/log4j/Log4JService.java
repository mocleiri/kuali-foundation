package org.kuali.common.util.log.log4j;

import java.io.File;
import java.util.Properties;

import org.kuali.common.util.log.log4j.model.Log4JConfiguration;
import org.w3c.dom.Element;

public interface Log4JService {

	void reset();

	void configure(Log4JConfiguration context);

	String toXml(Log4JConfiguration context);

	void write(File file, Log4JConfiguration context);

	void configure(Properties properties);

	void configure(Element element);

	void configure(String location);

}
