package org.kuali.core.db.torque.service;

import java.sql.DatabaseMetaData;
import java.sql.SQLException;

import org.kuali.core.db.torque.pojo.DatabaseContext;
import org.kuali.core.db.torque.pojo.TableContext;

public interface ImpexService {

	DatabaseContext getDatabaseObjectLists(ImpexContext context) throws SQLException;

	void fillInMetaData(DatabaseContext database, ImpexContext context) throws SQLException;

	void fillInMetaData(TableContext table, ImpexContext context, DatabaseMetaData metaData) throws SQLException;
}
