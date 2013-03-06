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

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.jdbc.context.ExecutionContext;
import org.kuali.common.threads.listener.ProgressListener;

/**
 * @author andrewlubbers
 */
public class MpxBucket implements Comparable<MpxBucket> {

	ImportContext context;
	ImpexExecutorService service;
	ProgressListener<MpxBucket> progressListener;
	List<MpxImportResult> results;
	List<MpxExecuteMetaData> mpxBeans = new ArrayList<MpxExecuteMetaData>();
	Long allRowCounts = 0l;
	ExecutionContext executionContext;

	@Override
	public int compareTo(MpxBucket o) {
		return allRowCounts.compareTo(o.getAllRowCounts());
	}

	public ImportContext getContext() {
		return context;
	}

	public void setContext(ImportContext context) {
		this.context = context;
	}

	public long getAllRowCounts() {
		return allRowCounts;
	}

	public void setAllRowCounts(long allRowCounts) {
		this.allRowCounts = allRowCounts;
	}

	public ProgressListener<MpxBucket> getProgressListener() {
		return progressListener;
	}

	public void setProgressListener(ProgressListener<MpxBucket> progressListener) {
		this.progressListener = progressListener;
	}

	public List<MpxImportResult> getResults() {
		return results;
	}

	public void setResults(List<MpxImportResult> results) {
		this.results = results;
	}

	public ImpexExecutorService getService() {
		return service;
	}

	public void setService(ImpexExecutorService service) {
		this.service = service;
	}

	public ExecutionContext getExecutionContext() {
		return executionContext;
	}

	public void setExecutionContext(ExecutionContext executionContext) {
		this.executionContext = executionContext;
	}

	public List<MpxExecuteMetaData> getMpxBeans() {
		return mpxBeans;
	}

	public void setMpxBeans(List<MpxExecuteMetaData> mpxBeans) {
		this.mpxBeans = mpxBeans;
	}
}
