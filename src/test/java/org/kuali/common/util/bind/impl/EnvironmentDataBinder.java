package org.kuali.common.util.bind.impl;

import org.kuali.common.util.bind.api.BindingPrefix;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.core.env.Environment;
import org.springframework.validation.DataBinder;

public class EnvironmentDataBinder extends DataBinder {

	private final BindingPrefix bind;

	public EnvironmentDataBinder(Object target, BindingPrefix bind) {
		super(target);
		this.bind = bind;
	}

	public void bind(Environment env) {
		MutablePropertyValues values = EnvironmentPropertyValues.builder(getTarget().getClass(), bind, env).build();
		super.doBind(values);
	}

}
