package org.kuali.common.util.spring.event;

import java.util.List;

import org.kuali.common.util.execute.Executable;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.SmartApplicationListener;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;

/**
 * Associate an executable with one or more application event's. If a supported application event is received, the executable is executed.
 */
public final class ExecutableApplicationListener implements SmartApplicationListener {

	private final Executable executable;
	private final int order;
	private final List<Class<?>> supportedSourceTypes;
	private final List<Class<? extends ApplicationEvent>> supportedEventTypes;

	@Override
	public void onApplicationEvent(ApplicationEvent event) {
		if (supportsEventType(event.getClass())) {
			executable.execute();
		}
	}

	@Override
	public int getOrder() {
		return order;
	}

	@Override
	public boolean supportsEventType(Class<? extends ApplicationEvent> eventType) {
		return supportedEventTypes.contains(eventType);
	}

	@Override
	public boolean supportsSourceType(Class<?> sourceType) {
		return supportedSourceTypes.contains(sourceType);
	}

	private ExecutableApplicationListener(Builder builder) {
		this.executable = builder.executable;
		this.order = builder.order;
		this.supportedSourceTypes = builder.supportedSourceTypes;
		this.supportedEventTypes = builder.supportedEventTypes;
	}

	public static Builder builder(Executable executable) {
		return new Builder(executable);
	}

	public static class Builder {

		// Required
		private final Executable executable;

		// Optional
		private int order = 0;
		private List<Class<?>> supportedSourceTypes = ImmutableList.of();
		private List<Class<? extends ApplicationEvent>> supportedEventTypes = ImmutableList.of();

		public Builder(Executable executable) {
			this.executable = executable;
		}

		public Builder order(int order) {
			this.order = order;
			return this;
		}

		public Builder supportedSourceTypes(List<Class<?>> supportedSourceTypes) {
			this.supportedSourceTypes = supportedSourceTypes;
			return this;
		}

		public Builder supportedEventTypes(List<Class<? extends ApplicationEvent>> supportedEventTypes) {
			this.supportedEventTypes = supportedEventTypes;
			return this;
		}

		public ExecutableApplicationListener build() {
			this.supportedEventTypes = ImmutableList.copyOf(supportedEventTypes);
			this.supportedSourceTypes = ImmutableList.copyOf(supportedSourceTypes);
			ExecutableApplicationListener instance = new ExecutableApplicationListener(this);
			validate(instance);
			return instance;
		}

		private void validate(ExecutableApplicationListener instance) {
			Preconditions.checkNotNull(executable, "executable cannot be null");
			Preconditions.checkNotNull(supportedSourceTypes, "supportedSourceTypes cannot be null");
			Preconditions.checkNotNull(supportedEventTypes, "supportedEventTypes cannot be null");
		}

		public int getOrder() {
			return order;
		}

		public void setOrder(int order) {
			this.order = order;
		}

		public List<Class<?>> getSupportedSourceTypes() {
			return supportedSourceTypes;
		}

		public void setSupportedSourceTypes(List<Class<?>> supportedSourceTypes) {
			this.supportedSourceTypes = supportedSourceTypes;
		}

		public List<Class<? extends ApplicationEvent>> getSupportedEventTypes() {
			return supportedEventTypes;
		}

		public void setSupportedEventTypes(List<Class<? extends ApplicationEvent>> supportedEventTypes) {
			this.supportedEventTypes = supportedEventTypes;
		}

		public Executable getExecutable() {
			return executable;
		}
	}

	public Executable getExecutable() {
		return executable;
	}

	public List<Class<?>> getSupportedSourceTypes() {
		return supportedSourceTypes;
	}

	public List<Class<? extends ApplicationEvent>> getSupportedEventTypes() {
		return supportedEventTypes;
	}

}
