TRUNCATE TABLE OLE_CAT_LOC_STATUS_T DROP STORAGE
/
INSERT INTO OLE_CAT_LOC_STATUS_T (LOC_STATUS_ID,OBJ_ID,VER_NBR,LOC_STATUS_CD,LOC_STATUS_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES ('1','4dcdf94f-fc19-40f4-86b6-9a3a66d42bda',3.0,'Permanent','Permanent','IU Sample',TO_DATE( '20120517000000', 'YYYYMMDDHH24MISS' ),'Y')
/
INSERT INTO OLE_CAT_LOC_STATUS_T (LOC_STATUS_ID,OBJ_ID,VER_NBR,LOC_STATUS_CD,LOC_STATUS_NM,SRC,SRC_DT,ROW_ACT_IND)
  VALUES ('2','8598b141-cdfe-41bf-ba77-6eaeb344e8b4',1.0,'Temporary','Temporary','IU Sample',TO_DATE( '20120517000000', 'YYYYMMDDHH24MISS' ),'Y')
/
