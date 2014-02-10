package org.kuali.common.devops.logic;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.kuali.common.devops.table.Label;

public enum EnvironmentTableColumns {

	NAME(Label.create(0, "#")), //
	URL(Label.create(1, "Url")), //
	APP(Label.create(2, "Application")), //
	VERSION(Label.create(3, "Version")), //
	BUILD_DATE(Label.create(4, "Build Date")), //
	SCM(Label.create(5, "SCM")), //
	DATABASE(Label.create(6, "Database")), //
	SCHEMA(Label.create(7, "Schema")), //
	JAVA(Label.create(8, "Java")), //
	SERVER(Label.create(9, "Server")), //
	TOMCAT(Label.create(10, "Tomcat")); //

	private final Label label;

	private EnvironmentTableColumns(Label label) {
		checkNotNull(label, "label");
		this.label = label;
	}

	public Label getLabel() {
		return label;
	}

}
