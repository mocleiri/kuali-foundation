package org.kuali.common.util.xml.jaxb;

import java.util.Collections;
import java.util.List;

import org.kuali.common.util.Assert;

public final class Student {

	private final String name;
	private final List<Class> classes;

	public String getName() {
		return name;
	}

	public List<Class> getClasses() {
		return classes;
	}

	public static class Builder {

		private final String name;
		private final List<Class> classes;

		public Builder(String name, List<Class> classes) {
			Assert.noBlanks(name);
			Assert.noNulls(classes);
			this.name = name;
			this.classes = Collections.unmodifiableList(classes);
		}
		
		public Student build() {
			return new Student(this);
		}
	}

	private Student(Builder builder) {
		this.name = builder.name;
		this.classes = builder.classes;
	}

}
