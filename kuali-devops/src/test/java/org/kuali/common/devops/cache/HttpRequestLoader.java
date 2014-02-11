package org.kuali.common.devops.cache;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.model.HttpRequestResult;
import org.kuali.common.http.model.HttpWaitResult;
import org.kuali.common.http.service.DefaultHttpService;
import org.kuali.common.http.service.HttpService;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.validate.IdiotProofImmutable;
import org.springframework.beans.BeanUtils;

import com.google.common.cache.CacheLoader;

@IdiotProofImmutable
public final class HttpRequestLoader extends CacheLoader<String, HttpRequestResult> {

	private final HttpContext context;
	private final HttpService service;

	@Override
	public HttpRequestResult load(String url) {
		checkNotBlank(url, "url");
		HttpContext.Builder builder = HttpContext.builder();
		BeanUtils.copyProperties(context, builder);
		builder.setUrl(url);
		HttpContext context = builder.build();
		HttpWaitResult result = service.wait(context);
		return result.getFinalRequestResult();
	}

	private HttpRequestLoader(Builder builder) {
		this.context = builder.context;
		this.service = builder.service;
	}

	public static HttpRequestLoader create(HttpContext context) {
		return builder().context(context).build();
	}

	public static HttpRequestLoader create(HttpContext context, HttpService service) {
		return builder().context(context).service(service).build();
	}

	public static HttpRequestLoader create() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<HttpRequestLoader> {

		private HttpContext context = HttpContext.builder(NullUtils.NONE).build();
		private HttpService service = new DefaultHttpService();

		public Builder context(HttpContext context) {
			this.context = context;
			return this;
		}

		public Builder service(HttpService service) {
			this.service = service;
			return this;
		}

		@Override
		public HttpRequestLoader getInstance() {
			return new HttpRequestLoader(this);
		}

		public HttpContext getContext() {
			return context;
		}

		public void setContext(HttpContext context) {
			this.context = context;
		}

		public HttpService getService() {
			return service;
		}

		public void setService(HttpService service) {
			this.service = service;
		}

	}

	public HttpContext getContext() {
		return context;
	}

	public HttpService getService() {
		return service;
	}

}
