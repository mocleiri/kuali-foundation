package org.kuali.common.devops.cache;

import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.validate.Validation.checkConstraints;

import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.model.HttpRequestResult;
import org.kuali.common.http.model.HttpWaitResult;
import org.kuali.common.http.service.DefaultHttpService;
import org.kuali.common.http.service.HttpService;
import org.kuali.common.util.build.LegacyValidatingBuilder;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.validate.IdiotProofImmutable;
import org.springframework.beans.BeanUtils;

import com.google.common.cache.CacheLoader;

@IdiotProofImmutable
public final class UrlLoader extends CacheLoader<String, HttpRequestResult> {

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

	private UrlLoader(Builder builder) {
		this.context = builder.context;
		this.service = builder.service;
	}

	public static UrlLoader create(HttpContext context) {
		return builder().context(context).build();
	}

	public static UrlLoader create(HttpContext context, HttpService service) {
		return builder().context(context).service(service).build();
	}

	public static UrlLoader create() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends LegacyValidatingBuilder<UrlLoader> {

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
		public UrlLoader build() {
			return checkConstraints(new UrlLoader(this), validator);
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
