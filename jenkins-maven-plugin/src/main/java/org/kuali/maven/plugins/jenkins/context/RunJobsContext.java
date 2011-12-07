package org.kuali.maven.plugins.jenkins.context;

import java.util.Map;

public interface RunJobsContext extends SimpleJobsContext {

    boolean isWait();

    boolean isSkipIfNoChanges();

    Map<String, String> getParamMap();

    String getParams();



}
