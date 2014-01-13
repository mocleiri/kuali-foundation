package org.kuali.common.util.bind.impl;

import org.kohsuke.MetaInfServices;
import org.kuali.common.util.bind.api.Binder;
import org.kuali.common.util.bind.api.BinderFactory;

@MetaInfServices
public class DefaultBinderFactory implements BinderFactory {

	@Override
	public Binder getBinder() {
		return new DefaultBinder();
	}

}
