drop table if exists KRMS_PROP_PARM_T
;

CREATE TABLE KRMS_PROP_PARM_T
(
      PROP_PARM_ID VARCHAR(40)
        , PROP_ID VARCHAR(40) NOT NULL
        , PARM_VAL VARCHAR(255)
        , PARM_TYP_CD VARCHAR(1) NOT NULL
        , SEQ_NO DECIMAL(5) NOT NULL
        , VER_NBR DECIMAL(8) default 0 NOT NULL

    , CONSTRAINT KRMS_PROP_PARM_TP1 PRIMARY KEY(PROP_PARM_ID)


    , INDEX KRMS_PROP_PARM_TI1 (PROP_ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
;

TRUNCATE TABLE KRMS_PROP_PARM_T
;

INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10000','10000','10000',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10000','10001',2,1)
;
