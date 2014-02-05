package org.kuali.common.devops.table;

import org.kuali.common.util.build.ValidatingBuilder;
import org.kuali.common.util.validate.IdiotProofImmutable;

import com.google.common.base.Optional;

@IdiotProofImmutable
public final class TableContext {

	private final int indent;
	private final boolean columnLabels;
	private final boolean rowLabels;
	private final Optional<Integer> border;

	private TableContext(Builder builder) {
		this.indent = builder.indent;
		this.columnLabels = builder.columnLabels;
		this.rowLabels = builder.rowLabels;
		this.border = builder.border;
	}

	public static Builder builder() {
		return new Builder();
	}

	public static class Builder extends ValidatingBuilder<TableContext> {

		private int indent = 0;
		private boolean columnLabels = true;
		private boolean rowLabels = false;
		private Optional<Integer> border = Optional.of(1);

		public Builder indent(int indent) {
			this.indent = indent;
			return this;
		}

		public Builder rowLabels(boolean rowLabels) {
			this.rowLabels = rowLabels;
			return this;
		}

		public Builder columnLabels(boolean columnLabels) {
			this.columnLabels = columnLabels;
			return this;
		}

		public Builder border(Optional<Integer> border) {
			this.border = border;
			return this;
		}

		public Builder border(boolean border) {
			if (border) {
				return border(1);
			} else {
				return border(Optional.<Integer> absent());
			}
		}

		public Builder border(int border) {
			return border(Optional.of(border));
		}

		@Override
		public TableContext getInstance() {
			return new TableContext(this);
		}

	}

	public int getIndent() {
		return indent;
	}

	public boolean isColumnLabels() {
		return columnLabels;
	}

	public Optional<Integer> getBorder() {
		return border;
	}

	public boolean isRowLabels() {
		return rowLabels;
	}

}
