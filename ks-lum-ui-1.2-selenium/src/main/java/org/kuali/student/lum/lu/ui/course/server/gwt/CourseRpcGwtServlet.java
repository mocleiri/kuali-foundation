/**
 * Copyright 2010 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.student.lum.lu.ui.course.server.gwt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.student.common.dto.StatusInfo;
import org.kuali.student.common.search.dto.SearchRequest;
import org.kuali.student.common.search.dto.SearchResult;
import org.kuali.student.common.ui.client.service.DataSaveResult;
import org.kuali.student.common.ui.server.gwt.DataGwtServlet;
import org.kuali.student.core.statement.dto.ReqComponentInfo;
import org.kuali.student.core.statement.dto.StatementTreeViewInfo;
import org.kuali.student.core.statement.service.StatementService;
import org.kuali.student.core.statement.ui.client.widgets.rules.ReqComponentInfoUi;
import org.kuali.student.core.statement.ui.client.widgets.rules.RulesUtil;
import org.kuali.student.lum.common.server.StatementUtil;
import org.kuali.student.lum.course.service.CourseService;
import org.kuali.student.lum.lu.service.LuService;
import org.kuali.student.lum.lu.ui.course.client.requirements.CourseRequirementsDataModel;
import org.kuali.student.lum.lu.ui.course.client.service.CourseRpcService;

public class CourseRpcGwtServlet extends DataGwtServlet implements CourseRpcService {

	private static final long serialVersionUID = 1L;

    private CourseService courseService;
    private LuService luService;
	private StatementService statementService;
	private CourseStateChangeServiceImpl stateChangeService;

    @Override
    public List<StatementTreeViewInfo> getCourseStatements(String courseId, String nlUsageTypeKey, String language) throws Exception {
        List<StatementTreeViewInfo> rules = courseService.getCourseStatements(courseId, nlUsageTypeKey, language);
        if (rules != null) {
        	for (StatementTreeViewInfo rule : rules) {
        		setReqCompNL(rule);
        	}
        }
        return rules;
    }

    public Map<Integer, StatementTreeViewInfo> storeCourseStatements(String courseId, String courseState, Map<Integer, CourseRequirementsDataModel.requirementState> states,
                                                                        Map<Integer, StatementTreeViewInfo> rules) throws Exception {

        Map<Integer, StatementTreeViewInfo> storedRules = new HashMap<Integer, StatementTreeViewInfo>();

        for (Integer key : rules.keySet()) {
            StatementTreeViewInfo rule = rules.get(key);
            switch (states.get(key)) {
                case STORED:
                    //rule was not changed so continue
                    storedRules.put(key, null);
                    break;
                case ADDED:
                    storedRules.put(key, createCourseStatement(courseId, courseState, rule));
                    break;
                case EDITED:
                    storedRules.put(key, updateCourseStatement(courseId, courseState, rule));
                    break;
                case DELETED:
                    storedRules.put(key, null);
                    deleteCourseStatement(courseId, rule);
                    break;
                default:
                    break;
            }
        }
        return storedRules;
    }

    @Override
    public StatementTreeViewInfo createCourseStatement(String courseId, String courseState, StatementTreeViewInfo statementTreeViewInfo) throws Exception {
    	StatementUtil.updateStatementTreeViewInfoState(courseState, statementTreeViewInfo);
    	CourseRequirementsDataModel.stripStatementIds(statementTreeViewInfo);
        StatementTreeViewInfo rule = courseService.createCourseStatement(courseId, statementTreeViewInfo);
        setReqCompNL(rule);
        return rule;
    }

    @Override
    public StatusInfo deleteCourseStatement(String courseId, StatementTreeViewInfo statementTreeViewInfo) throws Exception {
        return courseService.deleteCourseStatement(courseId, statementTreeViewInfo);
    }

    @Override
    public StatementTreeViewInfo updateCourseStatement(String courseId, String courseState, StatementTreeViewInfo statementTreeViewInfo) throws Exception {
    	StatementUtil.updateStatementTreeViewInfoState(courseState, statementTreeViewInfo);
    	CourseRequirementsDataModel.stripStatementIds(statementTreeViewInfo);
        StatementTreeViewInfo rule = courseService.updateCourseStatement(courseId, statementTreeViewInfo);
        setReqCompNL(rule);
        return rule;
    }

	@Override
	public DataSaveResult createCopyCourse(String originalCluId)
			throws Exception {
		throw new UnsupportedOperationException("Copy is not implemented without a proposal.");
	}

	@Override
	public DataSaveResult createCopyCourseProposal(String originalProposalId)
			throws Exception {
		throw new UnsupportedOperationException("Copy is not implemented without a proposal.");
	}

	@Override  
    public StatusInfo changeState(String courseId, String newState) throws Exception {
    	return changeState(courseId, newState, null);
    }
	
	@Override
    public StatusInfo changeState(String courseId, String newState, String prevEndTerm) throws Exception {
    	return stateChangeService.changeState(courseId, newState, prevEndTerm);
    }

    private void setReqCompNL(StatementTreeViewInfo tree) throws Exception {
        List<StatementTreeViewInfo> statements = tree.getStatements();
        List<ReqComponentInfo> reqComponentInfos = tree.getReqComponents();

         if ((statements != null) && (statements.size() > 0)) {
            // retrieve all statements
            for (StatementTreeViewInfo statement : statements) {
                setReqCompNL(statement); // inside set the children of this statementTreeViewInfo
            }
        } else if ((reqComponentInfos != null) && (reqComponentInfos.size() > 0)) {
            // retrieve all req. component LEAFS
        	for (int i = 0; i < reqComponentInfos.size(); i++) {
        		ReqComponentInfoUi reqUi = RulesUtil.clone(reqComponentInfos.get(i));
        		reqUi.setNaturalLanguageTranslation(statementService.translateReqComponentToNL(reqUi, "KUALI.RULE", "en"));
        		reqUi.setPreviewNaturalLanguageTranslation(statementService.translateReqComponentToNL(reqUi, "KUALI.RULE.PREVIEW", "en"));
        		reqComponentInfos.set(i, reqUi);
        	}
        }
    }

    @Override
	public Boolean isLatestVersion(String versionIndId, Long versionSequenceNumber) throws Exception {
    	//Perform a search to see if there are any new versions of the course that are approved, draft, etc.
    	//We don't want to version if there are
    	SearchRequest request = new SearchRequest("lu.search.isVersionable");
    	request.addParam("lu.queryParam.versionIndId", versionIndId);
    	request.addParam("lu.queryParam.sequenceNumber", versionSequenceNumber.toString());
    	List<String> states = new ArrayList<String>();
    	states.add("Approved");
    	states.add("Active");
    	states.add("Draft");
    	states.add("Superseded");
    	request.addParam("lu.queryParam.luOptionalState", states);
    	SearchResult result = luService.search(request);
    	
    	String resultString = result.getRows().get(0).getCells().get(0).getValue();
    	return "0".equals(resultString);
	}
    
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    public void setStatementService(StatementService statementService) {
        this.statementService = statementService;
    }

	public void setStateChangeService(
			CourseStateChangeServiceImpl stateChangeService) {
		this.stateChangeService = stateChangeService;
	}

	public void setLuService(LuService luService) {
		this.luService = luService;
	}
}
