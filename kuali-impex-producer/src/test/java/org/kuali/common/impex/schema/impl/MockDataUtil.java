/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.schema.impl;

import java.util.Collections;

import org.kuali.common.impex.model.Column;
import org.kuali.common.impex.model.DataType;
import org.kuali.common.impex.model.DataTypeSize;
import org.kuali.common.impex.model.ForeignKey;
import org.kuali.common.impex.model.ForeignKeyConstraintType;
import org.kuali.common.impex.model.Table;
import org.kuali.common.impex.model.UniqueConstraint;
import org.kuali.common.impex.model.View;

public class MockDataUtil {

	public static Table buildSimpleTable() {

		Table result = new Table("FOO_T");

		// id column
		Column idCol = new Column("ID", DataType.STRING);
		idCol.setSize(new DataTypeSize(36));
		idCol.setPrimaryKey(true);

		result.getColumns().add(idCol);

		// createtime column
		Column timeCol = new Column("CREATETIME", DataType.TIMESTAMP);
		timeCol.setNullable(false);

		result.getColumns().add(timeCol);

		// count column
		Column countCol = new Column("FOO_COUNT", DataType.FLOAT);
		countCol.setSize(new DataTypeSize(10));

		result.getColumns().add(countCol);

		// name column
		Column nameCol = new Column("NAME", DataType.STRING);
		nameCol.setSize(new DataTypeSize(255));

		result.getColumns().add(nameCol);

		// unique constraint on name
		UniqueConstraint nameIndex = new UniqueConstraint(Collections.singletonList(nameCol.getName()), "FOO_U1_NAME");

		result.getUniqueConstraints().add(nameIndex);

		return result;
	}

	public static View buildSimpleView() {

		String viewSql = "SELECT ID, NAME FROM FOO_T\n" + "WHERE NAME LIKE 'TEST%'";

		return new View("TEST_V1", viewSql);
	}

	public static ForeignKey buildSimpleForeignKey() {
		ForeignKey fk = new ForeignKey("FOO_FK_1", "FOO_T", "BAR_T");

		fk.setOnDelete(ForeignKeyConstraintType.CASCADE);
		fk.setLocalColumnNames(Collections.singletonList("BAR_ID"));
		fk.setForeignColumnNames(Collections.singletonList("ID"));

		return fk;
	}
}
