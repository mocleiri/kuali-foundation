package org.kuali.common.impex.schema;

import java.sql.SQLException;
import java.util.List;

import org.kuali.common.impex.model.View;

public interface ViewFinder {

    List<View> findViews() throws SQLException;

}
