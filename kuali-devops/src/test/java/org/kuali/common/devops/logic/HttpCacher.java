package org.kuali.common.devops.logic;

import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.model.HttpStatus;
import org.kuali.common.http.model.HttpWaitResult;
import org.kuali.common.http.service.DefaultHttpService;
import org.kuali.common.http.service.HttpService;

import com.google.common.base.Optional;

public class HttpCacher {

	private static final HttpService SERVICE = new DefaultHttpService();

	public static Optional<String> getContent(String url) {
		HttpContext context = HttpContext.builder(url).overallTimeout("10s").requestTimeout("3s").quiet(true).build();
		HttpWaitResult result = SERVICE.wait(context);
		if (result.getStatus().equals(HttpStatus.SUCCESS)) {
			return result.getFinalRequestResult().getResponseBody();
		} else {
			return Optional.absent();
		}
	}

}
