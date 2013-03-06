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

import java.util.List;

import org.kuali.common.threads.ElementHandler;
import org.kuali.common.threads.ListIteratorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA. User: andy Date: 2/21/13 Time: 1:50 PM To change this template use File | Settings | File Templates.
 */
public class MpxBucketHandler implements ElementHandler<MpxBucket> {

	private static final Logger logger = LoggerFactory.getLogger(MpxBucketHandler.class);

	@Override
	public void handleElement(ListIteratorContext<MpxBucket> mpxBucketListIteratorContext, int i, MpxBucket mpxBucket) {
		ImpexExecutorService service = mpxBucket.getService();
		ImportContext context = mpxBucket.getContext();

		List<MpxImportResult> results = mpxBucket.getResults();

		for (MpxExecuteMetaData metaData : mpxBucket.getMpxBeans()) {
			logger.debug("Importing data location: " + metaData.getLocation());
			MpxImportResult result = service.importDataLocation(metaData, context, mpxBucket.getExecutionContext());
			synchronized (results) {
				results.add(result);
			}

			mpxBucket.getProgressListener().progressOccurred(metaData.getRowCount(), 0, null);

			logger.debug("Importing " + metaData.getLocation() + " complete, elapsed time (seconds): " + (result.getElapsed() / 1000.0));
		}
	}
}
