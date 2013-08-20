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
import org.kuali.common.util.log.log4j.jaxb.UnmodifiableAppenderListAdapter;
import org.kuali.common.util.xml.jaxb.DropFalseAdapter;

@XmlRootElement(name = "log4j:configuration")
@XmlAccessorType(XmlAccessType.PROPERTY)
public final class Log4JConfiguration {

	public static final boolean DEFAULT_RESET = false;
	public static final String DEFAULT_NAMESPACE = "http://jakarta.apache.org/log4j/";
	public static final Debug DEFAULT_DEBUG = Debug.DEFAULT_VALUE;
	public static final List<Logger> NO_LOGGERS = Collections.<Logger> emptyList();
	public static final List<Appender> NO_APPENDERS = Collections.<Appender> emptyList();

	@XmlAttribute(name = "xmlns:log4j")
	private final String namespace;

	@XmlElement(name = "appender")
	@XmlJavaTypeAdapter(UnmodifiableAppenderListAdapter.class)
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

		public Builder(Logger root) {
			Assert.notNull(root);
			Assert.isFalse(Threshold.NULL.equals(root.getLevel().getValue()), "root logging level is null");
			this.root = root;
		}

		private final Logger root;

		private List<Appender> appenders = NO_APPENDERS;
		private String namespace = DEFAULT_NAMESPACE;
		private List<Logger> loggers = NO_LOGGERS;
		private boolean reset = DEFAULT_RESET;
		private Debug debug = DEFAULT_DEBUG;
		private Threshold threshold = Threshold.DEFAULT_REPOSITORY_VALUE;

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
			Assert.noNulls(appenders, loggers, debug, threshold);
			Assert.noBlanks(namespace);
			return new Log4JConfiguration(this);
		}
	}

	private Log4JConfiguration() {
		this(new Builder(Logger.NOOP_LOGGER));
	}

	private Log4JConfiguration(Builder builder) {
		this.root = builder.root;
		// Can't make this an unmodifiable copy because doing so blows up JAXB
		this.appenders = new ArrayList<Appender>(builder.appenders);
		this.loggers = new ArrayList<Logger>(builder.loggers);
		this.reset = builder.reset;
		this.debug = builder.debug;
		this.threshold = builder.threshold;
		this.namespace = builder.namespace;
	}

}
