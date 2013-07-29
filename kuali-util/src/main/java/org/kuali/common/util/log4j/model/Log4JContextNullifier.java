package org.kuali.common.util.log4j.model;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.nullify.Nullifier;

public class Log4JContextNullifier implements Nullifier {

	Log4JContext context;

	public Log4JContextNullifier() {
		this(null);
	}

	public Log4JContextNullifier(Log4JContext context) {
		super();
		this.context = context;
	}

	@Override
	public void nullify() {

		Assert.notNull(context);

		nullify(context);
		nullify(context.getRoot());
		for (Log4JLogger logger : CollectionUtils.toEmptyList(context.getLoggers())) {
			nullify(logger);
		}

	}

	protected void nullify(Log4JContext context) {
		if (Log4JContext.DEFAULT_RESET_VALUE.equals(context.getReset())) {
			context.setReset(null);
		}

		if (Log4JContext.DEFAULT_DEBUG_VALUE.equals(context.getDebug())) {
			context.setDebug(null);
		}

		if (Log4JContext.DEFAULT_THRESHOLD_VALUE.equals(context.getThreshold())) {
			context.setThreshold(null);
		}
	}

	protected void nullify(Log4JLogger logger) {
		if (logger == null) {
			return;
		}
		if (Log4JLogger.DEFAULT_ADDITIVITY_VALUE.equals(logger.getAdditivity())) {
			logger.setAdditivity(null);
		}
		if (logger.getLevel() == null) {
			return;
		}
		if (Log4JLevel.DEFAULT_JAVA_CLASS.equals(logger.getLevel().getJavaClass())) {
			logger.getLevel().setJavaClass(null);
		}
	}

	public Log4JContext getContext() {
		return context;
	}

	public void setContext(Log4JContext context) {
		this.context = context;
	}

}
