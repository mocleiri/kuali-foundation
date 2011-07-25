/*
 * Copyright 2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.krms.impl.repository;

import org.apache.log4j.Logger;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;

/**
 * This class keeps track of the KRMS Repository Services
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public final class KrmsRepositoryServiceLocator {

	private static final Logger LOG = Logger.getLogger(KrmsRepositoryServiceLocator.class);

    public static final String KRMS_ATTRIBUTE_DEFINITION_SERVICE = "krmsAttributeDefinitionService";
	
	private static KrmsAttributeDefinitionService krmsAttributeDefinitionService;
	
	public static <T extends Object> T getService(String serviceName) {
		return KrmsRepositoryServiceLocator.<T>getBean(serviceName);
	}

	public static <T extends Object> T getBean(String serviceName) {
		if ( LOG.isDebugEnabled() ) {
			LOG.debug("Fetching service " + serviceName);
		}
		return GlobalResourceLoader.<T>getService(serviceName);
	}

    public static KrmsAttributeDefinitionService getKrmsAttributeDefinitionService() {
		if ( krmsAttributeDefinitionService == null ) {
			krmsAttributeDefinitionService = getService(KRMS_ATTRIBUTE_DEFINITION_SERVICE);
		}
		return krmsAttributeDefinitionService;
    }

	public static void setKrmsAttributeDefinitionService(final KrmsAttributeDefinitionService service) {
		krmsAttributeDefinitionService = service;
	}

}
