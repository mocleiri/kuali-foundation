package org.kuali.common.util.log4j;

/**
 * @deprecated
 */
@Deprecated
public abstract class Log4JPatternConstants {

	/**
	 * <pre>
	 *  [&lt;level>] &lt;class> - &lt;message>
	 * </pre>
	 */
	public static final String DEFAULT = "[%-4p] %C - %m%n";

	/**
	 * <pre>
	 *  [&lt;level>] &lt;class>.&lt;method> - &lt;message>
	 * </pre>
	 */
	public static final String DEBUG = "[%-4p] %C.%M - %m%n";

	/**
	 * <pre>
	 *  [&lt;level>] &lt;message>
	 * </pre>
	 */
	public static final String MAVEN = "[%-4p] %m%n";

}
