/*
 * Copyright 2006-2011 The Kuali Foundation
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
package org.kuali.rice.devtools.pdle;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.kuali.rice.kns.bo.PersistableBusinessObject;
import org.kuali.rice.kns.service.BusinessObjectService;
import org.kuali.rice.kns.service.KNSServiceLocator;
import org.kuali.rice.kns.service.KNSServiceLocatorInternal;
import org.springframework.core.io.FileSystemResource;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * This is a servlet that can be used to invoke the PostDataLoadEncryptionService.
 * 
 * It is not recommended to leave this Servlet running at all times.  It is really only intended
 * to be made available during initial data load and then removed (from the web.xml of the
 * application) after data load and encryption is complete.
 * 
 * This was done as a Servlet for now because Rice does not have a batch runner yet similar
 * to what KFS has (which is where a lot of the code below was borrowed from).
 *
 *
 *  <code>

   <!--
		Add the following to your web.xml these if you want to run the servlet in order to encrypt
        some data in the Rice database.
		(this would most likely be used for external identifiers in KIM that require encryption)

		It is not recommended to enable this service for a production instance except during initial
		data load.  It is not secured in any way and would allow for easy corruption of data if mishandled.
	-->

	<servlet>
	    <servlet-name>postDataLoadEncryption</servlet-name>
	    <servlet-class>org.kuali.rice.devtools.pdle.PostDataLoadEncryptionServlet</servlet-class>
	</servlet>

	<servlet-mapping>
		<servlet-name>postDataLoadEncryption</servlet-name>
		<url-pattern>/postDataLoadEncryption</url-pattern>
	</servlet-mapping>

    </code>
 *
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class PostDataLoadEncryptionServlet extends HttpServlet {

	private static final Log LOG = LogFactory.getLog(PostDataLoadEncryptionServlet.class);
	
	private static final String ATTRIBUTES_TO_ENCRYPT_PROPERTIES = "attributesToEncryptProperties";
	private static final String CHECK_OJB_ENCRYPT_CONFIG = "checkOjbEncryptConfig";
	
	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		String attributesToEncryptPropertyFileName = request.getParameter(ATTRIBUTES_TO_ENCRYPT_PROPERTIES);
		if (StringUtils.isBlank(attributesToEncryptPropertyFileName)) {
			throw new IllegalArgumentException("No valid " + ATTRIBUTES_TO_ENCRYPT_PROPERTIES + " parameter was passed to this Servlet.");
		}
		boolean checkOjbEncryptConfig = true;
		String checkOjbEncryptConfigValue = request.getParameter(CHECK_OJB_ENCRYPT_CONFIG);
		if (!StringUtils.isBlank(checkOjbEncryptConfigValue)) {
			checkOjbEncryptConfig = Boolean.valueOf(checkOjbEncryptConfigValue);
		}
		execute(attributesToEncryptPropertyFileName, checkOjbEncryptConfig);
		response.getOutputStream().write(new String("<html><body><p>Successfully encrypted attributes as defined in: " + attributesToEncryptPropertyFileName + "</p></body></html>").getBytes());
	}

	public void execute(String attributesToEncryptPropertyFileName, boolean checkOjbEncryptConfig) {
		PostDataLoadEncryptionService postDataLoadEncryptionService = KNSServiceLocatorInternal.getService(PostDataLoadEncryptionService.POST_DATA_LOAD_ENCRYPTION_SERVICE);
        Properties attributesToEncryptProperties = new Properties();
        try {
            attributesToEncryptProperties.load(new FileSystemResource(attributesToEncryptPropertyFileName).getInputStream());
        }
        catch (Exception e) {
            throw new IllegalArgumentException("PostDataLoadEncrypter requires the full, absolute path to a properties file where the keys are the names of the BusinessObject classes that should be processed and the values are the list of attributes on each that require encryption", e);
        }
        for (Object businessObjectClassName : attributesToEncryptProperties.keySet()) {
            Class businessObjectClass;
            try {
                businessObjectClass = Class.forName((String) businessObjectClassName);
            }
            catch (Exception e) {
                throw new IllegalArgumentException(new StringBuffer("Unable to load Class ").append(businessObjectClassName).append(" specified by name in attributesToEncryptProperties file ").append(attributesToEncryptProperties).toString(), e);
            }
            Set<String> attributeNames = null;
            try {
                attributeNames = new HashSet(Arrays.asList(StringUtils.split((String) attributesToEncryptProperties.get(businessObjectClassName), ",")));
            }
            catch (Exception e) {
                throw new IllegalArgumentException(new StringBuffer("Unable to load attributeNames Set from comma-delimited list of attribute names specified as value for property with Class name ").append(businessObjectClassName).append(" key in attributesToEncryptProperties file ").append(attributesToEncryptProperties).toString(), e);
            }
            postDataLoadEncryptionService.checkArguments(businessObjectClass, attributeNames, checkOjbEncryptConfig);
            postDataLoadEncryptionService.createBackupTable(businessObjectClass);
            BusinessObjectService businessObjectService = KNSServiceLocator.getBusinessObjectService();
            try {
                postDataLoadEncryptionService.prepClassDescriptor(businessObjectClass, attributeNames);
                Collection objectsToEncrypt = businessObjectService.findAll(businessObjectClass);
                for (Object businessObject : objectsToEncrypt) {
                    postDataLoadEncryptionService.encrypt((PersistableBusinessObject) businessObject, attributeNames);
                }
                postDataLoadEncryptionService.restoreClassDescriptor(businessObjectClass, attributeNames);
                LOG.info(new StringBuffer("Encrypted ").append(attributesToEncryptProperties.get(businessObjectClassName)).append(" attributes of Class ").append(businessObjectClassName));
            }
            catch (Exception e) {
                postDataLoadEncryptionService.restoreTableFromBackup(businessObjectClass);
                LOG.error(new StringBuffer("Caught exception, while encrypting ").append(attributesToEncryptProperties.get(businessObjectClassName)).append(" attributes of Class ").append(businessObjectClassName).append(" and restored table from backup"), e);
            }
            postDataLoadEncryptionService.dropBackupTable(businessObjectClass);
        }
    }
	
}
