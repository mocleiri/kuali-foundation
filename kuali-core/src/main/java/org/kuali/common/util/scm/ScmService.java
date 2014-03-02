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
package org.kuali.common.util.scm;

import java.io.File;
import java.util.List;

public interface ScmService {

	/**
	 * Add <code>paths</code> so they are included in files managed by the SCM system.
	 */
	void add(List<File> paths);

	/**
	 * Delete <code>paths</code> so they are no longer managed by the SCM system.
	 */
	void delete(List<File> paths);

	/**
	 * Commit <code>paths</code> with a commit message.
	 */
	void commit(List<File> paths, String message);

	/**
	 * Display version information.
	 */
	void version();

}
