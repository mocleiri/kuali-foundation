package org.kuali.common.util.builder;

import static org.junit.Assert.assertEquals;

public class BuilderTest {

	public void testDefaultBuilder_expectedDefaults() {
		assertEquals(0, FooBar.DefaultBuilder.FOO_DEFAULT);
		assertEquals(1, FooBar.DefaultBuilder.BAR_DEFAULT.size());
		assertEquals("Hello, world!", FooBar.DefaultBuilder.BAR_DEFAULT.get(0));
	}

	public void testDefaultBuilder_defaultFooBar() {
		FooBar foobar = FooBar.builder().build();
		assertEquals(FooBar.DefaultBuilder.FOO_DEFAULT, foobar.getFoo());
		assertEquals(FooBar.DefaultBuilder.BAR_DEFAULT, foobar.getBar());
	}

	public void testIsValid_validFoo() {
		assertIsValid_foo(true /* valid test values for foo */);
	}

	public void testIsValid_invalidFoo() {
		assertIsValid_foo(false /* invalid test values for foo */);
	}

	private void assertIsValid_foo(boolean valid, int... foos) {
		FooBar.Builder<?> builder = FooBar.builder();
		for (int foo : foos) {
			assertEquals(valid, builder.foo(foo).isValid());
		}
	}
}
