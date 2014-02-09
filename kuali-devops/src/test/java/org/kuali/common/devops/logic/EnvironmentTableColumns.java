package org.kuali.common.devops.logic;

import static org.kuali.common.util.base.Precondition.checkNotNull;

import org.kuali.common.devops.table.Label;

public enum EnvironmentTableColumns {

	NAME(Label.create(0, "Env")), //
	URL(Label.create(1, "Url")), //
	APP(Label.create(2, "Application")), //
	SCM(Label.create(3, "SCM")), //
	JAVA(Label.create(4, "Java")), //
	SERVER(Label.create(5, "Server")), //
	TOMCAT(Label.create(6, "Tomcat")); //

	private final Label label;

	private EnvironmentTableColumns(Label label) {
		checkNotNull(label, "label");
		this.label = label;
	}

	public Label getLabel() {
		return label;
	}

}
