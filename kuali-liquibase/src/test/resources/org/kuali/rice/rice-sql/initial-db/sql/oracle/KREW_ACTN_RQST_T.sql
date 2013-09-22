TRUNCATE TABLE KREW_ACTN_RQST_T DROP STORAGE
/
INSERT INTO KREW_ACTN_RQST_T (ACTN_RQST_CD,ACTN_RQST_ID,ACTN_TKN_ID,APPR_PLCY,CRTE_DT,CUR_IND,DOC_HDR_ID,DOC_VER_NBR,FRC_ACTN,PRIO_NBR,PRNCPL_ID,RECIP_TYP_CD,RSP_DESC_TXT,RSP_ID,RTE_LVL_NBR,RTE_NODE_INSTN_ID,RULE_ID,STAT_CD,VER_NBR)
  VALUES ('A','2360','2324','F',TO_DATE( '20081222132425', 'YYYYMMDDHH24MISS' ),1,'2695',1,0,1,'user4','U','Destination Rule','2022',1,'2876','1046','D',0)
/
INSERT INTO KREW_ACTN_RQST_T (ACTN_RQST_CD,ACTN_RQST_ID,ACTN_TKN_ID,APPR_PLCY,CRTE_DT,CUR_IND,DOC_HDR_ID,DOC_VER_NBR,FRC_ACTN,PRIO_NBR,QUAL_ROLE_NM,QUAL_ROLE_NM_LBL_TXT,RECIP_TYP_CD,ROLE_NM,RSP_DESC_TXT,RSP_ID,RTE_LVL_NBR,RTE_NODE_INSTN_ID,RULE_ID,STAT_CD,VER_NBR)
  VALUES ('A','2361','2325','F',TO_DATE( '20081222132605', 'YYYYMMDDHH24MISS' ),1,'2695',1,0,1,'employee employee','employee','R','employee','Traveler Routing','2024',2,'2877','1049','D',0)
/
INSERT INTO KREW_ACTN_RQST_T (ACTN_RQST_CD,ACTN_RQST_ID,ACTN_TKN_ID,APPR_PLCY,CRTE_DT,CUR_IND,DOC_HDR_ID,DOC_VER_NBR,FRC_ACTN,PARNT_ID,PRIO_NBR,PRNCPL_ID,QUAL_ROLE_NM,QUAL_ROLE_NM_LBL_TXT,RECIP_TYP_CD,ROLE_NM,RSP_DESC_TXT,RSP_ID,RTE_LVL_NBR,RTE_NODE_INSTN_ID,RULE_ID,STAT_CD,VER_NBR)
  VALUES ('A','2362','2325','F',TO_DATE( '20081222132605', 'YYYYMMDDHH24MISS' ),1,'2695',1,0,'2361',1,'employee','employee employee','employee','U','employee','Traveler Routing','2024',2,'2877','1049','D',0)
/
INSERT INTO KREW_ACTN_RQST_T (ACTN_RQST_CD,ACTN_RQST_ID,ACTN_TKN_ID,APPR_PLCY,CRTE_DT,CUR_IND,DOC_HDR_ID,DOC_VER_NBR,FRC_ACTN,PRIO_NBR,QUAL_ROLE_NM,QUAL_ROLE_NM_LBL_TXT,RECIP_TYP_CD,ROLE_NM,RSP_DESC_TXT,RSP_ID,RTE_LVL_NBR,RTE_NODE_INSTN_ID,RULE_ID,STAT_CD,VER_NBR)
  VALUES ('A','2363','2326','F',TO_DATE( '20081222132635', 'YYYYMMDDHH24MISS' ),1,'2695',1,0,1,'supervisr supervisr','supervisr','R','supervisr','Supervisor Routing','2026',3,'2878','1050','D',0)
/
INSERT INTO KREW_ACTN_RQST_T (ACTN_RQST_CD,ACTN_RQST_ID,ACTN_TKN_ID,APPR_PLCY,CRTE_DT,CUR_IND,DOC_HDR_ID,DOC_VER_NBR,FRC_ACTN,PARNT_ID,PRIO_NBR,PRNCPL_ID,QUAL_ROLE_NM,QUAL_ROLE_NM_LBL_TXT,RECIP_TYP_CD,ROLE_NM,RSP_DESC_TXT,RSP_ID,RTE_LVL_NBR,RTE_NODE_INSTN_ID,RULE_ID,STAT_CD,VER_NBR)
  VALUES ('A','2364','2326','F',TO_DATE( '20081222132635', 'YYYYMMDDHH24MISS' ),1,'2695',1,0,'2363',1,'supervisor','supervisr supervisr','supervisr','U','supervisr','Supervisor Routing','2026',3,'2878','1050','D',0)
/
INSERT INTO KREW_ACTN_RQST_T (ACTN_RQST_CD,ACTN_RQST_ID,APPR_PLCY,CRTE_DT,CUR_IND,DOC_HDR_ID,DOC_VER_NBR,FRC_ACTN,PRIO_NBR,QUAL_ROLE_NM,QUAL_ROLE_NM_LBL_TXT,RECIP_TYP_CD,ROLE_NM,RSP_DESC_TXT,RSP_ID,RTE_LVL_NBR,RTE_NODE_INSTN_ID,RULE_ID,STAT_CD,VER_NBR)
  VALUES ('K','2365','F',TO_DATE( '20081222132635', 'YYYYMMDDHH24MISS' ),1,'2695',1,0,1,'director director','director','R','director','Dean/Director Routing','2028',3,'2878','1051','A',0)
/
INSERT INTO KREW_ACTN_RQST_T (ACTN_RQST_CD,ACTN_RQST_ID,APPR_PLCY,CRTE_DT,CUR_IND,DOC_HDR_ID,DOC_VER_NBR,FRC_ACTN,PARNT_ID,PRIO_NBR,PRNCPL_ID,QUAL_ROLE_NM,QUAL_ROLE_NM_LBL_TXT,RECIP_TYP_CD,ROLE_NM,RSP_DESC_TXT,RSP_ID,RTE_LVL_NBR,RTE_NODE_INSTN_ID,RULE_ID,STAT_CD,VER_NBR)
  VALUES ('K','2366','F',TO_DATE( '20081222132635', 'YYYYMMDDHH24MISS' ),1,'2695',1,0,'2365',1,'director','director director','director','U','director','Dean/Director Routing','2028',3,'2878','1051','A',0)
/
INSERT INTO KREW_ACTN_RQST_T (ACTN_RQST_CD,ACTN_RQST_ID,APPR_PLCY,CRTE_DT,CUR_IND,DOC_HDR_ID,DOC_VER_NBR,FRC_ACTN,PRIO_NBR,PRNCPL_ID,RECIP_TYP_CD,RSP_DESC_TXT,RSP_ID,RTE_LVL_NBR,RTE_NODE_INSTN_ID,STAT_CD,VER_NBR)
  VALUES ('A','2367','F',TO_DATE( '20090317101441', 'YYYYMMDDHH24MISS' ),1,'2701',1,0,1,'user4','U','Destination Rule','2022',1,'2891','A',0)
/
INSERT INTO KREW_ACTN_RQST_T (ACTN_RQST_CD,ACTN_RQST_ID,APPR_PLCY,CRTE_DT,CUR_IND,DLGN_TYP,DOC_HDR_ID,DOC_VER_NBR,FRC_ACTN,PARNT_ID,PRIO_NBR,PRNCPL_ID,RECIP_TYP_CD,RSP_DESC_TXT,RSP_ID,RTE_LVL_NBR,RTE_NODE_INSTN_ID,STAT_CD,VER_NBR)
  VALUES ('A','2368','F',TO_DATE( '20090317101441', 'YYYYMMDDHH24MISS' ),1,'S','2701',1,1,'2367',1,'user2','U','User4 Destination Routing Secondary Delegation','2061',1,'2891','A',0)
/
