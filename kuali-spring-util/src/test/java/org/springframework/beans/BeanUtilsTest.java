package org.springframework.beans;

import junit.framework.Assert;

import org.junit.Test;

public class BeanUtilsTest {

    public class A {
        String foo;
        String bar;

        public String getFoo() {
            return foo;
        }

        public void setFoo(String foo) {
            this.foo = foo;
        }

        public String getBar() {
            return bar;
        }

        public void setBar(String bar) {
            this.bar = bar;
        }
    }

    public class B {
        String foo;
        String bar;

        public String getFoo() {
            return foo;
        }

        public void setFoo(String foo) {
            this.foo = foo;
        }

        public String getBar() {
            return bar;
        }

        public void setBar(String bar) {
            this.bar = bar;
        }
    }

    @Test
    public void copyProperties() {
        A a = new A();
        a.setFoo("foo");
        a.setBar("bar");

        B b = new B();

        BeanUtils.copyProperties(a, b);
        Assert.assertEquals(b.getFoo(), a.getFoo());
        Assert.assertEquals(b.getBar(), b.getBar());

        a.setFoo(null);
        b.setFoo("foo-times-2");

        BeanUtils.copyProperties(a, b);
        System.out.println(b.getFoo());

    }

}
