package org.kuali.db.jdbc;

/*
 * import java.util.Properties; import java.util.Set;
 * 
 * import org.junit.Test; import org.kuali.spring.util.ResolvePropertiesFirstPlaceholderConfigurer; import
 * org.kuali.spring.util.PropertyPlaceholderHelper; import org.kuali.spring.util.PropertiesLoggerSupport; import
 * org.slf4j.Logger; import org.slf4j.LoggerFactory;
 */

public class Spring5369 {
    /*
     * final Logger logger = LoggerFactory.getLogger(Spring5369.class);
     * 
     * protected Properties getProps1() { Properties props = new Properties(); props.put("jdbc.vendor", "mysql");
     * props.put("jdbc.sql", "${jdbc.${jdbc.vendor}.sql}"); props.put("jdbc.user", "${jdbc.${jdbc.vendor}.user}");
     * props.put("jdbc.password", "${jdbc.${jdbc.vendor}.password}"); props.put("jdbc.database",
     * "${jdbc.${jdbc.vendor}.database}"); props.put("jdbc.oracle.database", "MYDB"); props.put("jdbc.oracle.user",
     * "${jdbc.oracle.database}"); props.put("jdbc.oracle.password", "${jdbc.oracle.database}");
     * props.put("jdbc.oracle.sql", "CREATE USER ${jdbc.user} IDENTIFIED BY ${jdbc.user}");
     * props.put("jdbc.mysql.database", "mydb"); props.put("jdbc.mysql.user", "${jdbc.database}");
     * props.put("jdbc.mysql.password", "${jdbc.database}"); props.put("jdbc.mysql.sql",
     * "GRANT ALL ON ${jdbc.database}.* TO '${jdbc.user}'@'%' IDENTIFIED BY '${jdbc.password}'"); return props; }
     * 
     * @Test public void testSpring5369_2() { try { Properties props = getProps1();
     * ResolvePropertiesFirstPlaceholderConfigurer ppc = new ResolvePropertiesFirstPlaceholderConfigurer();
     * PropertiesLoggerSupport loggerSupport = new PropertiesLoggerSupport();
     * loggerSupport.setFlattenPropertyValues(true); loggerSupport.setMaskPropertyValues(false);
     * logger.info(loggerSupport.getLogEntry(props, "Nested Properties for testing Spring issue 5369"));
     * ppc.setLoggerSupport(loggerSupport); ppc.convertProperties(props); } catch (Exception e) { e.printStackTrace(); }
     * }
     * 
     * public void testSpring5369() { try { String oracleUser = "MYDB"; String oraclePassword = oracleUser; String
     * expectedSql = getOracleSql(oracleUser, oraclePassword); String placeHolderSql = getOracleSql("${jdbc.user}",
     * "${jdbc.password}"); Properties props = new Properties(); props.put("jdbc.vendor", "oracle");
     * props.put("jdbc.user", "${jdbc.${jdbc.vendor}.user}"); props.put("jdbc.password",
     * "${jdbc.${jdbc.vendor}.password}"); props.put("jdbc.sql", "${jdbc.${jdbc.vendor}.sql}");
     * props.put("jdbc.oracle.user", oracleUser); props.put("jdbc.oracle.password", oraclePassword);
     * props.put("jdbc.oracle.sql", placeHolderSql); props.put("jdbc.${jdbc.vendor}.foo", "${jdbc.${jdbc.vendor}.sql}");
     * // props.put("jdbc.mysql.sql", "DROP DATABASE ${jdbc.user} / CREATE DATABASE ${jdbc.user} /"); //
     * props.put("jdbc.mysql.user", "mydb"); // props.put("jdbc.mysql.password", "mydb"); PropertiesLoggerSupport
     * loggerSupport = new PropertiesLoggerSupport(); loggerSupport.setFlattenPropertyValues(true);
     * loggerSupport.setMaskPropertyValues(false); logger.info(loggerSupport.getLogEntry(props,
     * "Nested Properties for testing Spring issue 5369")); PropertyPlaceholderHelper helper = new
     * PropertyPlaceholderHelper(false); // This fails throwing IllegalArgumentException //
     * helper.replacePlaceholders("${jdbc.ice.cream=rockyroad}", props); helper.replacePlaceholders("${jdbc.sql}",
     * props); // Assert.assertEquals(expectedSql, actualSql); Set<String> names = props.stringPropertyNames();
     * Properties resolvedProperties = new Properties(); for (String name : names) { String resolvedName =
     * helper.replacePlaceholders(name, props); String property = props.getProperty(name); String resolvedProperty =
     * helper.replacePlaceholders(property, props); resolvedProperties.setProperty(resolvedName, resolvedProperty); }
     * logger.info(loggerSupport.getLogEntry(resolvedProperties)); } catch (Exception e) { e.printStackTrace(); } }
     * 
     * protected String getOracleSql(String user, String password) { return "DROP USER " + user +
     * " CASCADE / CREATE USER " + user + " IDENTIFIED BY " + password + " /"; }
     */
}
