package org.kuali.common.liquibase.convert;

import org.kuali.common.util.Assert;

import com.google.common.base.Optional;

public class SqlFileContext {

	private final String path;
	private final Optional<String> dbms;
	private final Optional<String> encoding;
	private final Optional<String> endDelimiter;
	private final Optional<Boolean> relativeToChangelogFile;
	private final Optional<Boolean> splitStatements;
	private final Optional<String> sql;
	private final Optional<Boolean> stripComments;

	public static class Builder {

		// Required
		private final String path;

		// Optional
		private Optional<String> dbms = Optional.absent();
		private Optional<String> encoding = Optional.absent();
		private Optional<String> endDelimiter = Optional.of(";");
		private Optional<Boolean> relativeToChangelogFile = Optional.absent();
		private Optional<Boolean> splitStatements = Optional.absent();
		private Optional<String> sql = Optional.absent();
		private Optional<Boolean> stripComments = Optional.absent();

		public Builder(String path) {
			this.path = path;
		}

		public SqlFileContext build() {
			Assert.noBlanks(path);
			Assert.exists(path);
			return new SqlFileContext(this);
		}

	}

	private SqlFileContext(Builder builder) {
		this.dbms = builder.dbms;
		this.encoding = builder.encoding;
		this.endDelimiter = builder.endDelimiter;
		this.path = builder.path;
		this.relativeToChangelogFile = builder.relativeToChangelogFile;
		this.splitStatements = builder.splitStatements;
		this.sql = builder.sql;
		this.stripComments = builder.stripComments;
	}

	public String getPath() {
		return path;
	}

	public Optional<String> getDbms() {
		return dbms;
	}

	public Optional<String> getEncoding() {
		return encoding;
	}

	public Optional<String> getEndDelimiter() {
		return endDelimiter;
	}

	public Optional<Boolean> getRelativeToChangelogFile() {
		return relativeToChangelogFile;
	}

	public Optional<Boolean> getSplitStatements() {
		return splitStatements;
	}

	public Optional<String> getSql() {
		return sql;
	}

	public Optional<Boolean> getStripComments() {
		return stripComments;
	}

}
