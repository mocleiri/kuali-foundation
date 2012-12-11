package org.kuali.common.util.nullify;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.kuali.common.util.property.Constants;
import org.springframework.util.Assert;

public class DefaultBeanNullifier implements Nullify {

	Object bean;
	List<String> properties;
	List<String> nullTokens = Arrays.asList(Constants.NULL);

	@Override
	public void nullify() {
		Assert.notNull(bean, "bean cannot be null");
		Assert.notNull(properties, "properties cannot be null");
		Assert.notNull(nullTokens, "nullTokens cannot be null");

		for (String property : properties) {
			Object value = getProperty(bean, property);
			if (isNullify(value, nullTokens)) {
				setProperty(bean, property, null);
			}
		}
	}

	protected boolean isNullify(Object value, List<String> nullTokens) {
		if (value == null) {
			return false;
		} else {
			return nullTokens.contains(value.toString());
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

}
