package org.kuali.common.util.log4j;

import java.util.Properties;

import org.kuali.common.util.log4j.model.Log4JContext;
import org.w3c.dom.Element;

public interface Log4JService {

	void reset();

	void configure(Log4JContext context);

	void configure(Properties properties);

	void configure(Element element);

	void configure(String location);

}
