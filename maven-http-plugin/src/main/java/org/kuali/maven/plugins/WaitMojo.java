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
package org.kuali.maven.plugins;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;

/**
 * Contact the specified HTTP URL until a valid HTTP response code is returned or the maximum wait timeout is exceeded,
 * whichever comes first.
 *
 * @author Jeff Caddel
 * @goal wait
 * @execute phase="validate"
 * @threadSafe
 * @since 1.0
 */
public class WaitMojo extends AbstractMojo {

    /**
     * The url to contact
     *
     * @parameter expression="${http.url}" default-value="http://localhost"
     */
    private String url;

    /**
     * The maximum number of seconds to wait for the url to respond correctly
     *
     * @parameter expression="${http.timeout}" default-value="180"
     */
    private int timeout;

    /**
     * The maximum number of milliseconds to wait for an individual HTTP request to complete
     *
     * @parameter expression="${http.requestTimeout}" default-value="3000"
     */
    private int requestTimeout;

    /**
     * The number of milliseconds to sleep in between HTTP requests
     *
     * @parameter expression="${http.sleepInterval}" default-value="3000"
     */
    private int sleepInterval;

    /**
     * Comma separated list of HTTP status codes that represent success
     *
     * @parameter expression="${http.successCodes}" default-value="200"
     */
    private String httpSuccessCodes;

    /**
     * Comma separated list of HTTP status codes that means we should continue waiting for a success code
     *
     * @parameter expression="${http.httpContinueWaitingCodes}" default-value="503"
     */
    private String httpContinueWaitingCodes;

    protected HttpInspector getHttpInspector() throws MojoExecutionException {
        HttpInspector inspector = new HttpInspector();
        try {
            BeanUtils.copyProperties(inspector, this);
        } catch (Exception e) {
            throw new MojoExecutionException("Error copying properties", e);
        }
        String[] successCodeStrings = StringUtils.splitByWholeSeparator(httpSuccessCodes, ",");
        List<Integer> successCodeList = new ArrayList<Integer>();
        for (String successCodeString : successCodeStrings) {
            successCodeList.add(new Integer(successCodeString));
        }
        inspector.setSuccessCodes(successCodeList);
        return inspector;
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        HttpInspector inspector = getHttpInspector();
        Result result = inspector.wait(url);
        boolean success = result.equals(Result.SUCCESS);
        if (!success) {
            throw new MojoExecutionException("Waiting for a response from '" + url + "' was not successful");
        }
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getRequestTimeout() {
        return requestTimeout;
    }

    public void setRequestTimeout(int requestTimeout) {
        this.requestTimeout = requestTimeout;
    }

    public int getSleepInterval() {
        return sleepInterval;
    }

    public void setSleepInterval(int sleepInterval) {
        this.sleepInterval = sleepInterval;
    }

    public String getHttpSuccessCodes() {
        return httpSuccessCodes;
    }

    public void setHttpSuccessCodes(String httpSuccessCodes) {
        this.httpSuccessCodes = httpSuccessCodes;
    }

    public String getHttpContinueWaitingCodes() {
        return httpContinueWaitingCodes;
    }

    public void setHttpContinueWaitingCodes(String httpContinueWaitingCodes) {
        this.httpContinueWaitingCodes = httpContinueWaitingCodes;
    }

}
