/*
 * Copyright (c) 2003 The Visigoth Software Society. All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowledgement:
 *       "This product includes software developed by the
 *        Visigoth Software Society (http://www.visigoths.org/)."
 *    Alternately, this acknowledgement may appear in the software itself,
 *    if and wherever such third-party acknowledgements normally appear.
 *
 * 4. Neither the name "FreeMarker", "Visigoth", nor any of the names of the
 *    project contributors may be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact visigoths@visigoths.org.
 *
 * 5. Products derived from this software may not be called "FreeMarker" or "Visigoth"
 *    nor may "FreeMarker" or "Visigoth" appear in their names
 *    without prior written permission of the Visigoth Software Society.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE VISIGOTH SOFTWARE SOCIETY OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Visigoth Software Society. For more
 * information on the Visigoth Software Society, please see
 * http://www.visigoths.org/
 */

package freemarker.core;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * An element that calls a macro which is given by an expression
 */
public class DynamicCall extends TemplateElement {

    private Expression macroNameExp;
    private Expression macroArgsExp;

    public DynamicCall(Expression macroNameExp, Expression macroArgsExp) {
        this.macroNameExp = macroNameExp;
        this.macroArgsExp = macroArgsExp;
    }

    void accept(Environment env) throws TemplateException, IOException {
        Macro macro = getMacro(env);

        if (macro != null) {
            Map namedArgs = getMacroArguments(env);

            env.visit(macro, namedArgs, null, null, null);
        }
    }

    /**
     * Gets the macro from the given macro name expression
     * 
     * @param env
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    protected Macro getMacro(Environment env) throws TemplateException, IOException {
        Macro macro = null;

        TemplateModel macroNameTm = macroNameExp.getAsTemplateModel(env);

        if (!(macroNameTm instanceof SimpleScalar)) {
            throw new TemplateException("Macro name expression did not evaluate to scalar", env);
        }

        String macroName = ((SimpleScalar) macroNameTm).getAsString();

        Expression macroExp = buildExpression(macroName);
        TemplateModel tm = macroExp.getAsTemplateModel(env);

        if (tm == Macro.DO_NOTHING_MACRO) {
            return macro;
        }

        if (tm instanceof Macro) {
            macro = (Macro) tm;

            if (macro.isFunction) {
                throw new TemplateException("Routine " + macro.getName()
                        + " is a function. A function can only be called "
                        + "within the evaluation of an expression, like from inside ${...}.", env);
            }

        } else {
            throw new TemplateException("Variable given by macro expression did not evaluate to a macro " + macroName, env);
        }

        return macro;
    }

    /**
     * Gets the macro arguments (name=value pairs) from the given arguments
     * expression
     * 
     * @param env
     * @return
     * @throws TemplateException
     * @throws IOException
     */
    protected Map getMacroArguments(Environment env) throws TemplateException, IOException {
        Map namedArgs = new HashMap();

        TemplateModel macroArgsTm = macroArgsExp.getAsTemplateModel(env);

        if (!(macroArgsTm instanceof SimpleScalar)) {
            throw new TemplateException("Macro arguments expression did not evaluate to scalar", env);
        }

        String argsString = ((SimpleScalar) macroArgsTm).getAsString();
        argsString = argsString.trim();

        // match argument pairs (argName=argValue or argName="argValue")
        Pattern argPattern = Pattern.compile("(\\w+)\\s*=\\s*((\\w[\\w\\.\\[\\]'\\!]*)?(\".*\")?('.*')?){1}",
                Pattern.DOTALL);

        Matcher matcher = argPattern.matcher(argsString);
        while (matcher.find()) {
            namedArgs.put(matcher.group(1), buildExpression(matcher.group(2)));
        }

        return namedArgs;
    }

    /**
     * Given a string returns an expression, only primary expressions containing
     * default, map, dot, or literals is supported
     * 
     * @param expressionString
     * @return
     * @throws ParseException
     */
    protected Expression buildExpression(String expressionString) throws ParseException {
        Expression expression = null;

        expressionString = expressionString.trim();

        if (expressionString.contains("!")) {
            String[] expressionParts = expressionString.split("\\!");

            Expression defaultExpression = null;
            if (expressionParts.length > 1) {
                defaultExpression = buildExpression(expressionParts[1]);
            }

            expression = new DefaultToExpression(buildExpression(expressionParts[0]), defaultExpression);
        } else if (expressionString.contains("[")) {
            String[] expressionParts = expressionString.split("\\[");

            if (expressionParts[1].trim().endsWith("]")) {
                expressionParts[1] = expressionParts[1].trim();
                expressionParts[1] = expressionParts[1].substring(0, expressionParts[1].length() - 1);
            }

            expression = new DynamicKeyName(buildExpression(expressionParts[0]), buildExpression(expressionParts[1]));
        } else if (expressionString.contains(".")) {
            if (expressionString.startsWith(".")) {
                expressionString = expressionString.substring(1);

                String targetKey = expressionString.substring(0, expressionString.indexOf("."));
                expression = new BuiltinVariable(targetKey);
            } else {
                String targetKey = expressionString.substring(0, expressionString.indexOf("."));
                expression = buildExpression(targetKey);
            }

            expressionString = substringAfterDot(expressionString);

            expression = buildNextDot(expression, expressionString);
            expressionString = substringAfterDot(expressionString);

            while (expressionString != null) {
                expression = buildNextDot(expression, expressionString);
                expressionString = substringAfterDot(expressionString);
            }
        } else if (expressionString.startsWith("\"") || expressionString.startsWith("'")) {
            expressionString = expressionString.substring(1, expressionString.length() - 1);

            expression = new StringLiteral(expressionString);
        } else {
            expression = new Identifier(expressionString);
        }

        return expression;
    }

    /**
     * Builds a dot expression from the given string that operates on the target
     * expression
     * 
     * @param targetExpression
     * @param expressionString
     * @return
     */
    protected Expression buildNextDot(Expression targetExpression, String expressionString) {
        if (expressionString.startsWith(".")) {
            expressionString = expressionString.substring(expressionString.indexOf(".") + 1, expressionString.length());
        }

        String targetKey = null;
        if (expressionString.contains(".")) {
            targetKey = expressionString.substring(0, expressionString.indexOf("."));
            expressionString = expressionString.substring(expressionString.indexOf(".") + 1, expressionString.length());
        } else {
            targetKey = expressionString;
            expressionString = null;
        }

        return new Dot(targetExpression, targetKey);
    }

    /**
     * Gets the substring after the first dot in the given string
     * 
     * @param expressionString
     * @return
     */
    protected String substringAfterDot(String expressionString) {
        if (expressionString.contains(".")) {
            return expressionString.substring(expressionString.indexOf(".") + 1, expressionString.length());
        } else {
            return null;
        }
    }

    protected String dump(boolean canonical) {
        if (canonical) {
            StringBuffer buf = new StringBuffer("<#");

            buf.append(macroNameExp.getCanonicalForm());

            buf.append(macroArgsExp.getCanonicalForm());

            buf.append("/>");

            return buf.toString();
        } else {
            return "dynamic-directive-call " + macroNameExp;
        }
    }

    String getNodeTypeSymbol() {
        return "#dyncall-" + macroNameExp.getCanonicalForm();
    }

    int getParameterCount() {
        return macroArgsExp.getParameterCount();
    }

    Object getParameterValue(int idx) {
        return macroArgsExp.getParameterValue(idx);
    }

    ParameterRole getParameterRole(int idx) {
        return macroArgsExp.getParameterRole(idx);
    }

}
