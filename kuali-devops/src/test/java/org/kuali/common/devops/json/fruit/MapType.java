package org.kuali.common.devops.json.fruit;

import static org.kuali.common.util.base.Precondition.checkNotNull;

public final class MapType {

	public MapType(Class<?> keyType, Class<?> valueType) {
		this.keyType = checkNotNull(keyType, "keyType");
		this.valueType = checkNotNull(valueType, "valueType");
	}

	private final Class<?> keyType;
	private final Class<?> valueType;

	public Class<?> getKeyType() {
		return keyType;
	}

	public Class<?> getValueType() {
		return valueType;
	}

}
