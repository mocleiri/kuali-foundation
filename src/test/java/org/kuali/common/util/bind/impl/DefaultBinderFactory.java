package org.kuali.common.util.bind.impl;

import org.kuali.common.util.bind.api.Binder;
import org.kuali.common.util.bind.api.BinderFactory;

public class DefaultBinderFactory implements BinderFactory {

	@Override
	public Binder getBinder() {
		return new DefaultBinder();
	}

}
