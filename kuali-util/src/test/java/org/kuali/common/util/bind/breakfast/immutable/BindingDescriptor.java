package org.kuali.common.util.bind.breakfast.immutable;

import java.lang.reflect.Field;

import org.apache.commons.lang3.builder.Builder;

import com.google.common.base.Optional;

public class BindingDescriptor<T extends Builder<T>> {

	Field field;
	Optional<T> instance;
	String bindKey;
	Object bindValue;
	String instancePropertyName;

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Optional<T> getInstance() {
		return instance;
	}

	public void setInstance(Optional<T> instance) {
		this.instance = instance;
	}

	public String getBindKey() {
		return bindKey;
	}

	public void setBindKey(String bindKey) {
		this.bindKey = bindKey;
	}

	public Object getBindValue() {
		return bindValue;
	}

	public void setBindValue(Object bindValue) {
		this.bindValue = bindValue;
	}

	public String getInstancePropertyName() {
		return instancePropertyName;
	}

	public void setInstancePropertyName(String instancePropertyName) {
		this.instancePropertyName = instancePropertyName;
	}

}
