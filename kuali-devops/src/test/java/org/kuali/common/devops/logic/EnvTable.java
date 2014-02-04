package org.kuali.common.devops.logic;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import org.kuali.common.devops.table.Label;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;

public enum EnvTable {

	NAME(Label.create(0, "name")), //
	URL(Label.create(1, "url")), //
	JAVA(Label.create(2, "java")), //
	SERVER(Label.create(3, "server")); //

	private final Label label;

	private EnvTable(Label label) {
		checkNotNull(label, "'label' cannot be null");
		this.label = label;
	}

	public Label getLabel() {
		return label;
	}

	public static List<Label> asList() {
		List<Label> labels = Lists.newArrayList();
		for (EnvTable ev : values()) {
			labels.add(ev.getLabel());
		}
		return ImmutableList.copyOf(labels);
	}
}
