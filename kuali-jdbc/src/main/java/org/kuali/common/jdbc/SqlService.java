package org.kuali.common.jdbc;

import java.util.List;

public interface SqlService {

	SqlMetadata execute(JdbcContext context, String sql);

	SqlMetadata execute(JdbcContext context, List<String> sql);

	SqlMetadata executeLocation(JdbcContext context, String location);

	SqlMetadata executeLocations(JdbcContext context, List<String> locations);

	SqlMetadata getMetadata(SqlContext context, String sql);

	SqlMetadata getMetadata(SqlContext context, List<String> sql);

	SqlMetadata getLocationMetadata(SqlContext context, String location);

	SqlMetadata getLocationsMetadata(SqlContext context, List<String> locations);

}
