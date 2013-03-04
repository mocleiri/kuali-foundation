package org.kuali.common.util.service;

import static org.kuali.common.util.service.MySqlDumpService.PREFIX_50013_DEFINER;
import static org.kuali.common.util.service.MySqlDumpService.SUFFIX_SQL_SECURITY_DEFINER;

import java.util.ArrayList;
import java.util.List;

import org.kuali.common.util.ignore.Ignore;
import org.kuali.common.util.ignore.PrefixSuffixIgnorer;
import org.kuali.common.util.ignore.StartsWithIgnorer;

public class MySqlDumpUtils {

	public static List<Ignore> getDefaultIgnorers() {
		List<Ignore> ignorers = new ArrayList<Ignore>();
		ignorers.add(new PrefixSuffixIgnorer(PREFIX_50013_DEFINER, SUFFIX_SQL_SECURITY_DEFINER));
		ignorers.add(new StartsWithIgnorer(MySqlDumpService.PREFIX_DUMP_COMPLETED));
		ignorers.add(new StartsWithIgnorer(MySqlDumpService.PREFIX_MYSQL_DUMP));
		ignorers.add(new StartsWithIgnorer(MySqlDumpService.PREFIX_MYSQL_HOST));
		ignorers.add(new StartsWithIgnorer(MySqlDumpService.PREFIX_SERVER_VERSION));
		return ignorers;
	}

}
