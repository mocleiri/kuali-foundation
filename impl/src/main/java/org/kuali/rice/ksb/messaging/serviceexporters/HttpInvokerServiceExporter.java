/*
 * Copyright 2005-2007 The Kuali Foundation
 * 
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
package org.kuali.rice.ksb.messaging.serviceexporters;

import org.kuali.rice.ksb.messaging.KSBHttpInvokerServiceExporter;
import org.kuali.rice.ksb.messaging.ServerSideRemotedServiceHolder;
import org.kuali.rice.ksb.messaging.ServiceInfo;

/**
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class HttpInvokerServiceExporter implements ServiceExporter {

	private ServiceInfo serviceInfo;
	
	public HttpInvokerServiceExporter(ServiceInfo serviceInfo) {
		this.serviceInfo = serviceInfo;
	}
	
	public ServerSideRemotedServiceHolder getServiceExporter(Object serviceImpl) {
		KSBHttpInvokerServiceExporter service = new KSBHttpInvokerServiceExporter();
		service.setServiceInfo(getServiceInfo());
		service.setService(serviceImpl);
		service.afterPropertiesSet();
		return new ServerSideRemotedServiceHolder(service, getServiceInfo().getServiceDefinition().getService(), getServiceInfo());
	}

	public ServiceInfo getServiceInfo() {
		return this.serviceInfo;
	}
}
