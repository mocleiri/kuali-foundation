package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkNotNull;

import org.kuali.common.devops.table.Label;

public enum EnvTable {

	NAME(Label.create(0, "name")), //
	FQDN(Label.create(1, "fqdn")), //
	JAVA(Label.create(2, "java")); //

	private final Label column;

	private EnvTable(Label column) {
		checkNotNull(column, "'column' cannot be null");
		this.column = column;
	}

	public Label getColumn() {
		return column;
	}

}
