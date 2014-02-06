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
package org.kuali.common.util.nullify;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.common.util.Str;
import org.springframework.util.Assert;

public class BeanNullifier implements Nullifier {

	Object bean;
	List<String> properties;
	List<String> nullTokens = Arrays.asList(NullUtils.NULL);
	boolean caseSensitive = false;

	@Override
	public void nullify() {
		Assert.notNull(bean, "bean cannot be null");
		Assert.notNull(properties, "properties cannot be null");
		Assert.notNull(nullTokens, "nullTokens cannot be null");

		for (String property : properties) {
			Object value = getProperty(bean, property);
			if (isNullify(value, nullTokens, caseSensitive)) {
				setProperty(bean, property, null);
			}
		}
	}

	protected boolean isNullify(Object value, List<String> nullTokens, boolean caseSensitive) {
		if (value == null) {
			return false;
		} else {
			return Str.contains(nullTokens, value.toString(), caseSensitive);
		}
	}

	protected void setProperty(Object bean, String property, Object value) {
		try {
			PropertyUtils.setProperty(bean, property, value);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}

	protected Object getProperty(Object bean, String property) {
		try {
			return PropertyUtils.getProperty(bean, property);
		} catch (NoSuchMethodException e) {
			throw new IllegalArgumentException(e);
		} catch (InvocationTargetException e) {
			throw new IllegalArgumentException(e);
		} catch (IllegalAccessException e) {
			throw new IllegalArgumentException(e);
		}
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	public List<String> getProperties() {
		return properties;
	}

	public void setProperties(List<String> properties) {
		this.properties = properties;
	}

	public List<String> getNullTokens() {
		return nullTokens;
	}

	public void setNullTokens(List<String> nullTokens) {
		this.nullTokens = nullTokens;
	}

	public boolean isCaseSensitive() {
		return caseSensitive;
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

}
