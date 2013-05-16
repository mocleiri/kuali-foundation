/**
 * Copyright 2004-2013 The Kuali Foundation
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
package org.kuali.common.aws.s3.cloudfront;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.kuali.common.aws.s3.TreeContext;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;

/**
 * Holds context information for S3
 */
public class S3BucketContext {

	public static final String DEFAULT_FILE_IMAGE = "http://s3browse.ks.kuali.org/images/page_white.png";
	public static final String DEFAULT_DIR_IMAGE = "http://s3browse.ks.kuali.org/images/folder.png";
	public static final String DEFAULT_CSS = "http://s3browse.ks.kuali.org/css/style.css";
	public static final CannedAccessControlList DEFAULT_ACL = CannedAccessControlList.PublicRead;
	public static final String DEFAULT_DATE_FORMAT = "EEE, dd MMM yyyy HH:mm:ss z";
	public static final String DEFAULT_TIMEZONE = "UTC";

	String fileImage = DEFAULT_FILE_IMAGE;
	String directoryImage = DEFAULT_DIR_IMAGE;
	String css = DEFAULT_CSS;
	CannedAccessControlList acl = DEFAULT_ACL;
	String delimiter = TreeContext.DEFAULT_DELIMITER;
	SimpleDateFormat lastModifiedDateFormatter = getDefaultLastModifiedDateFormatter();

	AmazonS3Client client;
	String bucket;
	String defaultObject;
	String about;
	Integer maxKeys;

	protected static SimpleDateFormat getDefaultLastModifiedDateFormatter() {
		SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
		return sdf;
	}

	public AmazonS3Client getClient() {
		return client;
	}

	public void setClient(final AmazonS3Client client) {
		this.client = client;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(final String bucket) {
		this.bucket = bucket;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(final String delimiter) {
		this.delimiter = delimiter;
	}

	public String getFileImage() {
		return fileImage;
	}

	public void setFileImage(final String fileImage) {
		this.fileImage = fileImage;
	}

	public String getDirectoryImage() {
		return directoryImage;
	}

	public void setDirectoryImage(final String directoryImage) {
		this.directoryImage = directoryImage;
	}

	public String getCss() {
		return css;
	}

	public void setCss(final String css) {
		this.css = css;
	}

	public String getDefaultObject() {
		return defaultObject;
	}

	public void setDefaultObject(final String defaultObject) {
		this.defaultObject = defaultObject;
	}

	public SimpleDateFormat getLastModifiedDateFormatter() {
		return lastModifiedDateFormatter;
	}

	public void setLastModifiedDateFormatter(final SimpleDateFormat dateFormatter) {
		this.lastModifiedDateFormatter = dateFormatter;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(final String about) {
		this.about = about;
	}

	public CannedAccessControlList getAcl() {
		return acl;
	}

	public void setAcl(CannedAccessControlList acl) {
		this.acl = acl;
	}

	public Integer getMaxKeys() {
		return maxKeys;
	}

	public void setMaxKeys(Integer maxKeys) {
		this.maxKeys = maxKeys;
	}
}
