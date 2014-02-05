package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkNotNull;

import org.kuali.common.devops.table.Label;

public enum EnvironmentTableColumns {

	NAME(Label.create(0, "name")), //
	URL(Label.create(1, "url")), //
	JAVA(Label.create(3, "java")), //
	SERVER(Label.create(4, "server")), //
	TOMCAT(Label.create(5, "tomcat")), //
	APP(Label.create(2, "application")); //

	private final Label label;

	private EnvironmentTableColumns(Label label) {
		checkNotNull(label, "'label' cannot be null");
		this.label = label;
	}

	public Label getLabel() {
		return label;
	}

}
