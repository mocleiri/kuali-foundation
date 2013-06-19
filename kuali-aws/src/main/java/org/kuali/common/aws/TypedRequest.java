package org.kuali.common.aws;


import com.amazonaws.AmazonWebServiceRequest;

public class TypedRequest {

	AmazonWebServiceRequest request;
	AmazonWebServiceRequestType type;

	public TypedRequest() {
		this(null, null);
	}

	public TypedRequest(AmazonWebServiceRequest request, AmazonWebServiceRequestType type) {
		super();
		this.request = request;
		this.type = type;
	}

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
