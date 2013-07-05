package org.kuali.common.impex.model.util;

import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.Schema;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.TypeSize;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.nullify.Nullify;
import org.springframework.util.Assert;

/**
 * Null out values in a <code>schema</code> that are not needed when converting it to XML
 */
public class SchemaNullifier implements Nullify {

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
		}
	}

	protected void nullify(Column column) {
		// Not necessary when persisting to XML because every column tag is nested inside a table tag anyway
		column.setTable(null);

		if (Column.DEFAULT_NULLABLE_VALUE.equals(column.isNullable())) {
			column.setNullable(null);
		}

		if (Column.DEFAULT_PRIMARY_KEY_VALUE.equals(column.isPrimaryKey())) {
			column.setPrimaryKey(null);
		}

		TypeSize typeSize = column.getTypeSize();
		if (typeSize != null && TypeSize.DEFAULT_SCALE_SET.equals(typeSize.isScaleSet())) {
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
