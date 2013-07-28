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
package org.kuali.common.util.project;

/**
 * The project identifier concept embodied by this interface is based on two facts.
 * 
 * <p>
 * 1 - All Kuali projects produce only one artifact containing executable java code and associated resources.<br>
 * 2 - There is only ever one version of any given Kuali project in the java classpath.<br>
 * </p>
 * 
 * <p>
 * Thus, groupId + artifactId is a simple way to uniquely identify a project. It also provides a simple way to uniquely address resources inside a project assuming the directory
 * structure includes groupId + artifactId.<br>
 * 
 * For example:
 * 
 * <pre>
 *   src/main/resources/org/kuali/common/kuali-util/foo.txt
 *   src/main/resources/org/kuali/common/kuali-util/bar.txt
 * </pre>
 * 
 * </p>
 */
public interface ProjectIdentifier {

	String getGroupId();

	String getArtifactId();

}
