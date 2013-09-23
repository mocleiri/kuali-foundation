package org.kuali.common.liquibase;

import java.util.List;

import javax.sql.DataSource;

import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

import org.kuali.common.util.Assert;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.execute.Executable;

import com.google.common.collect.ImmutableList;

public class LiquibaseExecutable implements Executable {

	private final boolean skip;
	private final String changeLog;
	private final DataSource dataSource;
	private final List<String> contexts;

	@Override
	public void execute() {

		if (skip) {
			return;
		}

		SpringLiquibase springLiquibase = new SpringLiquibase();
		springLiquibase.setChangeLog(changeLog);
		springLiquibase.setDataSource(dataSource);
		springLiquibase.setContexts(CollectionUtils.asCSV(contexts));
		try {
			springLiquibase.afterPropertiesSet();
		} catch (LiquibaseException e) {
			throw new IllegalStateException("Unexpected Liquibase error", e);
		}

	}

	public static class Builder {

		// Required
		private final DataSource dataSource;
		private final String changeLog;

		// Optional
		private boolean skip = false;
		private List<String> contexts = ImmutableList.of();

		public Builder(DataSource dataSource, String changeLog) {
			this.dataSource = dataSource;
			this.changeLog = changeLog;
		}

		public Builder skip(boolean skip) {
			this.skip = skip;
			return this;
		}

		public Builder contexts(List<String> contexts) {
			this.contexts = contexts;
			return this;
		}

		public LiquibaseExecutable build() {
			Assert.noNulls(dataSource, contexts);
			Assert.noBlanks(changeLog);
			Assert.exists(changeLog, "[" + changeLog + "] does not exist");
			return new LiquibaseExecutable(this);
		}

	}

	private LiquibaseExecutable(Builder builder) {
		this.skip = builder.skip;
		this.changeLog = builder.changeLog;
		this.dataSource = builder.dataSource;
		this.contexts = ImmutableList.copyOf(builder.contexts);
	}

	public boolean isSkip() {
		return skip;
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
