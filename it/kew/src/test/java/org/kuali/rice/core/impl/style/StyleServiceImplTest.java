/*
 * Copyright 2005-2007 The Kuali Foundation
 *
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
package org.kuali.rice.core.impl.style;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.transform.Templates;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.kuali.rice.core.api.CoreApiServiceLocator;
import org.kuali.rice.core.api.exception.RiceRuntimeException;
import org.kuali.rice.core.api.impex.xml.XmlIngestionException;
import org.kuali.rice.core.api.resourceloader.GlobalResourceLoader;
import org.kuali.rice.core.api.style.Style;
import org.kuali.rice.core.api.style.StyleService;
import org.kuali.rice.core.framework.impex.xml.XmlLoader;
import org.kuali.rice.core.impl.services.CoreImplServiceLocator;
import org.kuali.rice.kew.test.KEWTestCase;
import org.kuali.rice.kew.test.TestUtilities;
import org.kuali.rice.ksb.api.cache.RiceCacheAdministrator;


/**
 * Tests StyleServiceImpl
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class StyleServiceImplTest extends KEWTestCase {
    private static final Logger LOG = Logger.getLogger(StyleServiceImplTest.class);

	@Test public void testLoadXML() throws FileNotFoundException {
        loadXmlFile("style.xml");

        StyleService styleService = CoreApiServiceLocator.getStyleService();
        assertNotNull("Style 'an_arbitrary_style' not found", styleService.getStyle("an_arbitrary_style"));

        Style style = styleService.getStyle("an_arbitrary_style");
        assertNotNull("'an_arbitrary_style' style not found", style);
        assertEquals("an_arbitrary_style", style.getName());
        assertTrue(style.isActive());
        assertNotNull(style.getXmlContent());
    }


	/**
	 * Tests automatic import of styles from files based on configuration properties.
	 * See edl.style.widgets in common-config-defualts.xml, edl.style.gidgets in kew-test-config.xml
	 */
    @Test public void testLoadingFromConfiguredFile() {
        StyleService styleService = CoreApiServiceLocator.getStyleService();
        StyleDao dao = (StyleDao)GlobalResourceLoader.getService("styleDao");

        String notThereStyle = "gidgets";
        String isThereStyle = "widgets";

        // first verify that the database doesn't contain these styles already
        assertNull(dao.getStyle(notThereStyle));
        assertNull(dao.getStyle(isThereStyle));

        // test loading an incorrectly configured style
        try {
            // the configured location for the gidgets style doesn't contain a file
            styleService.getStyle(notThereStyle);
            fail("should have thrown " + RiceRuntimeException.class.getSimpleName());
        } catch (RiceRuntimeException e) {
            LOG.info("^^^ CAUGHT EXPECTED EXCEPTION ^^^");
        } catch (Exception e) {
            fail("Wrong exception type '" + e.getClass() + "', should have been '" + RiceRuntimeException.class.getCanonicalName() + "'");
        }

        styleService.getStyle("widgets");
        // should succeed in loading it's style into the database
    }

    @Test public void testInclusions() throws FileNotFoundException, TransformerConfigurationException, TransformerException {
        loadXmlFile("style.xml");

        StyleService styleService = CoreApiServiceLocator.getStyleService();

        // ignoring the duplicate definition via inclusion test as the behavior seems
        // unspecified
        // XML.com claims it is an "error": http://www.xml.com/pub/a/2000/11/01/xslt/index.html
        // XLST 1.0 spec doesn't seem to specify anything regarding this: http://www.w3.org/TR/xslt
        // Michael Kay's XSLT Programmer's Reference states "...it is implementation-defined
        // whether an XSLT processor will report duplicate declarations as an error , so
        // the behavior may vary from on product to another
        // (although it is not clear to me whether he is speaking specifically of identical
        // literal definitions introduced by re-inclusion of the same exact stylesheet twice, or
        // "logical" duplication of template match criteria)
        /*Templates t = styleService.getStyleAsTranslet("test_includer");
        StringWriter w = new StringWriter();
        StreamResult result = new StreamResult(w);
        try {
            t.newTransformer().transform(new StreamSource(new StringReader("<a/>")), result);
            System.err.println(w.toString());
            fail("Exception not thrown on ambiguous template defs");
        } catch (Exception e) {
            // expected
        }*/

        Writer w = new StringWriter();
        StreamResult result = new StreamResult(w);
        Templates t = styleService.getStyleAsTranslet("test_includer2");
        t.newTransformer().transform(new StreamSource(new StringReader("<a/>")), result);
        assertEquals("oneoneoneoneone", w.toString());

        w = new StringWriter();
        result = new StreamResult(w);
        t.newTransformer().transform(new StreamSource(new StringReader("<b/>")), result);
        assertEquals("22222", w.toString());

        w = new StringWriter();
        result = new StreamResult(w);
        t = styleService.getStyleAsTranslet("test_importer");
        t.newTransformer().transform(new StreamSource(new StringReader("<a/>")), result);
        assertEquals("aaaaa", w.toString());

        w = new StringWriter();
        result = new StreamResult(w);
        t.newTransformer().transform(new StreamSource(new StringReader("<b/>")), result);
        assertEquals("BBBBB", w.toString());

        w = new StringWriter();
        result = new StreamResult(w);
        t.newTransformer().transform(new StreamSource(new StringReader("<c/>")), result);
        assertEquals("CCCCC", w.toString());
    }

    @Test public void testLoadBadDefinition() throws FileNotFoundException {
        XmlLoader xmlLoader = CoreImplServiceLocator.getStyleXmlLoader();
        try {
            xmlLoader.loadXml(TestUtilities.loadResource(getClass(), "badstyle.xml"), null);
            fail("BadDefinition was successfully parsed.");
        } catch (XmlIngestionException re) {
            // should probably use type system to detect type of error, not just message string...
            // maybe we need general parsing or "semantic" validation exception
            assertTrue("Wrong exception occurred: " + re, re.getMessage().contains("Style 'style' element must contain a 'xsl:stylesheet' child element"));
        }
    }

    @Test public void testStoreStyle() {
    	StyleService styleService = CoreApiServiceLocator.getStyleService();
    	XmlLoader xmlLoader = CoreImplServiceLocator.getStyleXmlLoader();
        String styleXml = "<data xmlns=\"ns:workflow\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"ns:workflow resource:WorkflowData\"><styles xmlns=\"ns:workflow/Style\" xsi:schemaLocation=\"ns:workflow/Style resource:Style\"><style></style></styles></data>";
        try {
            xmlLoader.loadXml(new ByteArrayInputStream(styleXml.getBytes()), null);
            fail("Storing style with no name succeeded");
        } catch (XmlIngestionException e) {
            // expected due to lack of name
        }
        styleXml = "<data xmlns=\"ns:workflow\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"ns:workflow resource:WorkflowData\"><styles xmlns=\"ns:workflow/Style\" xsi:schemaLocation=\"ns:workflow/Style resource:Style\"><style name=\"test\"></style></styles></data>";
        try {
        	xmlLoader.loadXml(new ByteArrayInputStream(styleXml.getBytes()), null);
            fail("Storing style with no xsl:stylesheet element succeeded");
        } catch (XmlIngestionException e) {
            // expected due to lack of stylesheet content
        }
        styleXml = "<data xmlns=\"ns:workflow\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:schemaLocation=\"ns:workflow resource:WorkflowData\"><styles xmlns=\"ns:workflow/Style\" xsi:schemaLocation=\"ns:workflow/Style resource:Style\"><style name=\"test\"><xsl:stylesheet></xsl:stylesheet></style></styles></data>";
        xmlLoader.loadXml(new ByteArrayInputStream(styleXml.getBytes()), null);
        Style style = styleService.getStyle("test");
        assertNotNull(style);
        assertEquals("test", style.getName());
        assertNotNull(style);
        assertNotNull(style.getXmlContent());
    }

    /**
     * Tests the caching of "styles" in StyleServiceImpl.
     *
     * The style cache is really a cache of java.xml.transform.Templates objects which represent
     * the "compiled" stylesheets.
     */
    @Test public void testStyleCaching() throws Exception {
        loadXmlFile("style.xml");

        // try to grab the templates out of the cache, it shouldn't be cached yet
        StyleServiceImpl styleServiceImpl = new StyleServiceImpl();
        styleServiceImpl.setCache((RiceCacheAdministrator)GlobalResourceLoader.getService("coreCache"));
        Templates cachedTemplates = styleServiceImpl.fetchTemplatesFromCache("an_arbitrary_style");
        assertNull("The default style template should not be cached yet.", cachedTemplates);

        // fetch the Templates object from the service
        Templates templates = CoreApiServiceLocator.getStyleService().getStyleAsTranslet("an_arbitrary_style");
        assertNotNull("Templates should not be null.", templates);
        templates = CoreApiServiceLocator.getStyleService().getStyleAsTranslet("an_arbitrary_style");
        assertNotNull("Templates should not be null.", templates);

        // the Templates should now be cached
        cachedTemplates = styleServiceImpl.fetchTemplatesFromCache("an_arbitrary_style");
        assertNotNull("Templates should now be cached.", cachedTemplates);

        // the cached Templates should be the same as the Templates we fetched from the service
        assertEquals("Templates should be the same.", templates, cachedTemplates);

        // now re-import the style and the templates should no longer be cached
        loadXmlFile("style.xml");
        cachedTemplates = styleServiceImpl.fetchTemplatesFromCache("an_arbitrary_style");
        assertNull("After re-import, the Default style Templates should no longer be cached.", cachedTemplates);

        // re-fetch the templates from the service and verify they are in the cache
        Templates newTemplates = CoreApiServiceLocator.getStyleService().getStyleAsTranslet("an_arbitrary_style");
        assertNotNull("Templates should not be null.", templates);
        newTemplates = CoreApiServiceLocator.getStyleService().getStyleAsTranslet("an_arbitrary_style");
        assertNotNull("Templates should not be null.", templates);

        cachedTemplates = styleServiceImpl.fetchTemplatesFromCache("an_arbitrary_style");
        assertNotNull("Templates should now be cached.", cachedTemplates);

        // lastly, check that the newly cached templates are not the same as the original templates
        assertFalse("Old Templates should be different from new Templates.", templates.equals(newTemplates));

    }
}
