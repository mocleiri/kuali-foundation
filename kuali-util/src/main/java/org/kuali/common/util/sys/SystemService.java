package org.kuali.common.util.sys;

import java.util.Properties;

public interface SystemService {

	Properties getProperties();

	Properties getGlobalProperties();

	Properties getEnvironment();

	String getGlobalProperty(String key);

	String getGlobalProperty(String key, String defaultValue);

	void removeSystemProperty(String key);

}
