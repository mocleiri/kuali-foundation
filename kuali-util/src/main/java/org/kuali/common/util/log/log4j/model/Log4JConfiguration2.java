package org.kuali.common.util.log.log4j.model;

import java.util.List;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;

public final class Log4JConfiguration2 {

	public static final String DEFAULT_NAMESPACE = "http://jakarta.apache.org/log4j/";
	public static final boolean DEFAULT_RESET = false;

	private final String namespace;
	private final List<Appender> appenders;
	private final Logger root;
	private final List<Logger> loggers;
	private final boolean reset;
	private final Debug debug;
	private final Threshold threshold;

	public String getNamespace() {
		return namespace;
	}

	public List<Appender> getAppenders() {
		return appenders;
	}

	public Logger getRoot() {
		return root;
	}

	public List<Logger> getLoggers() {
		return loggers;
	}

	public Boolean getReset() {
		return reset;
	}

	public Debug getDebug() {
		return debug;
	}

	public Threshold getThreshold() {
		return threshold;
	}

	public static class Builder {

		private List<Appender> appenders = Appender.EMPTY;
		private List<Logger> loggers = Logger.EMPTY;
		private String namespace = DEFAULT_NAMESPACE;
		private boolean reset = DEFAULT_RESET;
		private Debug debug = Debug.DEFAULT_VALUE;
		private Threshold threshold = Threshold.DEFAULT_REPOSITORY_VALUE;

		private final Logger root;

		public Builder(Logger root) {
			Assert.noNulls(root);
			this.root = root;
		}

		public Builder appenders(List<Appender> appenders) {
			this.appenders = appenders;
			return this;
		}

		public Builder appender(Appender appender) {
			this.appenders = CollectionUtils.singletonList(appender);
			return this;
		}

		public Builder namespace(String namespace) {
			this.namespace = namespace;
			return this;
		}

		public Builder logger(Logger logger) {
			this.loggers = CollectionUtils.singletonList(logger);
			return this;
		}

		public Builder loggers(List<Logger> loggers) {
			this.loggers = loggers;
			return this;
		}

		public Builder reset(boolean reset) {
			this.reset = reset;
			return this;
		}

		public Builder debug(Debug debug) {
			this.debug = debug;
			return this;
		}

		public Builder threshold(Threshold threshold) {
			this.threshold = threshold;
			return this;
		}

		public Log4JConfiguration2 build() {
			Assert.noNulls(root, appenders, loggers, debug, threshold);
			Assert.noBlanks(namespace);
			Assert.isFalse(Logger.nullThreshold(root), "root logging threshold is null");
			return new Log4JConfiguration2(this);
		}
	}

	private Log4JConfiguration2(Builder builder) {
		this.root = builder.root;
		this.appenders = CollectionUtils.unmodifiableCopy(builder.appenders);
		this.loggers = CollectionUtils.unmodifiableCopy(builder.loggers);
		this.reset = builder.reset;
		this.debug = builder.debug;
		this.threshold = builder.threshold;
		this.namespace = builder.namespace;
	}

}
