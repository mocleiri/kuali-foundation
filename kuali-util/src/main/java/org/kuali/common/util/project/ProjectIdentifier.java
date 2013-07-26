package org.kuali.common.util.project;

/**
 * The project identifier concept embodied by this interface is based on two facts.
 * 
 * <p>
 * 1 - Kuali projects produce at most one artifact containing executable java code and associated resources.<br>
 * 2 - There is only ever one version of a Kuali project in the java classpath.<br>
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
