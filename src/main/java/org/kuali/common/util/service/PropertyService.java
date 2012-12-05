package org.kuali.common.util.service;

import java.util.Properties;

import org.kuali.common.util.property.PropertyLoadContext;
import org.kuali.common.util.property.PropertyStoreContext;

public interface PropertyService {

	Properties load(PropertyLoadContext context);

	void store(PropertyStoreContext context, Properties properties);

}
