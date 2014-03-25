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
package org.kuali.common.util.xml.service;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;

public interface XmlService {

	<T> T getObject(InputStream in, Class<T> type);

	<T> T getObject(File in, Class<T> type);

	<T> T getObject(String location, Class<T> type);

	<T> T getObjectFromXml(String xml, String encoding, Class<T> type);

	void write(File file, Object object);

	void write(OutputStream out, Object object);

	String toXml(Object object, String encoding);

	/**
	 * @deprecated Use toXML(object,encoding) instead
	 */
	@Deprecated
	String toString(Object object, String encoding);

}
