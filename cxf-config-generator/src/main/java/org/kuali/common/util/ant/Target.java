package org.kuali.common.util.ant;

import java.util.List;

public class Target {
    String content;
    String name;
    List<Target> depends;
    List<String> dependsNames;
    int depth;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Target> getDepends() {
        return depends;
    }

    public void setDepends(List<Target> depends) {
        this.depends = depends;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getDependsNames() {
        return dependsNames;
    }

    public void setDependsNames(List<String> dependsNames) {
        this.dependsNames = dependsNames;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

}
