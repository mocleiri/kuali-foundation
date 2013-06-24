drop table if exists FOO
/

CREATE TABLE FOO (CMPNT_SET_ID VARCHAR(40)
        , LAST_UPDT_TS DATETIME NOT NULL
        , CHKSM VARCHAR(40) NOT NULL
        , VER_NBR DECIMAL(8) default 0 NOT NULL
    , CONSTRAINT FOO_P1 PRIMARY KEY(CMPNT_SET_ID)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/


drop table if exists BAR
/

CREATE TABLE BAR (NMSPC_CD VARCHAR(20)
        , CMPNT_CD VARCHAR(100)
        , OBJ_ID VARCHAR(36) NOT NULL
        , VER_NBR DECIMAL(8) default 1 NOT NULL
        , NM VARCHAR(255)
        , ACTV_IND CHAR(1) default 'Y' NOT NULL
    , CONSTRAINT BAR_P1 PRIMARY KEY(NMSPC_CD,CMPNT_CD)
    , CONSTRAINT KRNS_PARM_DTL_TYP_TC0 UNIQUE (OBJ_ID)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/