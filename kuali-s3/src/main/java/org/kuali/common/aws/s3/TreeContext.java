package org.kuali.common.aws.s3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.amazonaws.services.s3.AmazonS3Client;

public class TreeContext {

	public static final long DEFAULT_PREFIX_COUNT_ESTIMATE = 100;
	public static final String DEFAULT_DELIMITER = "/";
	public static final String CACHE_CONTROL = "max-age=3600, must-revalidate";
	public static final List<String> DEFAULT_WELCOME_FILES = new ArrayList<String>(Arrays.asList("index.html", "portal.html"));

	String delimiter = DEFAULT_DELIMITER;
	long prefixCountEstimate = DEFAULT_PREFIX_COUNT_ESTIMATE;
	List<String> welcomeFiles = DEFAULT_WELCOME_FILES;
	AmazonS3Client client;
	String bucket;
	String prefix;
	List<String> includes;
	List<String> excludes;

	public AmazonS3Client getClient() {
		return client;
	}

	public void setClient(AmazonS3Client client) {
		this.client = client;
	}

	public String getBucket() {
		return bucket;
	}

	public void setBucket(String bucket) {
		this.bucket = bucket;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getDelimiter() {
		return delimiter;
	}

	public void setDelimiter(String delimiter) {
		this.delimiter = delimiter;
	}

	public List<String> getIncludes() {
		return includes;
	}

	public void setIncludes(List<String> includes) {
		this.includes = includes;
	}

	public List<String> getExcludes() {
		return excludes;
	}

	public void setExcludes(List<String> excludes) {
		this.excludes = excludes;
	}

	public long getPrefixCountEstimate() {
		return prefixCountEstimate;
	}

	public void setPrefixCountEstimate(long prefixCountEstimate) {
		this.prefixCountEstimate = prefixCountEstimate;
	}

}
