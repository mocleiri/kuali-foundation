TRUNCATE TABLE KRMS_ATTR_DEFN_T
/
INSERT INTO KRMS_ATTR_DEFN_T (ACTV,ATTR_DEFN_ID,DESC_TXT,LBL,NM,NMSPC_CD,VER_NBR)
  VALUES ('Y','1000','An identifier for a PeopleFlow','PeopleFlow','peopleFlowId','KR-RULE',0)
/
INSERT INTO KRMS_ATTR_DEFN_T (ACTV,ATTR_DEFN_ID,DESC_TXT,LBL,NM,NMSPC_CD,VER_NBR)
  VALUES ('Y','1001','If true, execute the action','Invalid Rule','ruleTypeCode','KR-RULE',1)
/
INSERT INTO KRMS_ATTR_DEFN_T (ACTV,ATTR_DEFN_ID,DESC_TXT,LBL,NM,NMSPC_CD,VER_NBR)
  VALUES ('Y','1004','Error','Error Action','actionTypeCode','KR-RULE',1)
/
INSERT INTO KRMS_ATTR_DEFN_T (ACTV,ATTR_DEFN_ID,DESC_TXT,LBL,NM,NMSPC_CD,VER_NBR)
  VALUES ('Y','1005','Message validation action returns','Action Message','actionMessage','KR-RULE',1)
/
INSERT INTO KRMS_ATTR_DEFN_T (ACTV,ATTR_DEFN_ID,DESC_TXT,LBL,NM,NMSPC_CD,VER_NBR)
  VALUES ('Y','1006','PeopleFlow Name','PeopleFlow Name','peopleFlowName','KR-RULE',1)
/
INSERT INTO KRMS_ATTR_DEFN_T (ACTV,ATTR_DEFN_ID,DESC_TXT,LBL,NM,NMSPC_CD,VER_NBR)
  VALUES ('Y','T1000','null','Context 1 Qualifier','Context1Qualifier','KR-RULE-TEST',1)
/
INSERT INTO KRMS_ATTR_DEFN_T (ACTV,ATTR_DEFN_ID,DESC_TXT,LBL,NM,NMSPC_CD,VER_NBR)
  VALUES ('Y','T1001','null','Event Name','Event','KR-RULE-TEST',1)
/
INSERT INTO KRMS_ATTR_DEFN_T (ACTV,ATTR_DEFN_ID,DESC_TXT,LBL,NM,NMSPC_CD,VER_NBR)
  VALUES ('Y','T1002','this is an optional attribute for testing','label','Optional Test Attribute','KR-RULE-TEST',0)
/
INSERT INTO KRMS_ATTR_DEFN_T (ACTV,ATTR_DEFN_ID,DESC_TXT,LBL,NM,NMSPC_CD,VER_NBR)
  VALUES ('Y','T1003','the campus which this agenda is valid for','campus label','Campus','KR-RULE-TEST',0)
/
