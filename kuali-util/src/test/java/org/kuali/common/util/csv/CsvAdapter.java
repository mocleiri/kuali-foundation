package org.kuali.common.util.csv;

public abstract class CsvAdapter<T> {

	public abstract String format(T instance);

	public abstract T parse(String string);

}
