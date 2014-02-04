package org.kuali.common.devops.table;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class TableColumn {

	private final int sequence;
	private final String label;

	private TableColumn(Builder builder) {
		this.sequence = builder.sequence;
		this.label = builder.label;
	}

	public static TableColumn create(int sequence, String label) {
		return builder().sequence(sequence).label(label).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<TableColumn> {

		private int sequence;
		private String label;

		public Builder sequence(int sequence) {
			this.sequence = sequence;
			return this;
		}

		public Builder label(String label) {
			this.label = label;
			return this;
		}

		@Override
		public TableColumn getInstance() {
			return new TableColumn(this);
		}

	}

	public int getSequence() {
		return sequence;
	}

	public String getLabel() {
		return label;
	}

}
