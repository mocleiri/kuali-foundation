package org.kuali.common.jdbc.reader.spring;

import java.util.List;

import org.kuali.common.jdbc.reader.SqlReader;
import org.kuali.common.jdbc.reader.DefaultSqlReader;
import org.kuali.common.jdbc.reader.model.Comments;
import org.kuali.common.jdbc.reader.model.Delimiter;
import org.kuali.common.jdbc.reader.model.DelimiterMode;
import org.kuali.common.jdbc.reader.model.LineSeparator;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.spring.SpringUtils;
import org.kuali.common.util.spring.env.EnvironmentService;
import org.kuali.common.util.spring.service.SpringServiceConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({ SpringServiceConfig.class })
public class SqlReaderConfig {

	private static final String DELIMITER_KEY = "sql.delimiter";
	private static final String DELIMITER_MODE_KEY = "sql.delimiter.mode";
	private static final String LINE_SEPARATOR_KEY = "sql.line.separator";
	private static final String COMMENTS_IGNORE_KEY = "sql.comments.ignore";
	private static final String COMMENTS_TOKENS_KEY = "sql.comments.tokens";
	private static final String TRIM_KEY = "sql.trim";

	@Autowired
	EnvironmentService env;

	@Bean
	public SqlReader sqlReader() {
		boolean trim = env.getBoolean(TRIM_KEY, DefaultSqlReader.DEFAULT_TRIM);
		Comments comments = sqlComments();
		LineSeparator separator = sqlLineSeparator();
		Delimiter delimiter = sqlDelimiter();
		return new DefaultSqlReader(delimiter, separator, trim, comments);
	}

	@Bean
	public Comments sqlComments() {
		boolean ignore = env.getBoolean(COMMENTS_IGNORE_KEY, Comments.DEFAULT_IGNORE);
		List<String> tokens = SpringUtils.getNoneSensitiveListFromCSV(env, COMMENTS_TOKENS_KEY, CollectionUtils.getCSV(Comments.DEFAULT_TOKENS));
		return new Comments(ignore, tokens);
	}

	@Bean
	public LineSeparator sqlLineSeparator() {
		String value = env.getString(LINE_SEPARATOR_KEY, LineSeparator.DEFAULT_VALUE.name());
		return LineSeparator.valueOf(value.toUpperCase());
	}

	@Bean
	public Delimiter sqlDelimiter() {
		String delimiter = env.getString(DELIMITER_KEY, Delimiter.DEFAULT_VALUE);
		String modeString = env.getString(DELIMITER_MODE_KEY, DelimiterMode.DEFAULT_VALUE.name());
		DelimiterMode mode = DelimiterMode.valueOf(modeString.toUpperCase());
		return new Delimiter(delimiter, mode);
	}

}
