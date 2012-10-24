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
package org.kuali.common.aws.s3;

public enum Size {

	BYTE(1, "bytes", "bytes/s"), //
	KB(1024, "k", "KB/s"), //
	MB(1024 * Size.KB.getValue(), "m", "MB/s"), //
	GB(1024 * Size.MB.getValue(), "g", "GB/s"), //
	TB(1024 * Size.GB.getValue(), "t", "TB/s"), //
	PB(1024 * Size.TB.getValue(), "p", "PB/s"), //
	EB(1024 * Size.PB.getValue(), "e", "EB/s");

	private long value;
	private String sizeLabel;
	private String rateLabel;

	private Size(long value, String sizeLabel, String rateLabel) {
		this.value = value;
		this.sizeLabel = sizeLabel;
		this.rateLabel = rateLabel;
	}

	public long getValue() {
		return value;
	}

	public String getSizeLabel() {
		return sizeLabel;
	}

	public String getRateLabel() {
		return rateLabel;
	}

}
