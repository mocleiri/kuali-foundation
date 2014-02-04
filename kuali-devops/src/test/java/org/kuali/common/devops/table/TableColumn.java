package org.kuali.common.devops.table;

import org.kuali.common.util.ObjectUtils;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class TableColumn implements Comparable<Label> {

	private final int sequence;
	private final String label;

	private TableColumn(Builder builder) {
		this.sequence = builder.sequence;
		this.label = builder.label;
	}

	public static Label create(int sequence, String label) {
		return builder().sequence(sequence).label(label).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Label> {

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
		public Label getInstance() {
			return new Label(this);
		}

	}

	public int getSequence() {
		return sequence;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public int compareTo(Label other) {
		return Double.compare(sequence, other.getSequence());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + sequence;
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (ObjectUtils.notEqual(this, other)) {
			return false;
		} else {
			Label column = (Label) other;
			return sequence == column.getSequence() && label.equals(column.getText());
		}
	}

}
