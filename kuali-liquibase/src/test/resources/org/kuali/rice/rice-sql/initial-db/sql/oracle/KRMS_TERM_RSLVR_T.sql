TRUNCATE TABLE KRMS_TERM_RSLVR_T DROP STORAGE
/
INSERT INTO KRMS_TERM_RSLVR_T (ACTV,NM,NMSPC_CD,OUTPUT_TERM_SPEC_ID,TERM_RSLVR_ID,TYP_ID,VER_NBR)
  VALUES ('Y','campusSizeResolver','KR-RULE-TEST','T1000','T1000','T1000',1)
/
INSERT INTO KRMS_TERM_RSLVR_T (ACTV,NM,NMSPC_CD,OUTPUT_TERM_SPEC_ID,TERM_RSLVR_ID,TYP_ID,VER_NBR)
  VALUES ('Y','orgMemberResolver','KR-RULE-TEST','T1001','T1001','T1000',1)
/
