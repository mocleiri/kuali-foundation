package org.kuali.common.devops.logic;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.kuali.common.devops.table.Label;

public enum EnvironmentTableColumns {

	NAME(Label.create(Column.sequence++, "#")), //
	URL(Label.create(Column.sequence++, "Url")), //
	APP(Label.create(Column.sequence++, "Application")), //
	VERSION(Label.create(Column.sequence++, "Version")), //
	BUILD_DATE(Label.create(Column.sequence++, "Build Date")), //
	PURPOSE(Label.create(Column.sequence++, "Purpose")), //
	SCM(Label.create(Column.sequence++, "SCM")), //
	DATABASE(Label.create(Column.sequence++, "Database")), //
	SCHEMA(Label.create(Column.sequence++, "Schema")), //
	JAVA(Label.create(Column.sequence++, "Java")), //
	SERVER(Label.create(Column.sequence++, "Server")), //
	TOMCAT(Label.create(Column.sequence++, "Tomcat")); //

	private final Label label;

	private EnvironmentTableColumns(Label label) {
		checkNotNull(label, "label");
		this.label = label;
	}

	public Label getLabel() {
		return label;
	}

	private static class Column {
		private static int sequence = 0;
	}

}
