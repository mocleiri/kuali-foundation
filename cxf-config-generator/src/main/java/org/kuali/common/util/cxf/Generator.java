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
package org.kuali.common.util.cxf;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;

public class Generator {

    public static void main(String[] args) {
        new Generator().execute(args);
    }

    public void execute(String[] args) {
        try {
            File dir = new File("/Users/jeffcaddel/ws/rice");
            List<File> poms = getJava2wsPoms(dir);
            handlePoms(poms);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    protected void handlePoms(List<File> poms) throws IOException {
        int count = 0;
        for (File pom : poms) {
            List<Execution> executions = getExecutions(pom);
            String properties = getProperties(executions);
        }
    }

    protected String getProperties(List<Execution> executions) {
        StringBuilder sb = new StringBuilder();
        sb.append("<properties>\n");
        for (Execution e : executions) {
            sb.append(getProperties(e));
        }
        sb.append("</properties>\n");
        return sb.toString();
    }

    protected String getProperties(Execution e) {
        String abbreviation = getAbbreviation(e);
        StringBuilder sb = new StringBuilder();
        sb.append("<svc.xxx.name></svc.xxx.name>\n");
        sb.append("<svc.xxx.wsdl>${wsdl.dir}/${svc.xxx.name}</svc.xxx.wsdl>\n");
        return sb.toString();
    }

    protected String getAbbreviation(Execution e) {
        String className = e.getClassName();
        String serviceName = e.getServiceName();
        boolean fixedAlready = serviceName.startsWith("$");
        if (!fixedAlready && !className.endsWith(serviceName)) {
            System.out.println(serviceName + " " + className);
        }
        return serviceName;
    }

    protected List<Execution> getExecutions(File pom) throws IOException {
        String content = FileUtils.readFileToString(pom);
        String plugin = StringUtils.substringBetween(content, "cxf-java2ws-plugin</artifactId>", "</plugin>");
        String executions = parse(plugin, "executions");
        if (StringUtils.isBlank(executions)) {
            return new ArrayList<Execution>();
        }
        int pos = executions.indexOf("</execution>");
        List<Execution> list = new ArrayList<Execution>();
        String token = "</executions>";
        while (pos != -1) {
            String execution = parse(executions, "execution");
            Execution e = getExecution(execution);
            list.add(e);
            executions = executions.substring(pos + token.length());
            pos = executions.indexOf("</execution>");
        }
        return list;
    }

    protected Execution getExecution(String execution) {
        String targetNamespace = parse(execution, "targetNamespace");
        String className = parse(execution, "className");
        String outputFile = parse(execution, "outputFile");
        String serviceName = parse(execution, "serviceName");
        Execution e = new Execution();
        e.setTargetNamespace(targetNamespace);
        e.setClassName(className);
        e.setOutputFile(outputFile);
        e.setServiceName(serviceName);
        return e;

    }

    protected String parse(String s, String tag) {
        return StringUtils.substringBetween(s, "<" + tag + ">", "</" + tag + ">");
    }

    protected List<File> getJava2wsPoms(File dir) throws IOException {
        FileFilter filter = new PomFileFilter();
        List<File> poms = getFiles(dir, filter);
        List<File> java2wsPoms = new ArrayList<File>();
        for (File pom : poms) {
            String s = FileUtils.readFileToString(pom);
            if (s.contains("cxf-java2ws-plugin")) {
                java2wsPoms.add(pom);
            }
        }
        return java2wsPoms;
    }

    protected List<File> getFiles(File dir, FileFilter filter) {
        List<File> files = new ArrayList<File>();
        File[] list = dir.listFiles();
        for (File element : list) {
            if (!element.isDirectory()) {
                boolean accept = filter.accept(element);
                if (accept) {
                    files.add(element);
                }
            } else {
                files.addAll(getFiles(element, filter));
            }
        }
        return files;
    }
}
