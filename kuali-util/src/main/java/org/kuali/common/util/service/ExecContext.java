/**
 * Copyright 2010-2012 The Kuali Foundation
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

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.codehaus.plexus.util.cli.StreamConsumer;

public interface ExecContext {

	boolean isAddSystemEnvironment();

	String getExecutable();

	List<String> getArgs();

	InputStream getInput();

	int getTimeoutInSeconds();

	File getWorkingDirectory();

	StreamConsumer getStandardOutConsumer();

	StreamConsumer getStandardErrConsumer();

}
