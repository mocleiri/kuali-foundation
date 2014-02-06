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
package org.kuali.common.util.secure;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @deprecated
 */
@Deprecated
public interface SecureChannel {

	void open() throws IOException;

	void close();

	/**
	 * @deprecated
	 */
	@Deprecated
	void copyFile(File source, RemoteFile destination);

	/**
	 * @deprecated
	 */
	@Deprecated
	void copyLocationToFile(String location, RemoteFile destination);

	/**
	 * @deprecated
	 */
	@Deprecated
	void copyInputStreamToFile(InputStream source, RemoteFile destination);

	/**
	 * @deprecated
	 */
	@Deprecated
	void copyStringToFile(String string, RemoteFile destination);

	/**
	 * @deprecated
	 */
	@Deprecated
	void copyLocationToDirectory(String location, RemoteFile destination);

	/**
	 * @deprecated
	 */
	@Deprecated
	void copyFileToDirectory(File source, RemoteFile destination);

	/**
	 * @deprecated
	 */
	@Deprecated
	void copyFile(RemoteFile source, File destination);

	/**
	 * @deprecated
	 */
	@Deprecated
	void copyFileToDirectory(RemoteFile source, File destination);

	/**
	 * @deprecated
	 */
	@Deprecated
	RemoteFile getMetaData(String absolutePath);

	boolean exists(String absolutePath);

	boolean isDirectory(String absolutePath);

	void deleteFile(String absolutePath);

	/**
	 * @deprecated
	 */
	@Deprecated
	void createDirectory(RemoteFile dir);

	/**
	 * @deprecated
	 */
	@Deprecated
	RemoteFile getWorkingDirectory();

	/**
	 * @deprecated
	 */
	@Deprecated
	Result executeCommand(String command);

	/**
	 * @deprecated
	 */
	@Deprecated
	Result executeCommand(String command, String stdin);

	void executeNoWait(String command);

	void copyRemoteFile(String absolutePath, OutputStream out) throws IOException;

	/**
	 * @deprecated
	 */
	@Deprecated
	void copyFile(RemoteFile source, OutputStream out) throws IOException;

	/**
	 * @deprecated
	 */
	@Deprecated
	String toString(RemoteFile source);

}