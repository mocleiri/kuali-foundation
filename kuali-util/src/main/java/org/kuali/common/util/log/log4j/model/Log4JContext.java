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
import org.kuali.common.util.log.log4j.jaxb.ThresholdAdapter;
import org.kuali.common.util.xml.jaxb.DropFalseAdapter;

@XmlRootElement(name = "log4j:configuration")
@XmlAccessorType(XmlAccessType.PROPERTY)
public final class Log4JContext {

	public static final boolean DEFAULT_RESET = false;
	public static final String DEFAULT_NAMESPACE = "http://jakarta.apache.org/log4j/";
	public static final Debug DEFAULT_DEBUG = Debug.DEFAULT_VALUE;
	public static final Threshold DEFAULT_THRESHOLD = Threshold.DEFAULT_VALUE;
	public static final List<Logger> NO_LOGGERS = Collections.<Logger> emptyList();
	public static final List<Appender> NO_APPENDERS = Collections.<Appender> emptyList();

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
	private final String debug;

	@XmlAttribute
	@XmlJavaTypeAdapter(ThresholdAdapter.class)
	private final String threshold;

	public boolean getReset() {
		return reset;
	}

	public String getDebug() {
		return debug;
	}

	public String getThreshold() {
		return threshold;
	}

	public List<Appender> getAppenders() {
		return Collections.unmodifiableList(appenders);
	}

	public List<Logger> getLoggers() {
		return Collections.unmodifiableList(loggers);
	}

	public Logger getRoot() {
		return root;
	}

	public String getNamespace() {
		return namespace;
	}

	public static class Builder {

		private List<Appender> appenders = NO_APPENDERS;
		private Logger root = Logger.getNoRootLogger();
		private String namespace = DEFAULT_NAMESPACE;
		private List<Logger> loggers = NO_LOGGERS;
		private boolean reset = DEFAULT_RESET;
		private Debug debug = DEFAULT_DEBUG;
		private Threshold threshold = DEFAULT_THRESHOLD;

		public Builder appenders(List<Appender> appenders) {
			this.appenders = appenders;
			return this;
		}

		public Builder namespace(String namespace) {
			this.namespace = namespace;
			return this;
		}

		public Builder appender(Appender appender) {
			this.appenders = CollectionUtils.singletonList(appender);
			return this;
		}

		public Builder root(Logger root) {
			this.root = root;
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

		public Log4JContext build() {
			Assert.noNulls(appenders, root, loggers);
			return new Log4JContext(this);
		}
	}

	private Log4JContext() {
		this(new Builder());
	}

	private Log4JContext(Builder builder) {
		this.appenders = new ArrayList<Appender>(builder.appenders);
		this.root = builder.root;
		this.loggers = new ArrayList<Logger>(builder.loggers);
		this.reset = builder.reset;
		this.debug = builder.debug.name().toLowerCase();
		this.threshold = builder.threshold.name().toLowerCase();
		this.namespace = builder.namespace;
	}

}
