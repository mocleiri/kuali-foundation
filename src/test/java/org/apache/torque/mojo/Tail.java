/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.apache.torque.mojo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Tail {
	public static void main(String[] args) {
		try {
			int display = 3 * 1024;
			String filename = "C:/temp/old.xml";
			File file = new File(filename);
			long length = file.length();
			InputStream in = new FileInputStream(file);
			in.skip(length - display);
			OutputStream out = new FileOutputStream("C:/temp/tail.txt");
			byte[] buffer = new byte[1024];
			int readLength = 0;
			while ((readLength = in.read(buffer, 0, buffer.length)) != -1) {
				out.write(buffer, 0, readLength);
			}
			in.close();
			out.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

}
