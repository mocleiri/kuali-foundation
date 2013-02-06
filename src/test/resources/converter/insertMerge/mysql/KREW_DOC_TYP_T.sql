TRUNCATE TABLE KREW_DOC_TYP_T
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Workflow Maintenance Document Type Document','2011','DocumentTypeDocument',0,'1','default.htm?turl=WordDocuments%2Fdocumenttype.htm','Workflow Maintenance Document Type Document','6166CBA1BA5D644DE0404F8189D86C09','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Rule Maintenance Document Type Document','2012','RoutingRuleDocument',0,'1','Rule Maintenance Document Type Document','6166CBA1BA5E644DE0404F8189D86C09','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${ken.url}/DetailView.form','This is the re-usable notification document type that will be used for delivering all notifications with KEW.','2023','KualiNotification',0,'2000','Notification','6166CBA1BA69644DE0404F8189D86C09','org.kuali.rice.ken.postprocessor.kew.NotificationPostProcessor','1',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${ken.url}/AdministerNotificationRequest.form','Create a New Notification Request','2024','SendNotificationRequest',0,'1','Send Notification Request','6166CBA1BA6A644DE0404F8189D86C09','org.kuali.rice.ken.postprocessor.kew.NotificationSenderFormPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit parameter namespaces','2031','NamespaceMaintenanceDocument',0,'1','default.htm?turl=WordDocuments%2Fnamespace.htm','Namespace','6166CBA1BA71644DE0404F8189D86C09','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit a parameter type','2032','ParameterTypeMaintenanceDocument',0,'1','default.htm?turl=WordDocuments%2Fparametertype.htm','Parameter Type Maintenance Document','6166CBA1BA72644DE0404F8189D86C09','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit a parameter detail type','2033','ParameterDetailTypeMaintenanceDocument',0,'1','default.htm?turl=WordDocuments%2Fparametercomponent.htm','Parameter Detail Type Maintenance Document','6166CBA1BA73644DE0404F8189D86C09','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.url}/maintenance.do?methodToCall=docHandler','Create/edit a parameter','2034','ParameterMaintenanceDocument',0,'1','default.htm?turl=WordDocuments%2Fparameter.htm','Parameter Maintenance Document','6166CBA1BA74644DE0404F8189D86C09','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'KualiDocument','2680','KualiDocument',0,'KualiDocument','6166CBA1BA81644DE0404F8189D86C09','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Parent Document Type for all Rice Documents','2681','RiceDocument',0,'Rice Document','6166CBA1BA82644DE0404F8189D86C09','2680','2',3)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler','Routing Rule Delegation','2699','RoutingRuleDelegationMaintenanceDocument',0,'Routing Rule Delegation','A6DC8753-AF90-7A01-0EF7-E6D446529668','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler','2708','CampusMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fcampus.htm','CampusMaintenanceDocument','616D94CA-D08D-D036-E77D-4B53DB34CD95','2681','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler','2709','CampusTypeMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fcampustype.htm','CampusTypeMaintenanceDocument','DE0B8588-E459-C07A-87B8-6ACD693AE70C','2681','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler','2710','CountryMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fcountry.htm','CountryMaintenanceDocument','82EDB593-97BA-428E-C6E7-A7F3031CFAEB','2681','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler','2711','CountyMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fcounty.htm','CountyMaintenanceDocument','C972E260-5552-BB63-72E6-A514301B0326','2681','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler','2712','PostalCodeMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fpostalcode.htm','PostalCodeMaintenanceDocument','B79D1104-BC48-1597-AFBE-773EED31A110','2681','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler','2713','StateMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fstate.htm','StateMaintenanceDocument','EF2378F6-E770-D7BF-B7F1-C18881E3AFF0','2681','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'2994','IdentityManagementDocument',0,'Undefined','944596CD-A7FC-0DEE-EDE1-52A52BED85CC','2681','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kim.url}/identityManagementRoleDocument.do?methodToCall=docHandler','2995','IdentityManagementRoleDocument',0,'default.htm?turl=WordDocuments%2Frole.htm','Role','FEA8D9DD-0592-0525-B2BD-2F4BA811CF30','2994','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kim.url}/identityManagementGroupDocument.do?methodToCall=docHandler','2996','IdentityManagementGroupDocument',0,'default.htm?turl=WordDocuments%2Fgroup.htm','Group','D9636763-7749-8F3F-4570-21181E977AE3','2994','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kim.url}/identityManagementPersonDocument.do?methodToCall=docHandler','2997','IdentityManagementPersonDocument',0,'default.htm?turl=WordDocuments%2Fperson.htm','Person','14C95FE4-1497-82C6-CBBD-BF16AD81B845','2994','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler','2998','IdentityManagementReviewResponsibilityMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fresponsibility.htm','Review Responsibility','66413887-3C82-B12D-9563-0A893E8D1910','2994','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,HELP_DEF_URL,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${kr.url}/maintenance.do?methodToCall=docHandler','2999','IdentityManagementGenericPermissionMaintenanceDocument',0,'default.htm?turl=WordDocuments%2Fpermission.htm','Permission','A3AE4787-018E-1F17-6EB6-F2F0F366774F','2994','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'Kuali Student Parent Document','3000','KualiStudentDocument',0,'Kuali Student Parent Document','142727E5-233B-94EC-1A79-828BA4933243','2680','2',3)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'${lum.application.url}/org.kuali.student.lum.lu.ui.main.LUMMain/LUMMain.jsp?view=COURSE_PROPOSAL&idType=documentNumber','Kuali Student Credit Course Parent Document','3001','CluCreditCourseParentDocument',0,'Kuali Student Credit Course Parent Document','DE30C093-E98A-2AFF-7DA8-5818E1A39D1C','3036','2',3)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (0,0,'Credit Course Retirement','3002','kuali.proposal.type.course.retire',0,'Credit Course Retirement','6BF30ECF-C627-F88C-A4F1-EEB866D0A9B7','3038','org.kuali.student.lum.workflow.CoursePostProcessorBase','2',3)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'Credit Course Modification','3003','kuali.proposal.type.course.modify',0,'Credit Course Modification','2A6CD5BB-3B9D-0E31-A35C-64115FE51E05','3038','org.kuali.student.lum.workflow.CoursePostProcessorBase','2',3)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,0,'Credit Course Proposal','3004','kuali.proposal.type.course.create',0,'Credit Course Proposal','C7DFC7CD-8206-2AFC-8115-75EEDAA5AA0F','3038','org.kuali.student.lum.workflow.CoursePostProcessorBase','2',3)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${application.url}/kr/maintenance.do?methodToCall=docHandler','3009','StudentAdminMaintenanceDocument',0,'Kuali Student Admin Maintenance Document','2F5FDC52-1BEC-E2D5-97B1-15C46D50A563','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',13)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'3010','AcademicTimePeriodMaintenanceDocument',0,'Academic Time Period','4A5EAD63-DEAC-0D9F-91A7-EA6C1AEE3F58','3009','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'3011','AcademicTimePeriodDurationTypeMaintenanceDocument',0,'Academic Time Period Duration Type','F843CFCD-5120-C0C5-4C24-09B6B2103A61','3009','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'3012','AcademicTimePeriodSeasonalTypeMaintenanceDocument',0,'Academic Time Period Seasonal Type','24CB5B76-6B8B-5CD0-3D2C-CDB4EA4E9F2B','3009','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'3013','AcademicTimePeriodTypeMaintenanceDocument',0,'Academic Time Period Type','B559D920-B0B9-B942-E512-9081BC22AC95','3009','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'3014','DateRangeTypeMaintenanceDocument',0,'Date Range Type','8BD9DDA0-EE56-3349-DA48-96DC78C828AE','3009','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'3015','EnumeratedValueDocumentType',0,'Enumerated Value','3796ADEE-2BDA-3005-614D-E579261B505D','3009','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'3016','EnumerationDocumentType',0,'Enumeration','7AAB2CDA-ADB8-0954-475B-117A9F97C603','3009','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'3017','MessageEntityDocumentType',0,'Message','579C5254-E29E-1146-1292-76B72F763F27','3009','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${application.url}/kr-krad/maintenance?methodToCall=docHandler&dataObjectClassName=org.kuali.student.enrollment.class2.courseoffering.dto.ActivityOfferingWrapper','Create a New Activity Offering Maintenance Document','3021','ActivityOfferingInfoMaintenanceDocument',0,'1','org.kuali.student.r2.lum.course.infc.Activity Offering Info Maintenance Document','D2F07FCB2D3DFFC9E040760A4A45207E','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${application.url}/kr-krad/maintenance?methodToCall=docHandler&dataObjectClassName=org.kuali.student.enrollment.class2.courseoffering.dto.CourseOfferingEditWrapper','Create Course Offering Edit Maintenance Document','3025','CourseOfferingEditMaintenanceDocument',0,'1','org.kuali.student.r2.lum.course.infc.Course Offering Info Edit Maintenance Document','D2F07FCB2D3FFFC9E040760A4A45207E','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${application.url}/kr-krad/maintenance?methodToCall=docHandler&dataObjectClassName=org.kuali.student.enrollment.class2.courseoffering.dto.CourseOfferingCreateWrapper','Create a New Course Offering Maintenance Document','3027','CourseOfferingCreateMaintenanceDocument',0,'1','org.kuali.student.r2.lum.course.infc.Course Offering Info Maintenance Document','D2F07FCB2D40FFC9E040760A4A45207E','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${application.url}/kr-krad/maintenance?methodToCall=docHandler&dataObjectClassName=org.kuali.student.enrollment.class2.population.dto.PopulationWrapper','Create Population Maintenance Document','3029','PopulationWrapperMaintenanceDocument',0,'1','Population Maintenance Document','D2F07FCB2D41FFC9E040760A4A45207E','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.krad.url}/krmsAgendaEditor?methodToCall=docHandler','Create a KRMS Agenda','3030','AgendaEditorMaintenanceDocument',0,'1','KRMS Agenda Editor Maintenance Document','ebd70731-4d33-4c0b-a958-2b9ca047ae07','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.krad.url}/maintenance?methodToCall=docHandler','Create a New Agenda','3031','AgendaMaintenanceDocument',0,'1','Agenda Maintenance Document','3198b708-6e29-4b19-bf35-51473cf8a3d1','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.krad.url}/peopleFlowMaintenance?methodToCall=docHandler','People Flow Maintenance','3032','PeopleFlowMaintenanceDocument',0,'1','People Flow Maintenance Document','2c0a1333-f60b-47c4-a9b0-76f32f1ed97d','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.krad.url}/maintenance?methodToCall=docHandler','Create a KRMS Context','3033','ContextMaintenanceDocument',0,'1','KRMS Context Maintenance Document','87413487-8306-4130-b2df-a5d0e42243f9','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${kr.krad.url}/maintenance?methodToCall=docHandler','Create a KRMS Term Specification','3035','TermSpecificationMaintenanceDocument',0,'1','KRMS Term Specification Maintenance Document','57e7ee1d-e44a-4154-9ba4-ee562c434c98','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',0)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Kuali Student Parent Document','3036','KualiStudentDocument',1,'Kuali Student Parent Document','2c6d23f4-7092-4d3b-b762-78b27b113ecb','2680','3000','2',2)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Kuali Student Clu Parent Document','3037','CluParentDocument',0,'Kuali Student Clu Parent Document','c22704a1-13eb-42f4-b29e-26ba4cd25984','3036','2',3)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${lum.application.url}/org.kuali.student.lum.lu.ui.main.LUMMain/LUMMain.jsp?view=COURSE_PROPOSAL&idType=documentNumber','Kuali Student Credit Course Parent Document','3038','CluCreditCourseParentDocument',1,'Kuali Student Credit Course Parent Document','f0c6caf6-32c5-4def-998c-826ceeb75e71','3037','3001','2',6)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${lum.application.url}/org.kuali.student.lum.lu.ui.main.LUMMain/LUMMain.jsp?view=PROGRAM_PROPOSAL&idType=documentNumber','Kuali Student Major Discipline Parent Document','3039','CluMajorDisciplineParentDocument',0,'Kuali Student Major Discipline Parent Document','a2d9776b-e132-42de-914c-e0e54b3a1f6d','3037','2',3)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'${lum.application.url}/org.kuali.student.lum.lu.ui.main.LUMMain/LUMMain.jsp?view=COURSE_RETIRE_BY_PROPOSAL&idType=documentNumber','Credit Course Retirement','3040','kuali.proposal.type.course.retire',1,'Credit Course Retirement','c04d2afa-d83a-4d17-bfef-92ef8df0b2bb','3038','org.kuali.student.lum.workflow.CoursePostProcessorBase','3002','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Credit Course Modification','3041','kuali.proposal.type.course.modify',1,'Credit Course Modification','d8211168-d209-4fcd-b754-08ae15f7d642','3038','org.kuali.student.lum.workflow.CoursePostProcessorBase','3003','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,PREV_DOC_TYP_VER_NBR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Credit Course Proposal','3042','kuali.proposal.type.course.create',1,'Credit Course Proposal','cc274bad-8b2e-4792-8b34-56602d06fc03','3038','org.kuali.student.lum.workflow.CoursePostProcessorBase','3004','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Credit Course Admin Proposal','3043','kuali.proposal.type.course.create.admin',0,'Credit Course Admin Proposal','ed80a491-cfd0-48b0-9256-ddd6b32ebc1e','3038','org.kuali.student.lum.workflow.CoursePostProcessorBase','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Modify Credit Course Admin Proposal','3044','kuali.proposal.type.course.modify.admin',0,'Modify Credit Course Admin Proposal','9d3b6471-7208-4ef8-876b-64ee5df16b12','3038','org.kuali.student.lum.workflow.CoursePostProcessorBase','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Create Major Discipline Proposal','3045','kuali.proposal.type.majorDiscipline.create',0,'Create Major Discipline Proposal','eee0417e-971d-4099-a264-71fc658b4a1f','3039','org.kuali.student.lum.workflow.ProgramPostProcessorBase','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'Modify Major Discipline Proposal','3046','kuali.proposal.type.majorDiscipline.modify',0,'Modify Major Discipline Proposal','97ec4bca-6817-44af-ae69-77d1c8231335','3039','org.kuali.student.lum.workflow.ProgramPostProcessorBase','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'3047','SubjectCodeMaintenanceDocument',0,'Subject Code','2e238fac-fac5-4d43-997b-17de60ba1caf','3009','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'3048','SubjectCodeJoinOrgMaintenanceDocument',0,'Subject Code Join Org Type','59f17ad8-e7ed-4496-8146-1701474481c8','3009','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,CUR_IND,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,LBL,OBJ_ID,PARNT_ID,RTE_VER_NBR,VER_NBR)
  VALUES (1,1,'3049','SubjectCodeTypeMaintenanceDocument',0,'Subject Code Type','41eb416c-155a-4f62-a12e-d5462e793e9b','3009','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${application.url}/kr-krad/maintenance?methodToCall=docHandler&dataObjectClassName=org.kuali.student.enrollment.class1.krms.dto.RuleEditor','Create a KRMS Rule','3050','RuleMaintenanceDocument',0,'1','KRMS Rule Maintenance Document','D2F07FCB2D5FFFC9E040760A4A45207E','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',1)
/
INSERT INTO KREW_DOC_TYP_T (ACTV_IND,BLNKT_APPR_GRP_ID,CUR_IND,DOC_HDLR_URL,DOC_TYP_DESC,DOC_TYP_ID,DOC_TYP_NM,DOC_TYP_VER_NBR,GRP_ID,LBL,OBJ_ID,PARNT_ID,POST_PRCSR,RTE_VER_NBR,VER_NBR)
  VALUES (1,'1',1,'${application.url}/kr-krad/maintenance?methodToCall=docHandler&dataObjectClassName=org.kuali.student.r2.core.population.dto.PopulationInfo','Create KSKS PopulationInfo Maintenance Document','77300','KitchenSinkPopulationInfoMaintenanceDocument',0,'1','KSKS PopulationInfo Maintenance Document','D2F07FCB2D5EFFC9E040760A4A45207E','2681','org.kuali.rice.krad.workflow.postprocessor.KualiPostProcessor','2',1)
/
