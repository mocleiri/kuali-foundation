/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.kuali.common.aws.cloudfront;

/**
 * Pojo that represents one row in the directory listing of the contents of a directory in an S3 bucket
 */
public class DisplayRow {

	String show;
	String image;
	String ahref;
	String lastModified;
	String size;

	public String getImage() {
		return image;
	}

	public void setImage(final String image) {
		this.image = image;
	}

	public String getAhref() {
		return ahref;
	}

	public void setAhref(final String ahref) {
		this.ahref = ahref;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(final String date) {
		this.lastModified = date;
	}

	public String getSize() {
		return size;
	}

	public void setSize(final String size) {
		this.size = size;
	}

	public String getShow() {
		return show;
	}

	public void setShow(String show) {
		this.show = show;
	}

}
