package org.kuali.common.util.property.processor;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.kuali.common.util.Assert;
import org.kuali.common.util.ListUtils;

public class ProcessorsProcessor implements PropertyProcessor {

	public ProcessorsProcessor(PropertyProcessor... processors) {
		this(Arrays.asList(processors));
	}

	public ProcessorsProcessor(List<PropertyProcessor> processors) {
		Assert.noNulls(processors);
		this.processors = ListUtils.newImmutableArrayList(processors);
	}

	private final List<PropertyProcessor> processors;

	@Override
	public void process(Properties properties) {
		for (PropertyProcessor processor : processors) {
			processor.process(properties);
		}
	}

	public List<PropertyProcessor> getProcessors() {
		return processors;
	}

}
