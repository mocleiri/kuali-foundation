package org.kuali.common.util.sys;

import java.util.Properties;

public interface SystemService {

	Properties getProperties();

	Properties getGlobalProperties();

	Properties getEnvironment();

	void removeSystemProperty(String key);

}
