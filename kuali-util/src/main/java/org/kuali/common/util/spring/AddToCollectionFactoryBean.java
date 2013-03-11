/**
 * Copyright 2010-2012 The Kuali Foundation
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
package org.kuali.common.util.spring;

import java.util.Collection;

import org.kuali.common.util.Assert;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

public class AddToCollectionFactoryBean<T> implements FactoryBean<Collection<T>>, InitializingBean {

	Collection<T> source;
	Collection<T> destination;

	public Collection<T> getSource() {
		return source;
	}

	public void setSource(Collection<T> source) {
		this.source = source;
	}

	public Collection<T> getDestination() {
		return destination;
	}

	public void setDestination(Collection<T> destination) {
		this.destination = destination;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(source, "source is null");
		Assert.notNull(destination, "destination is null");
		destination.addAll(source);
	}

	@Override
	public Collection<T> getObject() throws Exception {
		return destination;
	}

	@Override
	public Class<?> getObjectType() {
		return null;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

}
