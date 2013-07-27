package org.kuali.common.util.spring.service;

import org.springframework.context.support.AbstractApplicationContext;

public class LoadContext {

	AbstractApplicationContext parent;
	AbstractApplicationContext child;

	public AbstractApplicationContext getParent() {
		return parent;
	}

	public void setParent(AbstractApplicationContext parent) {
		this.parent = parent;
	}

	public AbstractApplicationContext getChild() {
		return child;
	}

	public void setChild(AbstractApplicationContext child) {
		this.child = child;
	}

}
