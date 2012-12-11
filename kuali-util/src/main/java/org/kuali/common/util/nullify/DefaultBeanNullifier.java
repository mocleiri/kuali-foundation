package org.kuali.common.util.nullify;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.common.util.Str;
import org.kuali.common.util.property.Constants;
import org.springframework.util.Assert;

public class DefaultBeanNullifier implements Nullify {

	Object bean;
	List<String> properties;
	List<String> nullTokens = Collections.singletonList(Constants.NULL);
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
