package org.kuali.common.devops.cache;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.IOException;

import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.model.HttpWaitResult;
import org.kuali.common.http.service.DefaultHttpService;
import org.kuali.common.http.service.HttpService;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.validate.IdiotProofImmutable;
import org.springframework.beans.BeanUtils;

import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;

@IdiotProofImmutable
public final class HttpLoader extends CacheLoader<String, Optional<String>> {

	private final HttpContext context;
	private final HttpService service;

	@Override
	public Optional<String> load(String url) throws IOException {
		checkNotBlank(url, "url");
		HttpContext.Builder builder = HttpContext.builder();
		BeanUtils.copyProperties(context, builder);
		builder.setUrl(url);
		HttpContext context = builder.build();
		HttpWaitResult result = service.wait(context);
		return result.getFinalRequestResult().getResponseBody();
	}

	private HttpLoader(Builder builder) {
		this.context = builder.context;
		this.service = builder.service;
	}

	public static HttpLoader create(HttpContext context) {
		return builder().context(context).build();
	}

	public static HttpLoader create() {
		return builder().build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<HttpLoader> {

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
		public HttpLoader getInstance() {
			return new HttpLoader(this);
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
