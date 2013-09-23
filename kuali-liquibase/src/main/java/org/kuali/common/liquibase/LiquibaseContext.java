package org.kuali.common.liquibase;

import java.util.List;

import javax.sql.DataSource;

import org.kuali.common.util.Assert;

import com.google.common.collect.ImmutableList;

public class LiquibaseContext {

	private final String changeLog;
	private final DataSource dataSource;
	private final List<String> contexts;

	public static class Builder {

		// Required
		private final DataSource dataSource;
		private final String changeLog;

		// Optional
		private List<String> contexts = ImmutableList.of();

		public Builder(DataSource dataSource, String changeLog) {
			this.dataSource = dataSource;
			this.changeLog = changeLog;
		}

		public Builder contexts(List<String> contexts) {
			this.contexts = contexts;
			return this;
		}

		public LiquibaseContext build() {
			Assert.noNulls(dataSource, contexts);
			Assert.noBlanks(changeLog);
			Assert.exists(changeLog, "[" + changeLog + "] does not exist");
			return new LiquibaseContext(this);
		}

	}

	private LiquibaseContext(Builder builder) {
		this.changeLog = builder.changeLog;
		this.dataSource = builder.dataSource;
		this.contexts = ImmutableList.copyOf(builder.contexts);
	}

	public String getChangeLog() {
		return changeLog;
	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public List<String> getContexts() {
		return contexts;
	}

}
