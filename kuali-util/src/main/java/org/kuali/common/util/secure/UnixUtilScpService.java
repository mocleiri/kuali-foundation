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
package org.kuali.common.util.secure;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.codehaus.plexus.util.cli.Commandline;
import org.kuali.common.util.CollectionUtils;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.UnixUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

/**
 *
 */
public class UnixUtilScpService implements ScpService {

	private static final Logger logger = LoggerFactory.getLogger(UnixUtilScpService.class);

	private static final String SCP = "scp";

	/**
	 * <pre>
	 * scp source destination
	 * </pre>
	 *
	 * Where both <code>source</code> and <code>destination</code> are in the format
	 *
	 * <pre>
	 * [[user@]host:]file
	 * </pre>
	 */
	public static final int scp(String source, String destination) {
		return scp(null, source, destination);
	}

	/**
	 * <pre>
	 * scp [args] source destination
	 * </pre>
	 *
	 * Where both <code>source</code> and <code>destination</code> are in the format
	 *
	 * <pre>
	 * [[user@]host:]file
	 * </pre>
	 */
	public static final int scp(List<String> args, String source, String destination) {
		Assert.notNull(source);
		Assert.notNull(destination);
		List<String> arguments = new ArrayList<String>();
		arguments.addAll(CollectionUtils.toEmpty(args));
		arguments.add(source);
		arguments.add(destination);
		Commandline cl = new Commandline();
		cl.setExecutable(SCP);
		cl.addArguments(CollectionUtils.toStringArray(arguments));
		return UnixUtils.execute(cl);
	}

	/**
	 * <pre>
	 * scp [args] source destination
	 * </pre>
	 *
	 * Where <code>source</code> is a file on the local file system and <code>destination</code> is in the format
	 *
	 * <pre>
	 * [[user@]host:]file
	 * </pre>
	 */
	public static final int scp(List<String> args, File source, String destination) {
		Assert.notNull(source);
		String sourcePath = LocationUtils.getCanonicalPath(source);
		if (!source.exists()) {
			throw new IllegalArgumentException(sourcePath + " does not exist");
		}
		return scp(args, sourcePath, destination);
	}

	/**
	 * <pre>
	 * scp [args] source destination
	 * </pre>
	 *
	 * Where <code>destination</code> is a file on the local file system and <code>source</code> is in the format
	 *
	 * <pre>
	 * [[user@]host:]file
	 * </pre>
	 */
	public static final int scp(List<String> args, String source, File destination) {
		try {
			FileUtils.touch(destination);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		}
		String localPath = LocationUtils.getCanonicalPath(destination);
		return scp(args, source, localPath);
	}

	/**
	 * <pre>
	 * scp source destination
	 * </pre>
	 *
	 * Where <code>source</code> is a file on the local file system and <code>destination</code> is in the format
	 *
	 * <pre>
	 * [[user@]host:]file
	 * </pre>
	 */
	public int copy(File source, String destination) {
		return scp(null, source, destination);
	}

	/**
	 * <pre>
	 * scp source destination
	 * </pre>
	 *
	 * Where <code>destination</code> is a file on the local file system and <code>source</code> is in the format
	 *
	 * <pre>
	 * [[user@]host:]file
	 * </pre>
	 */
	public int copy(String source, File destination) {
		return scp(null, source, destination);
	}

}
