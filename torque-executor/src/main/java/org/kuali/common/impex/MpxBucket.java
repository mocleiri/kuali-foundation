/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex;

import org.kuali.common.impex.service.ImpexContext;
import org.kuali.common.impex.service.ImpexGeneratorService;
import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.util.PercentCompleteInformer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author andrewlubbers
 */
public class MpxBucket implements Comparable<MpxBucket> {

    ImpexContext context;
    ImpexGeneratorService service;
    List<File> files = new ArrayList<File>();
    PercentCompleteInformer progressTracker;
    List<MpxImportResult> results;
    Long allFilesSize = 0l;
    private ExecutionContext executionContext;

    @Override
    public int compareTo(MpxBucket o) {
        return allFilesSize.compareTo(o.getAllFilesSize());
    }

    public ImpexContext getContext() {
        return context;
    }

    public void setContext(ImpexContext context) {
        this.context = context;
    }

    public List<File> getFiles() {
        return files;
    }

    public void setFiles(List<File> files) {
        this.files = files;
    }

    public long getAllFilesSize() {
        return allFilesSize;
    }

    public void setAllFilesSize(long allFilesSize) {
        this.allFilesSize = allFilesSize;
    }

    public PercentCompleteInformer getProgressTracker() {
        return progressTracker;
    }

    public void setProgressTracker(PercentCompleteInformer progressTracker) {
        this.progressTracker = progressTracker;
    }

    public List<MpxImportResult> getResults() {
        return results;
    }

    public void setResults(List<MpxImportResult> results) {
        this.results = results;
    }

    public ImpexGeneratorService getService() {
        return service;
    }

    public void setService(ImpexGeneratorService service) {
        this.service = service;
    }

    public ExecutionContext getExecutionContext() {
        return executionContext;
    }

    public void setExecutionContext(ExecutionContext executionContext) {
        this.executionContext = executionContext;
    }
}
