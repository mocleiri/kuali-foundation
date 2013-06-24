-----------------------------------------------------------------------------
-- KRCR_CMPNT_SET_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRCR_CMPNT_SET_T';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRCR_CMPNT_SET_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRCR_CMPNT_SET_T
(
      CMPNT_SET_ID VARCHAR2(40)
        , LAST_UPDT_TS DATE NOT NULL
        , CHKSM VARCHAR2(40) NOT NULL
        , VER_NBR NUMBER(8) default 0 NOT NULL
    

)
/

ALTER TABLE KRCR_CMPNT_SET_T
    ADD CONSTRAINT KRCR_CMPNT_SET_TP1
PRIMARY KEY (CMPNT_SET_ID)
/







-----------------------------------------------------------------------------
-- KRCR_CMPNT_T
-----------------------------------------------------------------------------
DECLARE temp NUMBER;
BEGIN
	SELECT COUNT(*) INTO temp FROM user_tables WHERE table_name = 'KRCR_CMPNT_T';
	IF temp > 0 THEN EXECUTE IMMEDIATE 'DROP TABLE KRCR_CMPNT_T CASCADE CONSTRAINTS PURGE'; END IF;
END;
/

CREATE TABLE KRCR_CMPNT_T
(
      NMSPC_CD VARCHAR2(20)
        , CMPNT_CD VARCHAR2(100)
        , OBJ_ID VARCHAR2(36) default SYS_GUID() NOT NULL
        , VER_NBR NUMBER(8) default 1 NOT NULL
        , NM VARCHAR2(255)
        , ACTV_IND CHAR(1) default 'Y' NOT NULL
    
    , CONSTRAINT KRNS_PARM_DTL_TYP_TC0 UNIQUE (OBJ_ID)

)
/

ALTER TABLE KRCR_CMPNT_T
    ADD CONSTRAINT KRCR_CMPNT_TP1
PRIMARY KEY (NMSPC_CD,CMPNT_CD)
/
