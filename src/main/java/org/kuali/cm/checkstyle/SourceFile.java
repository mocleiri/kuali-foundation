package org.kuali.cm.checkstyle;

import java.util.Set;

public class SourceFile implements Comparable<SourceFile> {
	String name;
	Set<String> violations;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<String> getViolations() {
		return violations;
	}

	public void setViolations(Set<String> violations) {
		this.violations = violations;
	}

	@Override
	public int compareTo(SourceFile o) {
		return name.compareTo(o.getName());
	}

}
