/**
 * Copyright 2010-2013 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
