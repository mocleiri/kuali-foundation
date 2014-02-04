package org.kuali.common.devops.table;

import org.kuali.common.util.ObjectUtils;
import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

@IdiotProofImmutable
public final class Label implements Comparable<Label> {

	private final int sequence;
	private final String text;

	private Label(Builder builder) {
		this.sequence = builder.sequence;
		this.text = builder.text;
	}

	public static Label create(int sequence, String text) {
		return builder().sequence(sequence).text(text).build();
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<Label> {

		private int sequence;
		private String text;

		public Builder sequence(int sequence) {
			this.sequence = sequence;
			return this;
		}

		public Builder text(String text) {
			this.text = text;
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

	public String getText() {
		return text;
	}

	@Override
	public int compareTo(Label other) {
		return Double.compare(sequence, other.getSequence());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + sequence;
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (ObjectUtils.notEqual(this, other)) {
			return false;
		} else {
			Label column = (Label) other;
			return sequence == column.getSequence() && text.equals(column.getText());
		}
	}

}
