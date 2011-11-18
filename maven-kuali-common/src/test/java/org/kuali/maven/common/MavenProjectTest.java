package org.kuali.maven.common;

import java.util.List;

import org.apache.maven.model.Model;
import org.apache.maven.project.MavenProject;

public class MavenProjectTest extends MavenProject {
    List<String> modules;
    List<MavenProjectTest> children;

    public MavenProjectTest() {
        super();
    }

    public MavenProjectTest(Model model) {
        super(model);
    }

    @Override
    public List<String> getModules() {
        return modules;
    }

    public void setModules(List<String> modules) {
        this.modules = modules;
    }

    public List<MavenProjectTest> getChildren() {
        return children;
    }

    public void setChildren(List<MavenProjectTest> children) {
        this.children = children;
    }

}
