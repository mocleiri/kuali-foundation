/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.channel.model;

import org.kuali.common.util.Assert;

public final class CopyResult {

	public CopyResult(long startMillis, long transferAmountInBytes, CopyDirection direction) {
		this.stopMillis = System.currentTimeMillis();
		Assert.isTrue(stopMillis >= startMillis);
		Assert.positive(startMillis);
		Assert.notNegative(transferAmountInBytes);
		Assert.noNulls(direction);
		this.startMillis = startMillis;
		this.amountInBytes = transferAmountInBytes;
		this.elapsedMillis = stopMillis - startMillis;
		this.direction = direction;
	}

	private final long startMillis;
	private final long stopMillis;
	private final long elapsedMillis;
	private final long amountInBytes;
	private final CopyDirection direction;

	public long getStartMillis() {
		return startMillis;
	}

	public long getStopMillis() {
		return stopMillis;
	}

	public long getElapsedMillis() {
		return elapsedMillis;
	}

	public long getAmountInBytes() {
		return amountInBytes;
	}

	public CopyDirection getDirection() {
		return direction;
	}

}
