package org.kuali.common.devops.json.fruit;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.lang.reflect.Field;

import org.springframework.core.convert.TypeDescriptor;

import com.google.common.base.Optional;

public class FieldDescriptor extends TypeDescriptor {

	private static final long serialVersionUID = 1L;

	public FieldDescriptor(Field field) {
		super(field);
		this.field = checkNotNull(field, "field");
		if (isCollection()) {
			Class<?> type = getElementTypeDescriptor().getType();
			this.collectionType = Optional.<Class<?>> of(type);
		} else {
			this.collectionType = Optional.<Class<?>> absent();
		}
		if (isMap()) {
			Class<?> keyType = getMapKeyTypeDescriptor().getType();
			Class<?> valueType = getMapValueTypeDescriptor().getType();
			MapType mapType = new MapType(keyType, valueType);
			this.mapType = Optional.of(mapType);
		} else {
			this.mapType = Optional.<MapType> absent();
		}
	}

	private final Field field;
	private final Optional<Class<?>> collectionType;
	private final Optional<MapType> mapType;

	public Field getField() {
		return field;
	}

	public Optional<Class<?>> getCollectionType() {
		return collectionType;
	}

	public Optional<MapType> getMapType() {
		return mapType;
	}

}
