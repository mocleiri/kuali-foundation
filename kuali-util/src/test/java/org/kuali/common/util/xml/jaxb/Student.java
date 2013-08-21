package org.kuali.common.util.xml.jaxb;

import java.util.List;

import org.kuali.common.util.Assert;

public final class Student {

	public Student(String name, List<Class> classes) {
		Assert.noBlanks(name);
		Assert.noNulls(classes);
		this.name = name;
		this.classes = classes;
	}

	private final String name;
	private final List<Class> classes;

	public String getName() {
		return name;
	}

	public List<Class> getClasses() {
		return classes;
	}

}
