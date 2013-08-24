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
package org.kuali.common.util.xml;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.UnmarshallerHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class Log4JXmlService extends JAXBXmlService {

	@Override
	@SuppressWarnings("unchecked")
	public <T> T getObject(InputStream in, Class<T> type) {
		try {
			Class<?>[] classes = getClassesToBeBound(type);
			JAXBContext jc = JAXBContext.newInstance(classes);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			UnmarshallerHandler unmarshallerHandler = unmarshaller.getUnmarshallerHandler();

			SAXParserFactory spf = SAXParserFactory.newInstance();
			SAXParser sp = spf.newSAXParser();
			XMLReader xr = sp.getXMLReader();
			xr.setContentHandler(unmarshallerHandler);
			InputSource xmlSource = new InputSource(in);
			xr.parse(xmlSource);
			return (T) unmarshallerHandler.getResult();
		} catch (JAXBException e) {
			throw new IllegalStateException("Unexpected JAXB error", e);
		} catch (SAXException e) {
			throw new IllegalStateException("Unexpected SAX error", e);
		} catch (IOException e) {
			throw new IllegalStateException("Unexpected IO error", e);
		} catch (ParserConfigurationException e) {
			throw new IllegalStateException("Unexpected parser configuration error", e);
		}
	}
}
