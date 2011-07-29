package org.kuali.cm.checkstyle;

import java.util.Set;

public class SourceFile {
	String name;
	Set<Violation> violations;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Violation> getViolations() {
		return violations;
	}

	public void setViolations(Set<Violation> violations) {
		this.violations = violations;
	}

}
