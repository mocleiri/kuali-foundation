package org.kuali.common.util.property;

import java.util.Properties;

public interface StringResolver {

	String resolve(String location, Properties properties);
}
