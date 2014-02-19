package org.kuali.common.util.spring.convert;

import static com.google.common.base.Optional.absent;
import static org.kuali.common.util.log.Loggers.newLogger;

import java.util.Set;

import org.slf4j.Logger;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableSet;

public class OptionalConverter implements GenericConverter {

	private static final ConvertiblePair PAIR = new ConvertiblePair(String.class, Optional.class);
	private static final Set<ConvertiblePair> TYPES = ImmutableSet.of(PAIR);

	private static final Logger logger = newLogger();

	@Override
	public Set<ConvertiblePair> getConvertibleTypes() {
		return TYPES;
	}

	@Override
	public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
		logger.info(String.format("    source: [%s]", source.getClass().getCanonicalName()));
		logger.info(String.format("sourceType: [%s]", sourceType.getClass().getCanonicalName()));
		logger.info(String.format("targetType: [%s]", targetType.getClass().getCanonicalName()));
		return absent();
	}

}
