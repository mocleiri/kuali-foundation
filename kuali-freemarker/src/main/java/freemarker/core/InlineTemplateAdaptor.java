/**
 * Copyright 2005-2013 The Kuali Foundation
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
package freemarker.core;

import java.io.IOException;

import freemarker.template.TemplateException;

/**
 * Adaptor interface for use with {@link InlineTemplateElement}.
 * 
 * <p>
 * This interface facilitates the implementation of pragmatic FreeMarker
 * extensions via externally managed code.
 * </p>
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 * @see InlineTemplateElement#registerAdaptor(String, InlineKradAdaptor)
 */
public interface InlineTemplateAdaptor {

    /**
     * Perform inline processing in the current environment.
     * 
     * @param env
     *            The FreeMarker execution environment.
     * @throws TemplateException
     *             If a template error occurs.
     * @throws IOException
     *             If an IO error occurs.
     * @see TemplateElement#accept(Environment)
     */
    void accept(Environment env) throws TemplateException, IOException;

}
