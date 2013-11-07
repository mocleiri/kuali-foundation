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
package org.kuali.common.util.channel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface SecureChannel {

	Result executeCommand(String command);

	Result executeCommand(String command, String stdin);

	void executeNoWait(String command);

	void copyFile(File source, RemoteFile destination);

	void copyLocationToFile(String location, RemoteFile destination);

	void copyInputStreamToFile(InputStream source, RemoteFile destination);

	void copyStringToFile(String string, RemoteFile destination);

	void copyLocationToDirectory(String location, RemoteFile directory);

	void copyFileToDirectory(File source, RemoteFile directory);

	void copyFile(RemoteFile source, File destination);

	void copyFileToDirectory(RemoteFile source, File directory);

	RemoteFile getMetaData(String absolutePath);

	boolean exists(String absolutePath);

	boolean isDirectory(String absolutePath);

	void deleteFile(String absolutePath);

	void createDirectory(RemoteFile dir);

	RemoteFile getWorkingDirectory();

	void copyRemoteFile(String absolutePath, OutputStream out) throws IOException;

	void copyFile(RemoteFile source, OutputStream out) throws IOException;

	String toString(RemoteFile source);

	void close();

}