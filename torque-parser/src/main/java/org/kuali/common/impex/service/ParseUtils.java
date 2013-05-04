package org.kuali.common.impex.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.torque.engine.EngineException;
import org.apache.torque.engine.database.model.Column;
import org.apache.torque.engine.database.model.SchemaType;
import org.apache.torque.engine.database.model.Table;
import org.kuali.common.impex.KualiDatabase;
import org.kuali.core.db.torque.KualiXmlToAppData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseUtils {

	private static final Logger logger = LoggerFactory.getLogger(ParseUtils.class);

	private static final String QUOTE = "\"";
	private static final String SPLIT_TOKEN = QUOTE + "," + QUOTE;
	private static final SchemaType[] COLUMN_DATE_TYPES = { SchemaType.DATE, SchemaType.TIMESTAMP };

	public static KualiDatabase getDatabase(String vendor, String location) {
		KualiXmlToAppData parser = new KualiXmlToAppData(vendor);
		logger.debug("vendor={}", vendor);
		try {
			return parser.parseResource(location);
		} catch (EngineException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * Split the line up into individual values and remove any .mpx related formatting
	 */
	public static String[] getOriginalValues(String line) {
		// Remove trailing/leading quotes
		String trimmed = trimQuotes(line);
		// Split the line up into individual values
		String[] values = getValues(trimmed);
		// Convert ${mpx.lf} -> \n
		for (int i = 0; i < values.length; i++) {
			values[i] = parse(values[i]);
		}
		// These are the original string values with all of the .mpx related formatting removed
		return values;
	}

	public static String[] getValues(String line) {
		return StringUtils.splitByWholeSeparator(line, SPLIT_TOKEN);
	}

	/**
	 * Remove leading and trailing quotes (if any)
	 */
	public static String trimQuotes(String line) {
		if (StringUtils.startsWith(line, QUOTE)) {
			line = StringUtils.substring(line, QUOTE.length());
		}
		int length = line.length();
		if (StringUtils.endsWith(line, QUOTE)) {
			line = StringUtils.substring(line, 0, length - QUOTE.length());
		}
		return line;
	}

	public static boolean isHeaderLine(String line) {
		return !StringUtils.isBlank(line) && !StringUtils.startsWith(line, QUOTE);
	}

	public static String parse(String s) {
		if (StringUtils.equals(s, "${mpx.null}")) {
			return null;
		}
		String converted = StringUtils.replace(s, "${mpx.cr}", "\r");
		converted = StringUtils.replace(converted, "${mpx.lf}", "\n");
		converted = StringUtils.replace(converted, "${mpx.quote}", "\"");
		return converted;
	}

	public static String format(String s) {
		if (s == null) {
			return "${mpx.null}";
		}
		String converted = StringUtils.replace(s, "\r", "${mpx.cr}");
		converted = StringUtils.replace(converted, "\n", "${mpx.lf}");
		converted = StringUtils.replace(converted, "\"", "${mpx.quote}");
		return converted;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Gets the parameterized version of the columns list from a @Table
	 *
	 * @return the List&lt;Column&gt; of columns from the table
	 */
	public static List<Column> getColumns(Table table) {
		return table.getColumns();
	}

	public static boolean isColumnDateType(Column column) {
		SchemaType columnType = getColumnType(column);

		boolean result = false;
		for (SchemaType dateType : COLUMN_DATE_TYPES) {
			if (dateType.equals(columnType)) {
				result = true;
				break;
			}
		}
		return result;
	}

	public static SchemaType getColumnType(Column column) {
		return SchemaType.getEnum((String) column.getTorqueType());
	}

}
