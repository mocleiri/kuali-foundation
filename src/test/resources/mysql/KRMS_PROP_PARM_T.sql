--
-- Copyright 2010-2014 The Kuali Foundation
--
-- Licensed under the Educational Community License, Version 2.0 (the "License");
-- you may not use this file except in compliance with the License.
-- You may obtain a copy of the License at
--
-- http://www.opensource.org/licenses/ecl2.php
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an "AS IS" BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
--

drop table if exists KRMS_PROP_PARM_T
;

CREATE TABLE KRMS_PROP_PARM_T (PROP_PARM_ID VARCHAR(40)
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

SET autocommit = 0
;

INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10000','10000','10000',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10000','10001',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10000','10002',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10001','10001','10003',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10001','10004',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10001','10005',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10002','10002','10006',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10002','10007',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10002','10008',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10003','10003','10009',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10003','10010',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10003','10011',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10004','10004','10012',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10004','10013',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10004','10014',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10005','10005','10015',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10005','10016',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10005','10017',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10006','10007','10018',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10007','10019',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10007','10020',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10007','10008','10021',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10008','10022',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10008','10023',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10008','10010','10024',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10010','10025',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10010','10026',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10009','10011','10027',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10011','10028',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10011','10029',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10010','10012','10030',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10012','10031',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10012','10032',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10011','10014','10033',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10014','10034',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10014','10035',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10012','10015','10036',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','1','10015','10037',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10015','10038',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10013','10017','10039',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','1','10017','10040',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10017','10041',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10014','10018','10042',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10018','10043',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10018','10044',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10015','10019','10045',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10019','10046',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10019','10047',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10016','10020','10048',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10020','10049',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10020','10050',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10017','10022','10051',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','1','10022','10052',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10022','10053',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10018','10023','10054',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10023','10055',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10023','10056',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10019','10024','10057',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10024','10058',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10024','10059',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10020','10026','10060',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10026','10061',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10026','10062',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10021','10027','10063',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10027','10064',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10027','10065',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10022','10029','10066',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10029','10067',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10029','10068',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10023','10031','10069',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10031','10070',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10031','10071',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10024','10032','10072',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10032','10073',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10032','10074',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10025','10033','10075',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10033','10076',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10033','10077',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10026','10034','10078',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10034','10079',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10034','10080',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10027','10035','10081',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10035','10082',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10035','10083',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10028','10037','10084',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10037','10085',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10037','10086',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10029','10038','10087',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','1','10038','10088',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10038','10089',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10030','10039','10090',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10039','10091',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10039','10092',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10031','10040','10093',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10040','10094',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10040','10095',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10032','10041','10096',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10041','10097',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10041','10098',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10033','10042','10099',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10042','10100',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10042','10101',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10034','10043','10102',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10043','10103',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10043','10104',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10035','10044','10105',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10044','10106',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10044','10107',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10036','10045','10108',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10045','10109',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10045','10110',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10037','10046','10111',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10046','10112',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10046','10113',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10038','10047','10114',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10047','10115',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10047','10116',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10039','10048','10117',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10048','10118',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10048','10119',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10040','10049','10120',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10049','10121',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10049','10122',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10041','10050','10123',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10050','10124',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10050','10125',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10042','10051','10126',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10051','10127',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10051','10128',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10043','10052','10129',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10052','10130',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10052','10131',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10044','10054','10132',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','1','10054','10133',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10054','10134',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10045','10055','10135',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10055','10136',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10055','10137',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10046','10056','10138',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','1','10056','10139',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10056','10140',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10047','10057','10141',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10057','10142',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10057','10143',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10048','10059','10144',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10059','10145',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10059','10146',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10049','10060','10147',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10060','10148',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10060','10149',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10050','10062','10150',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10062','10151',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10062','10152',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10051','10063','10153',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10063','10154',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10063','10155',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10052','10065','10156',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10065','10157',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10065','10158',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10053','10066','10159',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10066','10160',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10066','10161',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10054','10068','10162',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10068','10163',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10068','10164',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10055','10069','10165',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10069','10166',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10069','10167',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10056','10070','10168',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10070','10169',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10070','10170',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10057','10071','10171',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10071','10172',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10071','10173',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10058','10073','10174',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','1','10073','10175',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10073','10176',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10059','10074','10177',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10074','10178',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10074','10179',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10060','10076','10180',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10076','10181',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10076','10182',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10061','10077','10183',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','1','10077','10184',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10077','10185',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10062','10078','10186',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','1','10078','10187',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10078','10188',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10063','10079','10189',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10079','10190',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10079','10191',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10064','10080','10192',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10080','10193',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10080','10194',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10065','10081','10195',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10081','10196',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10081','10197',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10066','10083','10198',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','1','10083','10199',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10083','10200',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10067','10084','10201',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10084','10202',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10084','10203',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10068','10085','10204',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10085','10205',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10085','10206',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10069','10086','10207',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10086','10208',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10086','10209',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10070','10087','10210',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10087','10211',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10087','10212',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10071','10088','10213',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10088','10214',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10088','10215',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10072','10089','10216',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10089','10217',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10089','10218',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10073','10091','10219',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','1','10091','10220',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10091','10221',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10074','10092','10222',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10092','10223',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10092','10224',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10075','10093','10225',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10093','10226',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10093','10227',3,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('T','10076','10095','10228',1,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('C','true','10095','10229',2,1)
;
INSERT INTO KRMS_PROP_PARM_T (PARM_TYP_CD,PARM_VAL,PROP_ID,PROP_PARM_ID,SEQ_NO,VER_NBR)
  VALUES ('O','=','10095','10230',3,1)
;
commit
;