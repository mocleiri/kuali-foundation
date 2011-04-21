package org.kuali.spring.util.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ObjectUtils;

public class DefaultVisitListener implements VisitListener {
	final Logger logger = LoggerFactory.getLogger(DefaultVisitListener.class);

	@Override
	public void valueResolved(ValueResolutionEvent event) {
		if (ObjectUtils.nullSafeEquals(event.getOldValue(), event.getNewValue())) {
			// Nothing changed
			logger.trace("Resolved value is the same as the original value");
		} else {
			// The property was updated
			logger.trace("Value updated: [{}]->[{}]", event.getOldValue(), event.getNewValue());
		}
	}

	@Override
	public void beforeVisit(BeanVisitEvent event) {
		logger.info("Visiting {}", event.getBeanDefinition());
	}

	@Override
	public void afterVisit(BeanVisitEvent event) {
		logger.trace("Visit completed. {}", event.getBeanDefinition());
	}

	@Override
	public void beforeVisit(PropertyValueVisitEvent event) {
		logger.trace("Visiting {}", event.getPropertyValue());
	}

	@Override
	public void afterVisit(PropertyValueVisitEvent event) {
		if (ObjectUtils.nullSafeEquals(event.getOldValue(), event.getNewValue())) {
			logger.trace("Value for property '{}' was left unchanged", event.getPropertyValue().getName());
		} else {
			logger.info(event.getPropertyValue() + " updated [{}]->[{}]", event.getOldValue(), event.getNewValue());
		}
	}
}
