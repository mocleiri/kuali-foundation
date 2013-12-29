package org.kuali.common.util.spring.event;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import org.kuali.common.util.execute.Executable;
import org.kuali.common.util.log.LoggerUtils;
import org.slf4j.Logger;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;

/**
 * <p>
 * Associate an executable with one type of Spring framework application event.
 * </p>
 * 
 * <p>
 * If the application event gets fired {@code onApplicationEvent} invokes the executable.
 * </p>
 */
public final class ExecutableApplicationEventListener<T extends ApplicationEvent> implements SmartApplicationListener {

	private static final Logger logger = LoggerUtils.make();

	private final Executable executable;
	private final int order;
	private final Class<T> supportedEventType;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		checkArgument(supportedEventType == event.getClass(), "Event type [%s] should not be getting passed to this listener", event.getClass().getCanonicalName());
		logger.info("Received event: [{}]", event.getClass().getCanonicalName());
		executable.execute();
	}

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return supportedEventType == eventType;
	}

	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		return true;
	}

	private ExecutableApplicationEventListener(Builder<T> builder) {
		this.executable = builder.executable;
		this.order = builder.order;
		this.supportedEventType = builder.supportedEventType;
	}

	public static <T extends ApplicationEvent> Builder<T> builder(Executable executable, Class<T> supportedEventType) {
		return new Builder<T>(executable, supportedEventType);
	}

	public static class Builder<T extends ApplicationEvent> {

		// Required
		private final Executable executable;
		private final Class<T> supportedEventType;

		// Optional
		private int order = 0; // Lower values mean higher priority, higher values mean lower priority

		public Builder(Executable executable, Class<T> supportedEventType) {
			this.executable = executable;
			this.supportedEventType = supportedEventType;
		}

		public ExecutableApplicationEventListener<T> build() {
			ExecutableApplicationEventListener<T> instance = new ExecutableApplicationEventListener<T>(this);
			validate(instance);
			return instance;
		}

		private void validate(ExecutableApplicationEventListener<T> instance) {
			checkNotNull(instance.getExecutable(), "'executable' cannot be null");
			checkNotNull(instance.getSupportedEventType(), "'supportedEventType' cannot be null");
		}

		public Builder<T> order(int order) {
			this.order = order;
			return this;
		}

		public int getOrder() {
			return order;
		}

		public void setOrder(int order) {
			this.order = order;
		}

		public Executable getExecutable() {
			return executable;
		}
	}

	public Executable getExecutable() {
		return executable;
	}

	public Class<T> getSupportedEventType() {
		return supportedEventType;
	}

}
