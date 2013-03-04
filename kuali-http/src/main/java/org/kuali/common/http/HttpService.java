package org.kuali.common.http;

public interface HttpService {

	HttpWaitResult wait(HttpContext context);

	String getStatusText(HttpRequestResult result);

}
