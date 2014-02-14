package org.kuali.common.util.bind.breakfast.immutable;

import java.lang.reflect.Field;
import java.util.List;

import org.apache.commons.lang3.builder.Builder;
import org.kuali.common.util.tree.Node;

public class BindDescriptor {

	public BindDescriptor(Node<Field> node) {
		this.node = node;
	}

	Node<Field> node;
	List<String> bindKeys;
	Object bindValue;
	String instancePropertyName;
	Object instance;
	Builder<?> instanceBuilder;

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

	public Builder<?> getInstanceBuilder() {
		return instanceBuilder;
	}

	public void setInstanceBuilder(Builder<?> instanceBuilder) {
		this.instanceBuilder = instanceBuilder;
	}

	public List<String> getBindKeys() {
		return bindKeys;
	}

	public void setBindKeys(List<String> bindKeys) {
		this.bindKeys = bindKeys;
	}

}
