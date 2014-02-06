package org.kuali.common.util.log.log4j.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.nullify.NullUtils;
import org.kuali.common.util.xml.jaxb.adapter.OmitNoneStringAdapter;
import org.kuali.common.util.xml.jaxb.adapter.OmitTrueAdapter;

public class Logger {

	public static final List<Logger> EMPTY = Collections.<Logger> emptyList();
	public static final boolean DEFAULT_ADDITIVITY = true;
	public static final Logger DEFAULT = new Logger();

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitNoneStringAdapter.class)
	private final String name;

	@XmlElement(name = "appender-ref")
	private final List<AppenderRef> references;

	@XmlElement
	private final Level level;

	@XmlAttribute
	@XmlJavaTypeAdapter(OmitTrueAdapter.class)
	private final Boolean additivity;

	private Logger() {
		this(NullUtils.NONE, AppenderRef.EMPTY, Level.DEFAULT);
	}

	public Logger(String name, Level level) {
		this(name, AppenderRef.EMPTY, level, DEFAULT_ADDITIVITY);
	}

	public Logger(String name, List<AppenderRef> references) {
		this(name, references, Level.DEFAULT, DEFAULT_ADDITIVITY);
	}

	public Logger(String name, List<AppenderRef> references, Level level) {
		this(name, references, level, DEFAULT_ADDITIVITY);
	}

	public Logger(String name, List<AppenderRef> references, Level level, boolean additivity) {
		Assert.noBlanks(name);
		Assert.noNulls(references, level);
		this.name = name;
		this.references = new ArrayList<AppenderRef>(references);
		this.level = level;
		this.additivity = additivity;
	}

	public List<AppenderRef> getReferences() {
		return Collections.unmodifiableList(references);
	}

	public boolean getAdditivity() {
		return additivity;
	}

	public String getName() {
		return name;
	}

	public Level getLevel() {
		return level;
	}

	public static boolean isThresholdNull(Logger logger) {
		return Threshold.NULL.equals(logger.getLevel().getValue());
	}

	public static Logger getRootLogger(AppenderRef reference, Level level) {
		return getRootLogger(CollectionUtils.singletonList(reference), level);
	}

	public static Logger getRootLogger(List<AppenderRef> references, Level level) {
		return new Logger(NullUtils.NONE, references, level);
	}
}
