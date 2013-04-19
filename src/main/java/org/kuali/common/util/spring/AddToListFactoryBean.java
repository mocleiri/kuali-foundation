/**
 * Copyright 2010-2013 The Kuali Foundation
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
import java.util.List;

import org.kuali.common.util.Assert;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Append all of the elements from <code>source</code> to the end of <code>target</code>
 */
public class AddToListFactoryBean<T> implements FactoryBean<List<T>>, InitializingBean {

	List<T> source;
	List<T> target;

	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(source, "source is null");
		Assert.notNull(target, "target is null");
		target.addAll(source);
	}

	@Override
	public List<T> getObject() throws Exception {
		return target;
	}

	@Override
	public Class<?> getObjectType() {
		return List.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	public Collection<T> getSource() {
		return source;
	}

	public void setSource(List<T> source) {
		this.source = source;
	}

	public Collection<T> getTarget() {
		return target;
	}

	public void setTarget(List<T> target) {
		this.target = target;
	}

}
