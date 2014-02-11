package org.kuali.common.devops.logic;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.kuali.common.devops.table.Label;

public enum EnvironmentTableColumns {

	NAME(create("#")), //
	URL(create("Url")), //
	APP(create("Application")), //
	VERSION(create("Version")), //
	BUILD_DATE(create("Build Date")), //
	PURPOSE(create("Purpose")), //
	SCM(create("SCM")), //
	DATABASE(create("Database")), //
	SCHEMA(create("Schema")), //
	JAVA(create("Java")), //
	SERVER(create("Server")), //
	TOMCAT(create("Tomcat")); //

	private final Label label;

	private EnvironmentTableColumns(Label label) {
		checkNotNull(label, "label");
		this.label = label;
	}

	private static int sequence = 0;

	private static synchronized final Label create(String text) {
		return Label.create(sequence++, text);
	}

	public Label getLabel() {
		return label;
	}

}
