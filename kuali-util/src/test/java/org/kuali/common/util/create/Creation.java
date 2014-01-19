package org.kuali.common.util.create;

import org.kuali.common.util.create.bootstrap.GenericBootstrap;

public class Creation {

	public static CreatorFactory buildDefaultCreatorFactory() {
		return byDefaultProvider().configure().buildCreatorFactory();
	}

	public static GenericBootstrap byDefaultProvider() {
		return null;
	}

}
