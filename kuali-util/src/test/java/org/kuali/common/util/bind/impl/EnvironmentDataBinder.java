package org.kuali.common.util.bind.impl;

import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.env.Environment;
import org.springframework.validation.DataBinder;

public class EnvironmentDataBinder extends DataBinder {

	public EnvironmentDataBinder(Object target, String objectName) {
		super(target, objectName);
	}

	public EnvironmentDataBinder(Object target) {
		super(target);
	}

	public void bind(Environment env) {
		MutablePropertyValues values = EnvironmentPropertyValues.builder(getTarget().getClass(), env).build();
		super.doBind(values);
	}

}
