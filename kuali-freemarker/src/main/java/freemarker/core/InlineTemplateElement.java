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
import java.util.HashMap;
import java.util.Map;

// KULRICE-10260 - use ProcessLogger for execution analysis
// import org.kuali.rice.krad.uif.util.ProcessLogger;

import freemarker.template.TemplateException;

/**
 * Inline processing element.
 * 
 * <p>
 * This KRAD FreeMarker extension passes control from FreeMarker templates to
 * inline Java code with direct access to the current processing environment.
 * </p>
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class InlineTemplateElement extends TemplateElement {

    /**
     * Static adaptor registry.
     */
    private static final Map ADAPTORS = new HashMap();

    /**
     * Register an inline processing adator. This method should be called once
     * per inline adaptor per classloader that manages FreeMarker templates, by
     * an initialization routine.
     * 
     * <p>
     * Once the application has initialized, it is expected that this method
     * will not be called again.
     * </p>
     * 
     * @param name
     *            The name of the adaptor.
     * @param adaptor
     *            The adaptor instance.
     */
    public static void registerAdaptor(String name, InlineTemplateAdaptor adaptor) {
        synchronized (ADAPTORS) {
            ADAPTORS.put(name, adaptor);
        }
    }

    /**
     * Adaptor name expression, parsed from the template.
     * 
     * For example:
     * 
     * <pre>
     * &lt;#inline 'adaptorName' /&gt;
     * </pre>
     */
    private final Expression adaptorNameExp;

    /**
     * Create a new inline processing element.
     * 
     * <p>
     * This constructor is invoked from the FTL parser.
     * </p>
     * 
     * @param adaptorNameExp
     *            The adaptor name expression.
     * @see FTL.jj
     */
    public InlineTemplateElement(Expression adaptorNameExp) {
        this.adaptorNameExp = adaptorNameExp;
    }

    /**
     * Get an adaptor from the environment for inline processing.
     * 
     * @param env
     *            The current FreeMarker environment.
     * @param adaptorName
     *            The name of the adaptor.
     * @return An adaptor, registered using
     *         {@link #registerAdaptor(String, InlineTemplateAdaptor)} using the
     *         provided adaptor name.
     * @throws TemplateException If the adator was not registered.
     * @see #registerAdaptor(String, InlineTemplateAdaptor)
     */
    private InlineTemplateAdaptor getAdaptor(Environment env, String adaptorName) throws TemplateException {
        InlineTemplateAdaptor adaptor = (InlineTemplateAdaptor) ADAPTORS.get(adaptorName);

        if (adaptor == null) {
            throw new TemplateException("Inline template adaptor " + adaptorName + " not registered", env);
        }

        return adaptor;
    }

    /**
     * Pass template processing control to an inline processing adaptor.
     * @see InlineTemplateAdaptor#accept(Environment)
     */
    void accept(Environment env) throws TemplateException, IOException {
        String adaptorName = adaptorNameExp.evalAndCoerceToString(env);

        // See KULRICE-10260 - use ProcessLogger for execution analysis
        // ProcessLogger.ntrace("fminline:", ":" + adaptorName, 1000);
        // ProcessLogger.countBegin("fminline:" + adaptorName);

        getAdaptor(env, adaptorName).accept(env);

        // ProcessLogger.countEnd("fminline:" + adaptorName,
        //        Environment.instructionStackItemToString(env.getInstructionStackSnapshot()[1]));
    }

    /**
     * {@inheritDoc}
     */
    protected String dump(boolean canonical) {
        return "#inline " + adaptorNameExp.getCanonicalForm();
    }

    /**
     * {@inheritDoc}
     */
    String getNodeTypeSymbol() {
        return "#inline-" + adaptorNameExp.getCanonicalForm();
    }

    /**
     * {@inheritDoc}
     */
    int getParameterCount() {
        return 0;
    }

    /**
     * {@inheritDoc}
     */
    Object getParameterValue(int idx) {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    ParameterRole getParameterRole(int idx) {
        return null;
    }

}
