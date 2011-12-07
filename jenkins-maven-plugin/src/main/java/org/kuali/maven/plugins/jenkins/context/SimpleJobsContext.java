package org.kuali.maven.plugins.jenkins.context;

import java.util.List;

public interface SimpleJobsContext {

    String getJobCmd();

    List<String> getNames();

    List<String> getTypes();

}
