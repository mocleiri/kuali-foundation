/*
 * Copyright 2007 The Kuali Foundation
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
package org.kuali.rice.ksb.messaging;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.kuali.rice.core.util.ClassLoaderUtils;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.remoting.httpinvoker.HttpInvokerProxyFactoryBean;


public class KSBHttpInvokerProxyFactoryBean extends HttpInvokerProxyFactoryBean {
	private static final Logger LOG = Logger.getLogger(KSBHttpInvokerProxyFactoryBean.class);

	private Object serviceProxy;

	private ServiceInfo serviceInfo;

	public ServiceInfo getServiceInfo() {
		return this.serviceInfo;
	}

	public void setServiceInfo(ServiceInfo serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	@Override
	public void afterPropertiesSet() {
		ProxyFactory proxyFactory = new ProxyFactory(getServiceInterfaces());
		proxyFactory.addAdvice(this);
		LOG.debug("Http proxying service " + this.serviceInfo);
		this.serviceProxy = proxyFactory.getProxy();
	}

	@Override
	public Object getObject() {
		return this.serviceProxy;
	}

	@Override
	public Class getObjectType() {
		return getObject().getClass();
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public Class[] getServiceInterfaces() {
		List<Class<?>> serviceInterfaces = new ArrayList<Class<?>>();
		JavaServiceDefinition javaServiceDefinition = (JavaServiceDefinition) this.serviceInfo.getServiceDefinition();
		try {
			for (String interfaceName : javaServiceDefinition.getServiceInterfaces()) {
				Class<?> clazz = Class.forName(interfaceName, true, ClassLoaderUtils.getDefaultClassLoader());
				LOG.debug("Adding service interface '" + clazz + "' to proxy object for service " + this.serviceInfo);
				serviceInterfaces.add(clazz);
			}
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
		return serviceInterfaces.toArray(new Class[0]);
	}
}
