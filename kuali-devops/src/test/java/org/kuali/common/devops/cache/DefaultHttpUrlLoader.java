package org.kuali.common.devops.cache;

import static org.kuali.common.util.base.Precondition.checkNotBlank;

import java.io.IOException;

import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.model.HttpWaitResult;
import org.kuali.common.http.service.HttpService;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;
import org.springframework.beans.BeanUtils;

import com.google.common.base.Optional;
import com.google.common.cache.CacheLoader;

@IdiotProofImmutable
public final class DefaultHttpUrlLoader extends CacheLoader<String, Optional<String>> {

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

	private DefaultHttpUrlLoader(Builder builder) {
		this.context = builder.context;
		this.service = builder.service;
	}

	public static class Builder extends ValidatingBuilder<DefaultHttpUrlLoader> {

		private HttpContext context;
		private HttpService service;

		public Builder context(HttpContext context) {
			this.context = context;
			return this;
		}

		public Builder service(HttpService service) {
			this.service = service;
			return this;
		}

		@Override
		public DefaultHttpUrlLoader getInstance() {
			return new DefaultHttpUrlLoader(this);
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
