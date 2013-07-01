package org.kuali.common.impex.schema;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.kuali.common.impex.model.View;
import org.kuali.common.util.StringFilter;

public interface ViewFinder {

	List<View> findViews(Connection connection, String schema, StringFilter nameFilter) throws SQLException;

}
