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
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * Utility class facilitating low-level access to FreeMarker templates for the
 * KRAD framework.
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public final class KualiTemplateUtils {

    /**
     * Private constuctor.
     */
    private KualiTemplateUtils() {
    }

    /**
     * Internal FreeMarker expression for wrapping any object.
     */
    private static class WrappedObjectExpression extends Expression {

        /**
         * The wrapped object.
         */
        private final Object wrappedObject;

        /**
         * The wrapped object, as a FreeMarker model element.
         */
        private final TemplateModel model;

        /**
         * Constructor.
         * 
         * @param wrappedObject
         *            The object to wrap.
         */
        private WrappedObjectExpression(Object wrappedObject) throws TemplateModelException {
            this.wrappedObject = wrappedObject;
            if (wrappedObject instanceof TemplateModel) {
                this.model = (TemplateModel) wrappedObject;
            } else {
                this.model = ObjectWrapper.DEFAULT_WRAPPER.wrap(wrappedObject);
            }
        }
        
        /**
         * Wrap the object as a FreeMarker model element.
         */
        TemplateModel _eval(Environment env) throws TemplateException {
            return model;
        }

        boolean isLiteral() {
            return false;
        }

        protected Expression deepCloneWithIdentifierReplaced_inner(String replacedIdentifier, Expression replacement,
                ReplacemenetState replacementState) {
            try {
                return new WrappedObjectExpression(wrappedObject);
            } catch (TemplateModelException e) {
                throw new RuntimeException("Error cloning wrapped object expression", e);
            }
        }

        public String getCanonicalForm() {
            return "-KUALI-wrapped-object-expression-"
                    + (wrappedObject == null ? "null" : wrappedObject.getClass().getName());
        }

        String getNodeTypeSymbol() {
            return "-KUALI-wrapped-object-expression";
        }

        int getParameterCount() {
            return 0;
        }

        Object getParameterValue(int idx) {
            return null;
        }

        ParameterRole getParameterRole(int idx) {
            return null;
        }

    }

    /**
     * Invoke a macro in the given execution environment using direct Java
     * objects as named arguments, and (optionally) pre-rendered body content.
     * 
     * @param env
     *            The execution environment.
     * @param macro
     *            The macro.
     * @param args
     *            Named arguments, as direct object instances.
     * @param body
     *            Pre-rendered body content.
     */
    public static void invokeMacro(Environment env, Macro macro, Map args, String body) {
        Map wrappedArgs = new java.util.HashMap();
        Iterator argEntryIterator = args.entrySet().iterator();
        while (argEntryIterator.hasNext()) {
            Entry argEntry = (Entry) argEntryIterator.next();
            try {
                wrappedArgs.put(argEntry.getKey(), new WrappedObjectExpression(argEntry.getValue()));
            } catch (TemplateModelException e) {
                throw new RuntimeException("Error wrapping argument as a FreeMarker model element", e);
            }
        }
        try {
            env.visit(macro, wrappedArgs, null, null, body == null ? null : new TextBlock(body));
        } catch (TemplateException e) {
            throw new RuntimeException("Error invoking macro " + macro.getCanonicalForm(), e);
        } catch (IOException e) {
            throw new RuntimeException("Error invoking macro " + macro.getCanonicalForm(), e);
        }
    }

}
