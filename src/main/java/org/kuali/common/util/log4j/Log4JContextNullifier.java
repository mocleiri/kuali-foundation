package org.kuali.common.util.log4j;

import org.apache.commons.lang3.ObjectUtils;
import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.nullify.Nullifier;

/**
 * @deprecated
 */
@Deprecated
public class Log4JContextNullifier implements Nullifier {

	org.kuali.common.util.log4j.model.Log4JContext context;

	public Log4JContextNullifier() {
		this(null);
	}

	public Log4JContextNullifier(org.kuali.common.util.log4j.model.Log4JContext context) {
		super();
		this.context = context;
	}

	@Override
	public void nullify() {

		Assert.notNull(context);

		nullify(context);
		nullify(context.getRoot());
		for (org.kuali.common.util.log4j.model.Logger logger : CollectionUtils.toEmptyList(context.getLoggers())) {
			nullify(logger);
		}

	}

	protected void nullify(org.kuali.common.util.log4j.model.Log4JContext context) {
		if (ObjectUtils.equals(org.kuali.common.util.log4j.model.Log4JContext.DEFAULT_RESET_VALUE, context.getReset())) {
			context.setReset(null);
		}

		if (ObjectUtils.equals(org.kuali.common.util.log4j.model.Log4JContext.DEFAULT_DEBUG_VALUE, context.getDebug())) {
			context.setDebug(null);
		}

		if (ObjectUtils.equals(org.kuali.common.util.log4j.model.Log4JContext.DEFAULT_THRESHOLD_VALUE, context.getThreshold())) {
			context.setThreshold(null);
		}
	}

	protected void nullify(org.kuali.common.util.log4j.model.Logger logger) {
		if (logger == null) {
			return;
		}
		if (ObjectUtils.equals(org.kuali.common.util.log4j.model.Logger.DEFAULT_ADDITIVITY_VALUE, logger.getAdditivity())) {
			logger.setAdditivity(null);
		}
		if (logger.getLevel() == null) {
			return;
		}
		if (ObjectUtils.equals(org.kuali.common.util.log4j.model.Level.DEFAULT_JAVA_CLASS, logger.getLevel().getJavaClass())) {
			logger.getLevel().setJavaClass(null);
		}
	}

	public org.kuali.common.util.log4j.model.Log4JContext getContext() {
		return context;
	}

	public void setContext(org.kuali.common.util.log4j.model.Log4JContext context) {
		this.context = context;
	}

}
