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
package org.kuali.common.util.channel.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.kuali.common.util.channel.model.CommandResult;
import org.kuali.common.util.channel.model.CopyResult;
import org.kuali.common.util.channel.model.RemoteFile;

public interface SecureChannel {

	CommandResult executeCommand(String command);

	CommandResult executeCommand(String command, String stdin);

	void executeNoWait(String command);

	CopyResult copyFile(File source, RemoteFile destination);

	CopyResult copyLocationToFile(String location, RemoteFile destination);

	CopyResult copyInputStreamToFile(InputStream source, RemoteFile destination);

	CopyResult copyStringToFile(String string, RemoteFile destination);

	CopyResult copyLocationToDirectory(String location, RemoteFile directory);

	CopyResult copyFileToDirectory(File source, RemoteFile directory);

	CopyResult copyFile(RemoteFile source, File destination);

	CopyResult copyFileToDirectory(RemoteFile source, File directory);

	RemoteFile getMetaData(String absolutePath);

	boolean exists(String absolutePath);

	boolean isDirectory(String absolutePath);

	void deleteFile(String absolutePath);

	void createDirectory(RemoteFile dir);

	RemoteFile getWorkingDirectory();

	CopyResult copyRemoteFile(String absolutePath, OutputStream out) throws IOException;

	CopyResult copyFile(RemoteFile source, OutputStream out) throws IOException;

	String toString(RemoteFile source);

	void close();

}