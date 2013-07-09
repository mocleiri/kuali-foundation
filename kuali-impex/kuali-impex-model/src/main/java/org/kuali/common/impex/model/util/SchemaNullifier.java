package org.kuali.common.impex.model.util;

import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.Index;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.Size;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.nullify.Nullifier;
import org.springframework.util.Assert;

/**
 * Null out values in a <code>schema</code> that are not needed when converting it to XML
 */
public class SchemaNullifier implements Nullifier {

	Schema schema;

	public SchemaNullifier() {
		this(null);
	}

	public SchemaNullifier(Schema schema) {
		super();
		this.schema = schema;
	}

	@Override
	public void nullify() {
		Assert.notNull(schema, "schema is null");
		for (Table table : CollectionUtils.toEmptyList(schema.getTables())) {
			for (Column column : CollectionUtils.toEmptyList(table.getColumns())) {
				nullify(column);
			}
			for (Index index : CollectionUtils.toEmptyList(table.getIndices())) {
				nullify(index);
			}
		}
	}

	protected void nullify(Index index) {
		if (Index.DEFAULT_UNIQUE_VALUE.equals(index.isUnique())) {
			index.setUnique(null);
		}
	}

	protected void nullify(Column column) {
		if (Column.DEFAULT_NULLABLE_VALUE.equals(column.isNullable())) {
			column.setNullable(null);
		}

		if (Column.DEFAULT_PRIMARY_KEY_VALUE.equals(column.isPrimaryKey())) {
			column.setPrimaryKey(null);
		}

		Size typeSize = column.getSize();
		if (typeSize != null && Size.DEFAULT_SCALE_SET.equals(typeSize.isScaleSet())) {
			typeSize.setScaleSet(null);
		}
	}

	public Schema getSchema() {
		return schema;
	}

	public void setSchema(Schema schema) {
		this.schema = schema;
	}

}
