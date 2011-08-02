no SYS_GUID in mysql  (basically, no procedural default column values in mysql)
common data types

SYSDATE in oracle = CURRENT_TIMESTAMP in mysql

if a column is defaulted to CURRENT_TIMESTAMP in mysql, no other columns in that table can be of the type TIMESTAMP

Maximum index key is 1000 bytes in mysql, so some indicies are based on columns that are more than 1000 bytes long
	may be able to use prefix index keys

	1) KREW_DOC_HDR_EXT_T, index built for KEY_CD and VAL.  VAL is too large, changed to 500 bytes
	2) KREW_RTE_BRCH_ST_T, index built for KEY_CD and VAL.  VAL is too large, changed to 500 bytes
	3) KREW_RTE_NODE_INSTN_ST_T, index built for KEY_CD and VAL.  VAL is too large, changed to 500 bytes
	4) KREW_RULE_EXT_VAL_T, index built for KEY_CD and RULE_EXT_VAL_ID.  KEY_CD is too large, changed to 500 bytes
	5) KRSB_BAM_T, index built for SVC_NM and MTHD_NM.  MTHD_NM is too large, changed to 500 bytes
	6) KRSB_MSG_QUE_T, index built for SVC_MTHD_NM and SVC_NM.  SVC_MTHD_NM is too large, changed to 500 bytes
	6) KRSB_MSG_QUE_T, index built for APPL_ID, STAT_CD, IP_NBR, and DT.  IP_NBR is too large, changed to 500 bytes
	
KSRB_QRTZ time fields need to be BIGINT


perform all db constraints after the data has been loaded



--- Mysql setup for KS
add mysql equivalent dialect, url, and driver entries in ks-core-config.xml, ks-lum-config.xml, and ks-enroll-config.xml

in ks-config.xml, switch these:
	<param name="jpa.DatabasePlatform">org.hibernate.dialect.Oracle10gDialect</param>   
	<param name="datasource.driver.name">oracle.jdbc.OracleDriver</param>
	<param name="datasource.url">jdbc:oracle:thin:@localhost:1521:XE</param>
	
	to these:
	
	<param name="jpa.DatabasePlatform">org.hibernate.dialect.MySQLDialect</param>
	<param name="datasource.driver.name">com.mysql.jdbc.Driver</param>
	<param name="datasource.url">jdbc:mysql://localhost/KSEMBEDDED</param> 


In all the above config files, set the db.vendor param to mysql