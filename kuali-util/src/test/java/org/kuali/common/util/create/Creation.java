package org.kuali.common.util.create;

import org.kuali.common.util.create.bootstrap.GenericBootstrap;

public class Creation {

	private static Creator instance;

	public synchronized static Creator getDefaultCreator() {
		if (instance == null) {
			CreatorFactory factory = buildDefaultCreatorFactory();
			instance = factory.getCreator();
		}
		return instance;
	}

	public static CreatorFactory buildDefaultCreatorFactory() {
		return byDefaultProvider().configure().buildCreatorFactory();
	}

	public static GenericBootstrap byDefaultProvider() {
		return null;
	}

}
