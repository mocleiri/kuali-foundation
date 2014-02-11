package org.kuali.common.devops.logic;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.kuali.common.devops.table.Label;
import org.kuali.common.util.Counter;

public enum EnvironmentTableColumns {

	NAME(Label.create(Int.next(), "#")), //
	URL(Label.create(Int.next(), "Url")), //
	APP(Label.create(Int.next(), "Application")), //
	VERSION(Label.create(Int.next(), "Version")), //
	BUILD_DATE(Label.create(Int.next(), "Build Date")), //
	PURPOSE(Label.create(Int.next(), "Purpose")), //
	SCM(Label.create(Int.next(), "SCM")), //
	DATABASE(Label.create(Int.next(), "Database")), //
	SCHEMA(Label.create(Int.next(), "Schema")), //
	JAVA(Label.create(Int.next(), "Java")), //
	SERVER(Label.create(Int.next(), "Server")), //
	TOMCAT(Label.create(Int.next(), "Tomcat")); //

	private final Label label;

	private EnvironmentTableColumns(Label label) {
		checkNotNull(label, "label");
		this.label = label;
	}

	public Label getLabel() {
		return label;
	}

	private static class Int {
		private static final Counter COUNTER = new Counter();

		private static final int next() {
			return COUNTER.increment();
		}
	}

}
