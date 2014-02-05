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

		public int getSequence() {
			return sequence;
		}

		public void setSequence(int sequence) {
			this.sequence = sequence;
		}

		public String getText() {
			return text;
		}

		public void setText(String text) {
			this.text = text;
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
		return sequence > other.getSequence() ? 1 : sequence < other.getSequence() ? -1 : 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + text.hashCode();
		result = prime * result + sequence;
		return result;
	}

	@Override
	public boolean equals(Object other) {
		if (ObjectUtils.notEqual(this, other)) {
			return false;
		} else {
			Label label = (Label) other;
			return sequence == label.getSequence() && text.equals(label.getText());
		}
	}

	@Override
	public String toString() {
		return text;
	}

}
