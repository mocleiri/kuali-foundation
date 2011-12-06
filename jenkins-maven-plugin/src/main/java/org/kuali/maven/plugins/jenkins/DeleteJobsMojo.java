/**
 * Copyright 2004-2011 The Kuali Foundation
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
package org.kuali.maven.plugins.jenkins;

import java.util.ArrayList;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.kuali.maven.common.PropertiesUtils;
import org.kuali.maven.plugins.jenkins.context.MojoContext;

/**
 * Connect to a Jenkins server and delete one or more jobs
 *
 * @goal deletejobs
 * @requiresDependencyResolution test
 */
public class DeleteJobsMojo extends BaseMojo {

    /**
     * The command issued to Jenkins CLI
     *
     * @parameter expression="${jenkins.cmd}" default-value="delete-job"
     * @required
     */
    private String cmd;

    /**
     * The location of the jenkins job config template
     *
     * @parameter expression="${jenkins.template}" default-value="classpath:org/kuali/jenkins/jobs/template.xml"
     * @required
     */
    private String template;

    /**
     * Comma delimited list of types to delete. Maven GAV info is combined with 'type' to derive the complete job name
     * eg 'jenkins-maven-plugin-1.0-publish'
     *
     * @parameter expression="${jenkins.types}" default-value="publish,unit,license,release"
     * @required
     */
    private String types;

    /**
     * The explicit list of jobs to delete. If names are provided, 'types' is ignored.
     *
     * @parameter
     */
    private List<String> names;

    /**
     * Comma delimited list of result codes to ignore. Result code of 1 means the job we are trying to delete does not
     * exist.
     *
     * @parameter expression="${jenkins.ignoreCodes}" default-value="1"
     * @required
     */
    private String ignoreCodes;

    @Override
    public void execute() throws MojoExecutionException {
        String[] tokens = PropertiesUtils.splitAndTrim(types, ",");
        List<MojoContext> contexts = helper.deleteJobs(this, names, tokens);
        List<Integer> ignoreCodeList = getIgnoreCodeList();
        helper.handleResults(contexts, ignoreCodeList);
    }

    protected List<Integer> getIgnoreCodeList() {
        List<Integer> ignoreCodeList = new ArrayList<Integer>();
        String[] tokens = PropertiesUtils.splitAndTrim(ignoreCodes, ",");
        for (String token : tokens) {
            ignoreCodeList.add(new Integer(token));
        }
        return ignoreCodeList;
    }

    @Override
    public String getTemplate() {
        return template;
    }

    @Override
    public void setTemplate(String template) {
        this.template = template;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getIgnoreCodes() {
        return ignoreCodes;
    }

    public void setIgnoreCodes(String ignoreCodes) {
        this.ignoreCodes = ignoreCodes;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

}