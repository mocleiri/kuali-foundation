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
import java.util.List;

import org.kuali.common.util.channel.model.CommandResult;
import org.kuali.common.util.channel.model.CopyResult;
import org.kuali.common.util.channel.model.RemoteFile;

import com.google.common.base.Optional;

public interface SecureChannel {

	CommandResult exec(String command);

	List<CommandResult> exec(String... commands);

	CommandResult exec(String command, Optional<String> stdin);

	void execNoWait(String command);

	CopyResult scp(File source, RemoteFile destination);

	CopyResult scp(String location, RemoteFile destination);

	CopyResult scp(InputStream source, RemoteFile destination);

	/**
	 * Copy the contents of <code>string</code> to <code>destination</code>
	 */
	CopyResult scpString(String string, RemoteFile destination);

	CopyResult scpToDir(String location, RemoteFile directory);

	CopyResult scpToDir(File source, RemoteFile directory);

	CopyResult scp(RemoteFile source, File destination);

	CopyResult scpToDir(RemoteFile source, File directory);

	RemoteFile getMetaData(String absolutePath);

	boolean exists(String absolutePath);

	boolean isDirectory(String absolutePath);

	void deleteFile(String absolutePath);

	void createDirectory(RemoteFile dir);

	RemoteFile getWorkingDirectory();

	CopyResult scp(String absolutePath, OutputStream out) throws IOException;

	CopyResult scp(RemoteFile source, OutputStream out) throws IOException;

	String toString(RemoteFile source);

	void close();

}