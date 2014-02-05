package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkNotNull;

import org.kuali.common.devops.table.Label;

public enum EnvironmentTableColumns {

	NAME(Label.create(0, "env")), //
	URL(Label.create(1, "url")), //
	APP(Label.create(2, "application")), //
	SCM(Label.create(3, "scm")), //
	JAVA(Label.create(4, "java")), //
	SERVER(Label.create(5, "server")), //
	TOMCAT(Label.create(6, "tomcat")); //

	private final Label label;

	private EnvironmentTableColumns(Label label) {
		checkNotNull(label, "'label' cannot be null");
		this.label = label;
	}

	public Label getLabel() {
		return label;
	}

}
