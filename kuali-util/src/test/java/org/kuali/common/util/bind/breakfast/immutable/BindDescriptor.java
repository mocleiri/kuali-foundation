package org.kuali.common.util.bind.breakfast.immutable;

import java.lang.reflect.Field;

import org.apache.commons.lang3.builder.Builder;
import org.kuali.common.util.tree.Node;

public class BindDescriptor {

	Node<Field> node;
	Builder<?> builder;
	Object instance;
	String bindKey;
	Object bindValue;
	String instancePropertyName;

	public Builder<?> getBuilder() {
		return builder;
	}

	public void setBuilder(Builder<?> builder) {
		this.builder = builder;
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

	public Node<Field> getNode() {
		return node;
	}

	public void setNode(Node<Field> node) {
		this.node = node;
	}

	public Object getInstance() {
		return instance;
	}

	public void setInstance(Object instance) {
		this.instance = instance;
	}

}
