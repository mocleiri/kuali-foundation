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

import java.util.Map;

import org.kuali.common.util.Assert;
import org.springframework.beans.factory.InitializingBean;

public class AddToMapInitializingBean<K, V> implements InitializingBean {

	Map<K, V> source;
	Map<K, V> destination;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(source, "source is null");
		Assert.notNull(destination, "destination is null");
		destination.putAll(source);
	}

	public Map<K, V> getSource() {
		return source;
	}

	public void setSource(Map<K, V> source) {
		this.source = source;
	}

	public Map<K, V> getDestination() {
		return destination;
	}

	public void setDestination(Map<K, V> destination) {
		this.destination = destination;
	}

}
