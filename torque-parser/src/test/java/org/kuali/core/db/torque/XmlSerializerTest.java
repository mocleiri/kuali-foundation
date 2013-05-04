package org.kuali.core.db.torque;

import java.io.File;
import java.io.OutputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.xerces.dom.DocumentImpl;
import org.apache.xerces.dom.DocumentTypeImpl;
import org.apache.xml.serialize.Method;
import org.apache.xml.serialize.OutputFormat;
import org.apache.xml.serialize.XMLSerializer;
import org.junit.Ignore;
import org.junit.Test;
import org.kuali.common.util.LocationUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;

public class XmlSerializerTest {

	private static final Logger logger = LoggerFactory.getLogger(XmlSerializerTest.class);

	@Test
	@Ignore
	public void test1() {
		try {
			File file = new File("/Users/jeffcaddel/ws/impex-2.0/torque-generator/target/schema.xml");
			String encoding = "UTF-8";
			Document document = getDocument();
			logger.info("Creating [{}]", LocationUtils.getCanonicalPath(file));
			serialize(document, file, encoding);
			logger.info("Done");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected Document getDocument() {
		DocumentTypeImpl docType = new DocumentTypeImpl(null, "database", null, "foobar");
		DocumentImpl doc = new DocumentImpl(docType);
		doc.appendChild(doc.createComment(" what thee hell "));
		return doc;
	}

	protected void serialize(Document document, File file, String encoding) {
		OutputStream out = null;
		try {
			out = FileUtils.openOutputStream(file);
			OutputFormat format = new OutputFormat(Method.XML, encoding, true);
			XMLSerializer serializer = new XMLSerializer();
			serializer.setOutputByteStream(out);
			serializer.setOutputFormat(format);
			serializer.serialize(document);
		} catch (Exception e) {
			throw new IllegalStateException("Error serializing", e);
		} finally {
			IOUtils.closeQuietly(out);
		}
	}
}
