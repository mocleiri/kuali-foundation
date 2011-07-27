package org.kuali.cm.checkstyle;

import java.util.List;

public class SourceFile {
    String name;
    List<String> violations;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getViolations() {
        return violations;
    }

    public void setViolations(List<String> violations) {
        this.violations = violations;
    }
}
