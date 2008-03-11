/*
 * Copyright 2008 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.core.db.torque;

/**
 * Common interface all loggable objects must implement.
 */
public interface Loggable {
    public static final int NO_TRACING = 5;
    public static final int UBER_TRACING = 5;
    
    /**
     * Build a log message for the current <code>{@link Loggable}</code>
     * 
     * @param objs
     * @return
     */
    public String getLogMessage(Object ... objs);
    
    /**
     * Report what level to output the stack at. The valid possible outputs are:
     * <br/>
     * <table>
     *   <tr>
     *     <th>Stack Output Level</th><th>Description</th>
     *   </tr>
     *   <tr>
     *     <td>0</td><td>No Stack Reporting</td>
     *     <td>1</td><td>Limited Tracing - method and class name reporting.</td>
     *     <td>2</td><td>Basic Tracing - previous two method calls </td>
     *     <td>3</td><td>Entry Tracing - all method calls within the same class, and the calling method from an external class.</td>
     *     <td>4</td><td>Advanced Tracing - Same as Entry Tracing, but with line numbers</td>
     *     <td>5</td><td>Uber Tracing - Outputs the entire trace</td>
     *   </tr>
     * </table>
     * 
     * @return int the level used for outputting the stack
     */
    public int getStackOutputLevel();
}
