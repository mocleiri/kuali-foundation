package org.kuali.common.util.spring.binder;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;
import org.springframework.format.number.CurrencyFormatter;
import org.springframework.format.number.NumberFormatter;
import org.springframework.format.number.PercentFormatter;

import com.google.common.collect.ImmutableList;

public final class NumberFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<NumberFormat> {

	@Override
	public Set<Class<?>> getFieldTypes() {
		return new HashSet<Class<?>>(ImmutableList.of(Short.class, Integer.class, Long.class, Float.class, Double.class, BigDecimal.class, BigInteger.class));
	}

	@Override
	public Printer<Number> getPrinter(NumberFormat annotation, Class<?> fieldType) {
		return configureFormatterFrom(annotation, fieldType);
	}

	@Override
	public Parser<Number> getParser(NumberFormat annotation, Class<?> fieldType) {
		return configureFormatterFrom(annotation, fieldType);
	}

	private Formatter<Number> configureFormatterFrom(NumberFormat annotation, Class<?> fieldType) {
		if (!annotation.pattern().isEmpty()) {
			return new NumberFormatter(annotation.pattern());
		} else {
			Style style = annotation.style();
			if (style == Style.PERCENT) {
				return new PercentFormatter();
			} else if (style == Style.CURRENCY) {
				return new CurrencyFormatter();
			} else {
				return new NumberFormatter();
			}
		}
	}
}