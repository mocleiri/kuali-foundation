/**
 * Copyright 2004-2012 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
