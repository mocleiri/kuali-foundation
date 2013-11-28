package org.kuali.common.util.builder;

public class FooBar {

	protected int foo;
	protected String[] bar;

	public FooBar(int foo, String[] bar) {
		this.foo = foo;
		this.bar = bar;
	}

	public int getFoo() {
		return foo;
	}

	public String[] getBar() {
		return bar;
	}
}