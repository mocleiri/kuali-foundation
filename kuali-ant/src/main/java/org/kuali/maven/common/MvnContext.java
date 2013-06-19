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

import java.io.File;
import java.util.List;
import java.util.Properties;

public interface MvnContext {

    public static final String MAVEN_OPTS = "MAVEN_OPTS";

    public abstract Properties getProjectProperties();

    public abstract File getWorkingDir();

    public abstract void setWorkingDir(File workingDir);

    public abstract File getBasedir();

    public abstract void setBasedir(File basedir);

    public abstract String getExecutable();

    public abstract void setExecutable(String executable);

    public abstract List<String> getPoms();

    public abstract void setPoms(List<String> poms);

    public abstract String getPom();

    public abstract void setPom(String pom);

    public abstract boolean isFilterPom();

    public abstract void setQuiet(boolean quiet);

    public abstract boolean isQuiet();

    public abstract void setSilent(boolean silent);

    public abstract boolean isSilent();

    public abstract void setFilterPom(boolean filterPom);

    public abstract List<String> getArgs();

    public abstract void setArgs(List<String> args);

    public abstract List<String> getProperties();

    public abstract void setProperties(List<String> properties);

    public abstract List<String> getFilterProperties();

    public abstract void setFilterProperties(List<String> filterProperties);

    public abstract boolean isAddEnvironment();

    public abstract void setAddEnvironment(boolean addEnvironment);

    public abstract boolean isAddMavenOpts();

    public abstract void setAddMavenOpts(boolean addMavenOpts);

    public abstract boolean isFailOnError();

    public abstract boolean isDeleteTempPom();

    public abstract void setFailOnError(boolean failOnError);

}