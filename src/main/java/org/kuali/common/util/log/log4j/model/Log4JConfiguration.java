package org.kuali.common.util.log.log4j.model;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.ListUtils;
import org.kuali.common.util.log.log4j.jaxb.DebugAdapter;
import org.kuali.common.util.log.log4j.jaxb.RepositoryThresholdAdapter;
import org.kuali.common.util.xml.jaxb.adapter.OmitFalseAdapter;

@XmlRootElement(name = "log4j:configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public final class Log4JConfiguration {

	@XmlAttribute(name = "xmlns:log4j")
	private final String namespace;

	@XmlElement(name = "appender")
	private final List<Appender> appenders;

	@XmlElement
	private final Logger root;

	@XmlElement(name = "logger")
	private final List<Logger> loggers;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitFalseAdapter.class)
	private final Boolean reset;

	@XmlAttribute
	@XmlJavaTypeAdapter(DebugAdapter.class)
	private final Debug debug;

	@XmlAttribute
	@XmlJavaTypeAdapter(RepositoryThresholdAdapter.class)
	private final Threshold threshold;

	// Only expose an unmodifiable version of our internal list
	public List<Logger> getLoggers() {
		return Collections.unmodifiableList(loggers);
	}

	// Only expose an unmodifiable version of our internal list
	public List<Appender> getAppenders() {
		return Collections.unmodifiableList(appenders);
	}

	public boolean getReset() {
		return reset;
	}

	public Debug getDebug() {
		return debug;
	}

	public Threshold getThreshold() {
		return threshold;
	}

	public Logger getRoot() {
		return root;
	}

	public String getNamespace() {
		return namespace;
	}

	public static class Builder {

		// Required
		private final Logger root;

		// Optional
		private String namespace = "http://jakarta.apache.org/log4j/";
		private List<Appender> appenders = Appender.EMPTY;
		private List<Logger> loggers = Logger.EMPTY;
		private boolean reset = false;
		private Debug debug = Debug.DEFAULT_VALUE;
		private Threshold threshold = Threshold.DEFAULT_REPOSITORY_VALUE;

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

		private Builder finish() {

			// Ensure we are being configured correctly
			Assert.noNulls(root, appenders, loggers, debug, threshold);
			Assert.isFalse(Logger.isThresholdNull(root), "root logging threshold is null");
			Assert.noBlanks(namespace);

			// Defensive copies of the 2 lists we were passed
			this.appenders = ListUtils.newArrayList(appenders);
			this.loggers = ListUtils.newArrayList(loggers);

			// Return the fully configured Builder
			return this;
		}

		public Log4JConfiguration build() {
			finish(); // Finish setting things up
			return new Log4JConfiguration(this);
		}
	}

	// This is a concession to JAXB so it can unmarshal the object from XML
	private Log4JConfiguration() {
		this(new Builder(Logger.DEFAULT).finish());
	}

	private Log4JConfiguration(Builder builder) {
		this.root = builder.root;
		this.appenders = builder.appenders;
		this.loggers = builder.loggers;
		this.reset = builder.reset;
		this.debug = builder.debug;
		this.threshold = builder.threshold;
		this.namespace = builder.namespace;
	}

}
