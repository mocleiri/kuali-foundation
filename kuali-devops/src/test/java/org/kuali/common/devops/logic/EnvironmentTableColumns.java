package org.kuali.common.devops.logic;

import static org.kuali.common.devops.table.Label.create;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.kuali.common.devops.table.Label;
import org.kuali.common.util.Counter;

public enum EnvironmentTableColumns {

	NAME(create(Int.next(), "#")), //
	URL(create(Int.next(), "Url")), //
	APP(create(Int.next(), "Application")), //
	VERSION(create(Int.next(), "Version")), //
	BUILD_DATE(create(Int.next(), "Build Date")), //
	PURPOSE(create(Int.next(), "Purpose")), //
	SCM(create(Int.next(), "SCM")), //
	DATABASE(create(Int.next(), "Database")), //
	SCHEMA(create(Int.next(), "Schema")), //
	JAVA(create(Int.next(), "Java")), //
	SERVER(create(Int.next(), "Server")), //
	TOMCAT(create(Int.next(), "Tomcat")); //

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
			COUNTER.increment();
			return COUNTER.getValue();
		}
	}

}
