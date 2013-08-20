package org.kuali.common.util.log.log4j.model;

import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;

@XmlRootElement(name = "log4j:configuration")
@XmlAccessorType(XmlAccessType.PROPERTY)
public final class Log4JContext {

	public static final boolean DEFAULT_RESET = false;
	public static final boolean DEFAULT_DEBUG = false;
	public static final Value DEFAULT_THRESHOLD = Value.DEFAULT_VALUE;
	public static final List<Logger> NO_LOGGERS = Collections.<Logger> emptyList();
	public static final List<Appender> NO_APPENDERS = Collections.<Appender> emptyList();

	@XmlElement(name = "appender")
	private final List<Appender> appenders;

	@XmlElement
	private final Logger root;

	@XmlElement(name = "logger")
	private final List<Logger> loggers;

	@XmlAttribute
	private final boolean reset;

	@XmlAttribute
	private final boolean debug;

	@XmlAttribute
	private final Value threshold;

	public boolean getReset() {
		return reset;
	}

	public boolean getDebug() {
		return debug;
	}

	public Value getThreshold() {
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

	public static class Builder {

		private List<Appender> appenders = NO_APPENDERS;
		private Logger root = Logger.getNoRootLogger();
		private List<Logger> loggers = NO_LOGGERS;
		private boolean reset = DEFAULT_RESET;
		private boolean debug = DEFAULT_DEBUG;
		private Value threshold = DEFAULT_THRESHOLD;

		public Builder() {
		}

		public Builder appenders(List<Appender> appenders) {
			this.appenders = appenders;
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

		public Builder debug(boolean debug) {
			this.debug = debug;
			return this;
		}

		public Builder debug(Value threshold) {
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
		this.appenders = builder.appenders;
		this.root = builder.root;
		this.loggers = builder.loggers;
		this.reset = builder.reset;
		this.debug = builder.debug;
		this.threshold = builder.threshold;
	}

}
