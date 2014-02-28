package org.kuali.common.devops.cache;

import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.springframework.beans.BeanUtils.copyProperties;

import java.util.Set;

import javax.validation.ConstraintViolation;

import org.kuali.common.http.model.HttpContext;
import org.kuali.common.http.model.HttpWaitResult;
import org.kuali.common.http.service.DefaultHttpService;
import org.kuali.common.http.service.HttpService;
import org.kuali.common.util.build.ViolationsBuilder;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.cache.CacheLoader;

@IdiotProofImmutable
public final class UrlLoader extends CacheLoader<String, HttpWaitResult> {

	private final HttpContext context;
	private final HttpService service;

	@Override
	public HttpWaitResult load(String url) {
		checkNotBlank(url, "url");
		HttpContext.Builder builder = HttpContext.builder();
		copyProperties(context, builder);
		builder.setUrl(url);
		HttpContext context = builder.build();
		return service.wait(context);
	}

	private UrlLoader(Builder builder) {
		this.context = builder.context;
		this.service = builder.service;
	}

	public static UrlLoader newUrlLoader() {
		return builder().build();
	}

	public static UrlLoader newUrlLoader(HttpContext context) {
		return builder().context(context).build();
	}

	public static UrlLoader newUrlLoader(HttpContext context, HttpService service) {
		return builder().context(context).service(service).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ViolationsBuilder<UrlLoader> {

		private HttpContext context = HttpContext.builder(NullUtils.NONE).build();
		private HttpService service = new DefaultHttpService();

		@Override
		public Set<ConstraintViolation<UrlLoader>> violations() {
			return violations(make());
		}

		@Override
		public UrlLoader build() {
			return validate(make());
		}

		private UrlLoader make() {
			return new UrlLoader(this);
		}

		public Builder context(HttpContext context) {
			this.context = context;
			return this;
		}

		public Builder service(HttpService service) {
			this.service = service;
			return this;
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
