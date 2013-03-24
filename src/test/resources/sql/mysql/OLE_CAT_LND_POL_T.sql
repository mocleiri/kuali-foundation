TRUNCATE TABLE OLE_CAT_LND_POL_T
/
INSERT INTO OLE_CAT_LND_POL_T (LND_POL_ID,LND_POL_CD,LND_POL_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES (1.0,'a','Will Lend','MFHD 008-20 http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','febedf5c-7e31-4af5-b3ce-05a100af895a',1.0)
/
INSERT INTO OLE_CAT_LND_POL_T (LND_POL_ID,LND_POL_CD,LND_POL_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES (2.0,'b','Will Not Lend','MFHD 008-20 http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','6437c4df-471e-4192-838b-0d3942b5da75',1.0)
/
INSERT INTO OLE_CAT_LND_POL_T (LND_POL_ID,LND_POL_CD,LND_POL_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES (3.0,'c','Will lend hard copy only','MFHD 008-20 http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','3bdad86c-a6c2-4332-80db-e9fcbbb8fa36',1.0)
/
INSERT INTO OLE_CAT_LND_POL_T (LND_POL_ID,LND_POL_CD,LND_POL_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES (4.0,'l','Limited lending policy','MFHD 008-20 http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','acbadb5f-47da-49fd-8fda-c13e5710898f',1.0)
/
INSERT INTO OLE_CAT_LND_POL_T (LND_POL_ID,LND_POL_CD,LND_POL_NM,SRC,SRC_DT,ROW_ACT_IND,OBJ_ID,VER_NBR)
  VALUES (5.0,'u','unknown','MFHD 008-20 http://www.loc.gov/marc/holdings/hd008.html',STR_TO_DATE( '20120322000000', '%Y%m%d%H%i%s' ),'Y','3ff8b76a-780c-4ea2-98a1-c17e09938c92',1.0)
/
