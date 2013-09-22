--
-- Copyright 2010-2013 The Kuali Foundation
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

TRUNCATE TABLE PUR_VNDR_CONTR_T DROP STORAGE
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1000.0,'BD7D8FD7-C6B4-1E2D-87BD-D95FC16491EF',2.0,1001.0,0.0,'ANIMALRES','Animal Research','BL',TO_DATE( '20070601000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20070630000000', 'YYYYMMDDHH24MISS' ),14.0,'CON','00N10','N',500.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1009.0,'4E9F7F03-2EBA-06DB-A6B4-BDE8338FCC31',2.0,1011.0,0.0,'3344','Fall Negotiations','IN',TO_DATE( '20070501000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20090430000000', 'YYYYMMDDHH24MISS' ),14.0,'QUOT','10D10','N',6000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1013.0,'000D7A2B-5CD9-9F3B-8019-1AB92A75D907',2.0,1014.0,0.0,'8898','Good Treats','UA',TO_DATE( '20060101000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20101231000000', 'YYYYMMDDHH24MISS' ),16.0,'EST','10D10','N',900.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1014.0,'799AA12C-4245-1920-127E-C9C40194D448',2.0,1015.0,0.0,'IU WIDE','IU contract for all campuses regarding this PO vendor','IN',TO_DATE( '20070501000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20080430000000', 'YYYYMMDDHH24MISS' ),16.0,'VEN','10D30','N',1000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1015.0,'82897694-3DE3-4DC8-0295-CE3F4A6C7E06',1.0,1014.0,3.0,'3399','Good Things','BL',TO_DATE( '20030101000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '29331231000000', 'YYYYMMDDHH24MISS' ),10.0,'QUOT','05D10','N',3300.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_SHP_PMT_TERM_CD,VNDR_SHP_TTL_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1016.0,'84366F91-B2EF-1B33-64C3-9D678AF501C9',2.0,1017.0,0.0,'STOKERCOAL','Stoke Coal Shipment Winter','CO',TO_DATE( '20061101000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20070508000000', 'YYYYMMDDHH24MISS' ),99.0,'VEN','10D15','CL','DE','N',1000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1017.0,'2A0DA5F4-13EF-D6FF-C09C-4CB2E4E2E632',2.0,1017.0,0.0,'COALSPRING','Coal Shipment Spring','OC',TO_DATE( '20070321000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20070621000000', 'YYYYMMDDHH24MISS' ),99.0,'VEN','10D05','N',1000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1018.0,'20E20F5D-6C01-C7F9-1E98-297BB4DE163B',2.0,1018.0,0.0,'TRUCKMAINTENANCE','Small Truck Maintenance','EA',TO_DATE( '20070508000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20070630000000', 'YYYYMMDDHH24MISS' ),10.0,'QUOT','00N30','N',100.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1019.0,'73E2B7CD-99EB-4C34-3FF4-BDD117DEDEBE',2.0,1018.0,0.0,'CONSTUCTIONVEH','Construction Vehicles','EA',TO_DATE( '20070508000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20070630000000', 'YYYYMMDDHH24MISS' ),10.0,'QUOT','00N30','N',100.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_SHP_PMT_TERM_CD,VNDR_SHP_TTL_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1020.0,'1D6AD5CC-65D3-DFB1-327A-E552B7037077',2.0,1020.0,0.0,'SPRING PROJECT','FILM FOR PHOTOS','BL',TO_DATE( '20070501000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20080501000000', 'YYYYMMDDHH24MISS' ),12.0,'COOP','00N30','AL','DE','N',1000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (1021.0,'AF740A6F-95B2-21F2-CE16-1E9FAB3BF900',4.0,1000.0,0.0,'PARTS','abc parts','BL',TO_DATE( '20070508000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20090508000000', 'YYYYMMDDHH24MISS' ),10.0,'VEN','00N07','N',100000.0,'N')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (2001.0,'3D64E7F1-41F5-BA53-60E5-84054F72CE67',1.0,1010.0,3.0,'ABCD','A contract','BL',TO_DATE( '20060701000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20100630000000', 'YYYYMMDDHH24MISS' ),10.0,'QUOT','10D11','N',30000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_SHP_PMT_TERM_CD,VNDR_SHP_TTL_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (2003.0,'913A95CD-9125-1FF6-EB0A-924B57DD5FEE',5.0,2003.0,0.0,'CONSTRUCTION','Physical Plant','BL',TO_DATE( '20070708000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20100819000000', 'YYYYMMDDHH24MISS' ),12.0,'VEN','20D10','PA','DE','N',2000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_SHP_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (2004.0,'9449D667-A370-936E-6CC7-ACB4380229D5',6.0,2013.0,0.0,'WATER','Besco Water','BL',TO_DATE( '20070701000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20080630000000', 'YYYYMMDDHH24MISS' ),10.0,'VEN','00N07','PA','N',2000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_SHP_PMT_TERM_CD,VNDR_SHP_TTL_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (2009.0,'8D7F7C81-6DB4-DDA2-FEB6-B4F6B5D2C7BD',2.0,2021.0,0.0,'COFFEE CUP CAFE','Coffee Supplies','BL',TO_DATE( '20070701000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20080630000000', 'YYYYMMDDHH24MISS' ),10.0,'VEN','10D10','AL','SP','N',1000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_SHP_PMT_TERM_CD,VNDR_SHP_TTL_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (2010.0,'C2FDF181-7282-831F-88F1-DF67BD618A06',3.0,2023.0,0.0,'TOOLS','Grainger - Tools','BL',TO_DATE( '20070701000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20080630000000', 'YYYYMMDDHH24MISS' ),16.0,'QUOT','00N30','PA','SP','N',1000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_SHP_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (4005.0,'77983318-A2B8-1433-7192-FA762D7D91B8',1.0,4005.0,0.0,'bn.com books','B2B books','BL',TO_DATE( '20081001000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20281001000000', 'YYYYMMDDHH24MISS' ),10.0,'CON','00N30','PA','Y',15000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (4006.0,'EE4B9496-5E13-1A04-85E8-F1AFCE3200BE',1.0,4006.0,0.0,'dell','dell','BL',TO_DATE( '20081001000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20281001000000', 'YYYYMMDDHH24MISS' ),10.0,'EST','00N20','Y',10000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (4007.0,'2CBFBFD6-4048-815B-06E9-3D665439E6C9',1.0,4007.0,0.0,'usa-b2b','usa scientific','BL',TO_DATE( '20081001000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20281001000000', 'YYYYMMDDHH24MISS' ),10.0,'EST','00N20','Y',10000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (4008.0,'62B57143-4B61-377B-7919-879CBF27D065',1.0,4008.0,0.0,'qiagen','qiagen-b2b','BL',TO_DATE( '20081001000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20281001000000', 'YYYYMMDDHH24MISS' ),10.0,'EST','00N20','Y',10000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (4009.0,'22495D37-AB32-BE7A-8CEA-D0B070F35ED4',2.0,4009.0,0.0,'pierce','pierce-b2b','BL',TO_DATE( '20081001000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20281001000000', 'YYYYMMDDHH24MISS' ),10.0,'QUOT','00N45','Y',10000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (4010.0,'092F8DD2-1A8F-F88E-85F7-3CA309C490D5',1.0,4010.0,0.0,'perkin','perkin-b2b','BL',TO_DATE( '20081001000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20281001000000', 'YYYYMMDDHH24MISS' ),10.0,'QUOT','00N45','Y',10000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (4011.0,'B435F28D-0477-2AB8-1269-489F6E61AE18',1.0,4010.0,0.0,'perkin-in','perkin-b2b (IN)','IN',TO_DATE( '20081001000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20281001000000', 'YYYYMMDDHH24MISS' ),12.0,'QUOT','00N20','Y',20000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (4012.0,'6BA03109-B850-02C6-09F0-45FC8474FB34',1.0,4010.0,0.0,'perkin-ea','perkin-b2b (EA)','EA',TO_DATE( '20081001000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20281001000000', 'YYYYMMDDHH24MISS' ),12.0,'QUOT','00N14','Y',3000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (4013.0,'FA067A8D-9F53-91C3-122D-541B79BB9C93',1.0,4011.0,0.0,'fisher','Fisher','BL',TO_DATE( '20081001000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20281001000000', 'YYYYMMDDHH24MISS' ),10.0,'EST','00N20','Y',10000.0,'Y')
/
INSERT INTO PUR_VNDR_CONTR_T (VNDR_CONTR_GNRTD_ID,OBJ_ID,VER_NBR,VNDR_HDR_GNRTD_ID,VNDR_DTL_ASND_ID,VNDR_CONTR_NM,VNDR_CONTR_DESC,VNDR_CMP_CD,VNDR_CONTR_BEG_DT,VNDR_CONTR_END_DT,CONTR_MGR_CD,PO_CST_SRC_CD,VNDR_PMT_TERM_CD,VNDR_B2B_IND,ORG_AUTO_PO_LMT,DOBJ_MAINT_CD_ACTV_IND)
  VALUES (4014.0,'B062600C-2317-4E28-D18B-0B23EB621847',1.0,4012.0,0.0,'Grainger','Grainger','BL',TO_DATE( '20081001000000', 'YYYYMMDDHH24MISS' ),TO_DATE( '20281001000000', 'YYYYMMDDHH24MISS' ),10.0,'EST','00N14','Y',10000.0,'Y')
/
