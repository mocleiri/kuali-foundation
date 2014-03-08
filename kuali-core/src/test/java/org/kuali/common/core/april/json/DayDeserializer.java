package org.kuali.common.core.april.json;

import static org.kuali.common.util.base.Exceptions.illegalArgument;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DayDeserializer extends JsonDeserializer<Long> {

	private final String format = "yyyy-M-dd";
	private final int year = 2014;
	private final int month = 3;

	@Override
	public Long deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		try {
			SimpleDateFormat parser = new SimpleDateFormat(format);
			String date = year + "-" + month + "-" + jp.getText();
			return parser.parse(date).getTime();
		} catch (ParseException e) {
			throw illegalArgument(e);
		}
	}

}
