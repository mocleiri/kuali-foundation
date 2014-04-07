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

import org.kuali.common.aws.s3.BucketContext;

public class DirectoryListingContext {

	HtmlGeneratorContext generatorContext;
	BucketContext bucketContext;
	String about;
	IndexContext indexContext;

	public DirectoryListingContext() {
		this(null, null, null, null);
	}

	public DirectoryListingContext(HtmlGeneratorContext generatorContext, BucketContext bucketContext, String about, IndexContext indexContext) {
		super();
		this.generatorContext = generatorContext;
		this.bucketContext = bucketContext;
		this.about = about;
		this.indexContext = indexContext;
	}

	public HtmlGeneratorContext getGeneratorContext() {
		return generatorContext;
	}

	public void setGeneratorContext(HtmlGeneratorContext generatorContext) {
		this.generatorContext = generatorContext;
	}

	public BucketContext getBucketContext() {
		return bucketContext;
	}

	public void setBucketContext(BucketContext bucketContext) {
		this.bucketContext = bucketContext;
	}

	public IndexContext getIndexContext() {
		return indexContext;
	}

	public void setIndexContext(IndexContext indexContext) {
		this.indexContext = indexContext;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

}
