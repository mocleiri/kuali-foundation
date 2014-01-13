package org.kuali.common.util.bind.impl;

import org.kohsuke.MetaInfServices;
import org.kuali.common.util.bind.api.BinderFactory;
import org.kuali.common.util.bind.api.BindingProvider;

@MetaInfServices
public class DefaultBindingProvider implements BindingProvider {

	@Override
	public BinderFactory buildBinderFactory() {
		return new DefaultBinderFactory();
	}

}
