package org.kuali.common.aws.s3.cloudfront;

import com.amazonaws.AmazonWebServiceRequest;

public class TypedRequest {

	AmazonWebServiceRequestType type;
	AmazonWebServiceRequest request;

	public AmazonWebServiceRequestType getType() {
		return type;
	}

	public void setType(AmazonWebServiceRequestType type) {
		this.type = type;
	}

	public AmazonWebServiceRequest getRequest() {
		return request;
	}

	public void setRequest(AmazonWebServiceRequest request) {
		this.request = request;
	}

}
