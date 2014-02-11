package org.kuali.common.devops.logic;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.kuali.common.devops.table.Label;

public enum EnvironmentTableColumns {

	NAME(Label.create(Column.value++, "#")), //
	URL(Label.create(Column.value++, "Url")), //
	APP(Label.create(Column.value++, "Application")), //
	VERSION(Label.create(Column.value++, "Version")), //
	BUILD_DATE(Label.create(Column.value++, "Build Date")), //
	PURPOSE(Label.create(Column.value++, "Purpose")), //
	SCM(Label.create(Column.value++, "SCM")), //
	DATABASE(Label.create(Column.value++, "Database")), //
	SCHEMA(Label.create(Column.value++, "Schema")), //
	JAVA(Label.create(Column.value++, "Java")), //
	SERVER(Label.create(Column.value++, "Server")), //
	TOMCAT(Label.create(Column.value++, "Tomcat")); //

	private final Label label;

	private EnvironmentTableColumns(Label label) {
		checkNotNull(label, "label");
		this.label = label;
	}

	public Label getLabel() {
		return label;
	}

	private static class Column {
		private static int value = 0;
	}

}
