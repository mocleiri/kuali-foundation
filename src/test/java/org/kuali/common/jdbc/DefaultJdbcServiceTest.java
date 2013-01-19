package org.kuali.common.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.kuali.common.util.FormatUtils;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultJdbcServiceTest {

	private static final Logger logger = LoggerFactory.getLogger(DefaultJdbcServiceTest.class);

	@Test
	public void test() {
		try {
			SqlReader reader = new DefaultSqlReader();
			JdbcService service = new DefaultJdbcService();
			String encoding = "UTF-8";

			String tableList = "classpath:sql/oracle/rice-impex-master-tables.txt";
			List<String> tables = LocationUtils.getLocations(tableList);
			List<String> locations = getLocations(tables);
			List<SqlMetaData> smdl = service.getMetaData(reader, locations, encoding);

			long count = 0;
			long size = 0;
			for (int i = 0; i < locations.size(); i++) {
				SqlMetaData smd = smdl.get(i);
				String location = locations.get(i);
				count += smd.getCount();
				size += smd.getSize();
				String sz = FormatUtils.getSize(smd.getSize());
				String ct = FormatUtils.getCount(smd.getCount());
				logger.info(location + " - " + sz + ", " + ct);
			}

			String c = FormatUtils.getCount(count);
			String s = FormatUtils.getSize(size);

			logger.info("Count: {} Size: {}", c, s);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected List<String> getLocations(List<String> tables) {
		List<String> locations = new ArrayList<String>();
		for (String table : tables) {
			String location = "classpath:sql/oracle/" + table + ".sql";
			locations.add(location);
		}
		return locations;
	}

}
