package org.kuali.common.util.log.log4j.model;

import java.util.ArrayList;
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
import org.kuali.common.util.log.log4j.jaxb.DebugAdapter;
import org.kuali.common.util.log.log4j.jaxb.RepositoryThresholdAdapter;
import org.kuali.common.util.xml.jaxb.DropFalseAdapter;

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
	@XmlJavaTypeAdapter(DropFalseAdapter.class)
	private final Boolean reset;

	@XmlAttribute
	@XmlJavaTypeAdapter(DebugAdapter.class)
	private final Debug debug;

	@XmlAttribute
	@XmlJavaTypeAdapter(RepositoryThresholdAdapter.class)
	private final Threshold threshold;

	public List<Logger> getLoggers() {
		return Collections.unmodifiableList(loggers);
	}

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

		private List<Appender> appenders = Appender.EMPTY;
		private String namespace = "http://jakarta.apache.org/log4j/";
		private List<Logger> loggers = Logger.EMPTY;
		private boolean reset = false;
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

		public Log4JConfiguration build() {
			Assert.noNullsWithMsg("required field is null", root, appenders, loggers, debug, threshold);
			Assert.isFalse(Logger.nullThreshold(root), "root logging threshold is null");
			Assert.noBlanksWithMsg("namespace is blank", namespace);
			this.appenders = new ArrayList<Appender>(appenders);
			this.loggers = new ArrayList<Logger>(loggers);
			return new Log4JConfiguration(this);
		}
	}

	private Log4JConfiguration() {
		this(new Builder(Logger.NOOP));
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
