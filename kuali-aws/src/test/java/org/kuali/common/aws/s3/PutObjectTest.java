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
package org.kuali.common.aws.s3;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.file.CanonicalFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

public class PutObjectTest {
	private static final String ACCESSKEY = "AKIAJFD5IM7IPVVUEBNA";
	private static final String SECRETYKEY = System.getProperty("secret.key");

	private static final Logger log = LoggerFactory.getLogger(PutObjectTest.class);

	@Test
	public void listObjectsTest() {
		try {
			AmazonS3Client client = new AmazonS3Client(getCredentials());
			ObjectListing listing = client.listObjects("site.origin.kuali.org", "common/kuali-spaces/1.0.0-SNAPSHOT/myimages/dir+with+spaces/");
			System.out.println("truncated=" + listing.isTruncated());
			List<S3ObjectSummary> summaries = listing.getObjectSummaries();
			System.out.println("summaries.size()=" + summaries.size());
			for (S3ObjectSummary summary : summaries) {
				System.out.println(summary.getKey());
			}
			System.out.println(encodeUTF8("+"));
			String dir = System.getProperty("java.io.tmpdir") + "/dir with spaces";
			String encoded = encodeUTF8(dir);
			File file = new CanonicalFile(dir);
			System.out.println(LocationUtils.getCanonicalURLString(file));
			System.out.println(encoded);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected static String encodeUTF8(String s) {
		try {
			return URLEncoder.encode(s, "UTF-8");
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	@Test
	@Ignore
	public void filesWithSpacesTest() {
		try {
			String filename = "/Users/jcaddel/Downloads/icon with spaces.png";
			String encoded = URLEncoder.encode(filename, "UTF-8");
			System.out.println(encoded);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected AWSCredentials getCredentials() {
		log.debug("access key: " + ACCESSKEY);
		return new BasicAWSCredentials(ACCESSKEY, SECRETYKEY);
	}

	protected AmazonS3Client getClient() {
		AWSCredentials credentials = getCredentials();
		return new AmazonS3Client(credentials);
	}

}
