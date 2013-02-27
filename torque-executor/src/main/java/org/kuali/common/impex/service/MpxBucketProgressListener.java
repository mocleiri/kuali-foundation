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

package org.kuali.common.impex.service;

import org.kuali.common.threads.listener.ProgressEvent;
import org.kuali.common.threads.listener.ProgressListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;

/**
 * @author andrewlubbers
 */
public class MpxBucketProgressListener implements ProgressListener<MpxBucket> {

    PrintStream out = System.out;
    Long count = 0l;
    Long total = 0l;
    int percentCompletePrevious;
    String startToken = "[INFO] Progress: ";
    String progressToken = ".";
    String completeToken = "\n";

    @Override
    public void progressStarted() {
    }

    @Override
    public synchronized void progressOccurred(int count, int total, ProgressEvent<MpxBucket> event) {
        if(this.count == 0) {
            out.print(startToken);
        }

        // Increment the counter
        this.count += count;

        // Print a dot anytime we make 1% progress
        int percentComplete = (int)((this.count * 100) / getTotal());
        if (percentComplete > percentCompletePrevious) {
            int percentsToAdd = percentComplete - percentCompletePrevious;
            for(int i = 0; i < percentsToAdd; i++) {
                out.print(progressToken);
            }
            this.percentCompletePrevious = percentComplete;
        }
    }

    @Override
    public void progressCompleted() {
        out.print(completeToken);
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public String getStartToken() {
        return startToken;
    }

    public void setStartToken(String startToken) {
        this.startToken = startToken;
    }

    public String getProgressToken() {
        return progressToken;
    }

    public void setProgressToken(String progressToken) {
        this.progressToken = progressToken;
    }

    public PrintStream getOut() {
        return out;
    }

    public void setOut(PrintStream out) {
        this.out = out;
    }
}
