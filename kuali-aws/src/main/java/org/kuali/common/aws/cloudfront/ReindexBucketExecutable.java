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

import java.util.List;

import org.kuali.common.aws.s3.BucketContext;
import org.kuali.common.aws.s3.BucketService;
import org.kuali.common.aws.s3.ListingResult;
import org.kuali.common.aws.s3.ObjectListingsContext;
import org.kuali.common.util.execute.Executable;

import com.amazonaws.services.s3.model.ObjectListing;

public class ReindexBucketExecutable implements Executable {

	CloudFrontContext cloudFrontContext;
	ObjectListingsContext objectListingsContext;
	BucketContext bucketContext;
	BucketService bucketService;
	HtmlGeneratorService generatorService;
	HtmlGeneratorContext generatorContext;
	ListingConverterService converterService;
	ListingConverterContext converterContext;
	String about;

	@Override
	public void execute() {
		ListingResult listingResult = bucketService.getObjectListings(objectListingsContext);

		for (ObjectListing listing : listingResult.getListings()) {
			IndexDataContext idc = null;// new IndexDataContext(bucketContext, converterContext, listing);
			List<String[]> indexData = converterService.getIndexData(idc);
			String welcomeFileKey = CloudFrontUtils.getFirstMatchingKey(listing, cloudFrontContext.getWelcomeFiles());
			IndexContext ic = new IndexContext(listing, indexData, welcomeFileKey);
			DirectoryListingContext dlc = new DirectoryListingContext(generatorContext, bucketContext, about, ic);
			String html = generatorService.getDirectoryListing(dlc);
			System.out.println(html);
		}

	}

	public BucketContext getBucketContext() {
		return bucketContext;
	}

	public void setBucketContext(BucketContext bucketContext) {
		this.bucketContext = bucketContext;
	}

	public BucketService getBucketService() {
		return bucketService;
	}

	public void setBucketService(BucketService bucketService) {
		this.bucketService = bucketService;
	}

	public CloudFrontContext getCloudFrontContext() {
		return cloudFrontContext;
	}

	public void setCloudFrontContext(CloudFrontContext cloudFrontContext) {
		this.cloudFrontContext = cloudFrontContext;
	}

	public HtmlGeneratorService getGeneratorService() {
		return generatorService;
	}

	public void setGeneratorService(HtmlGeneratorService generatorService) {
		this.generatorService = generatorService;
	}

	public HtmlGeneratorContext getGeneratorContext() {
		return generatorContext;
	}

	public void setGeneratorContext(HtmlGeneratorContext generatorContext) {
		this.generatorContext = generatorContext;
	}

	public ListingConverterService getConverterService() {
		return converterService;
	}

	public void setConverterService(ListingConverterService converterService) {
		this.converterService = converterService;
	}

	public ListingConverterContext getConverterContext() {
		return converterContext;
	}

	public void setConverterContext(ListingConverterContext converterContext) {
		this.converterContext = converterContext;
	}

	public ObjectListingsContext getObjectListingsContext() {
		return objectListingsContext;
	}

	public void setObjectListingsContext(ObjectListingsContext objectListingsContext) {
		this.objectListingsContext = objectListingsContext;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

}
