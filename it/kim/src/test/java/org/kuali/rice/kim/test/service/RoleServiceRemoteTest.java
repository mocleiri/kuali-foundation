/*
 * Copyright 2006-2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.rice.kim.test.service;

import static org.junit.Assert.fail;

import java.util.List;

import javax.xml.namespace.QName;

import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.ksb.api.KsbApiServiceLocator;
import org.kuali.rice.ksb.api.bus.Endpoint;
import org.kuali.rice.ksb.api.bus.ServiceBus;

/**
 * Test the RoleService via remote calls
 * 
 * @author Kuali Rice Team (kuali-rice@googlegroups.com)
 *
 */
public class RoleServiceRemoteTest extends RoleServiceTest {

	public void setUp() throws Exception {
		super.setUp();
	}

	//@Override
	//protected Lifecycle getLoadApplicationLifecycle() {
	//	return new BaseLifecycle() {
	//		public void start() throws Exception {
	//			new JettyServerLifecycle(getConfigIntProp("kim.test.port"), "/" + getConfigProp("app.context.name"), "/../kim/src/test/webapp").start();
	//			super.start();
	//		}
	//	};	
	//}
	
	private int getConfigIntProp(String intPropKey) {
		return Integer.parseInt(getConfigProp(intPropKey));
	}

	private String getConfigProp(String propKey) {
		return ConfigContext.getCurrentContextConfig().getProperty(propKey);
	}
	
	/**
	 * This method tries to get a client proxy for the specified KIM service
	 * 
	 * @param  svcName - name of the KIM service desired
	 * @return the proxy object
	 * @throws Exception 
	 */
	protected Object getKimService(String svcName) throws Exception {
		ServiceBus serviceBus = KsbApiServiceLocator.getServiceBus();
		List<Endpoint> endpoints = serviceBus.getRemoteEndpoints(new QName("KIM", svcName));
		if (endpoints.size() > 1) {
			fail("Found more than one RemotedServiceHolder for " + svcName);
		}
		Endpoint endpoint = endpoints.get(0);
		return endpoint.getService();
	}
}
