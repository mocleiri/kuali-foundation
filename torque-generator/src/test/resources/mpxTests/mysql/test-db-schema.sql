CREATE TABLE FOO_T
(
      ID VARCHAR(36)
        , CREATETIME TIMESTAMP NOT NULL
        , NAME VARCHAR(255)

        , CONSTRAINT FOO_TP1 PRIMARY KEY(ID)

) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/

CREATE TABLE BAR_T
(
      ID VARCHAR(36)
        , CREATETIME TIMESTAMP NOT NULL
        , NAME VARCHAR(255)
        , FOO_ID VARCHAR(36)

        , CONSTRAINT BAR_TP1 PRIMARY KEY(ID)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/

CREATE TABLE BAZ_T
(
      ID VARCHAR(36)
        , XML LONGTEXT NOT NULL
        , CREATETIME TIMESTAMP NOT NULL
        , NAME VARCHAR(255)
        , FOO_ID VARCHAR(36)

        , CONSTRAINT BAZ_TP1 PRIMARY KEY(ID)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/

-- complex clob example:  2 pks, 2 clob columns
CREATE TABLE COMPLEX_BAZ_T
(
      ID_ONE VARCHAR(36)
        , ID_TWO VARCHAR(36)
        , XML LONGTEXT NOT NULL
        , COMPLEX_DATA LONGTEXT NOT NULL
        , CREATETIME TIMESTAMP NOT NULL
        , NAME VARCHAR(255)
        , FOO_ID VARCHAR(36)

        , CONSTRAINT COMPLEX_BAZ_TP1 PRIMARY KEY(ID_ONE, ID_TWO)
) ENGINE InnoDB CHARACTER SET utf8 COLLATE utf8_bin
/
