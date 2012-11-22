package org.kuali.common.jdbc;

import java.util.List;

public interface SqlService {

	SqlMetadata executeString(JdbcContext context, String sql);

	SqlMetadata executeStrings(JdbcContext context, List<String> sql);

	SqlMetadata executeLocation(JdbcContext context, String location);

	SqlMetadata executeLocations(JdbcContext context, List<String> locations);

	SqlMetadata getStringMetadata(SqlContext context, String sql);

	SqlMetadata getStringsMetadata(SqlContext context, List<String> sql);

	SqlMetadata getLocationMetadata(SqlContext context, String location);

	SqlMetadata getLocationsMetadata(SqlContext context, List<String> locations);

}
