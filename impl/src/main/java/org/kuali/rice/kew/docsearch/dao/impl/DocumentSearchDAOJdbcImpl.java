/*
 * Copyright 2006-2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kuali.rice.kew.docsearch.dao.impl;

import org.apache.commons.lang.StringUtils;
import org.kuali.rice.core.framework.services.CoreFrameworkServiceLocator;
import org.kuali.rice.kew.docsearch.DocSearchCriteriaDTO;
import org.kuali.rice.kew.docsearch.DocSearchDTO;
import org.kuali.rice.kew.docsearch.DocumentSearchGenerator;
import org.kuali.rice.kew.docsearch.StandardDocumentSearchGenerator;
import org.kuali.rice.kew.docsearch.dao.DocumentSearchDAO;
import org.kuali.rice.kew.util.KEWConstants;
import org.kuali.rice.kew.util.PerformanceLogger;
import org.kuali.rice.kns.util.KNSConstants;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 *
 * Spring JdbcTemplate implementation of DocumentSearchDAO
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class DocumentSearchDAOJdbcImpl implements DocumentSearchDAO {

    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DocumentSearchDAOJdbcImpl.class);

    private static final int DEFAULT_FETCH_MORE_ITERATION_LIMIT = 10;
    private DataSource ds;

    public List<DocSearchDTO> getListBoundedByCritera(DocumentSearchGenerator documentSearchGenerator, DocSearchCriteriaDTO criteria, String principalId) {
        return getList(documentSearchGenerator, criteria, criteria.getThreshold(), principalId);
    }

    public List<DocSearchDTO> getList(DocumentSearchGenerator documentSearchGenerator, DocSearchCriteriaDTO criteria, String principalId) {
        return getList(documentSearchGenerator, criteria, getSearchResultCap(documentSearchGenerator), principalId);
    }

    public void setDataSource(DataSource ds) {
        this.ds = new TransactionAwareDataSourceProxy(ds);
     }

    @SuppressWarnings("unchecked")
    private List<DocSearchDTO> getList(final DocumentSearchGenerator documentSearchGenerator, final DocSearchCriteriaDTO criteria, final Integer searchResultCap, final String principalId) {
        LOG.debug("start getList");
        try {
            final JdbcTemplate template = new JdbcTemplate(ds);

            return (List<DocSearchDTO>) template.execute(new ConnectionCallback() {
                public Object doInConnection(final Connection con) throws SQLException {
                    final Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                    try {
                        criteria.setThreshold(searchResultCap);
                        if (searchResultCap != null) {
                            final int fetchLimit = getFetchMoreIterationLimit() * searchResultCap;
                            criteria.setFetchLimit(fetchLimit);
                            statement.setFetchSize(searchResultCap + 1);
                            statement.setMaxRows(fetchLimit + 1);
                        } else {
                            criteria.setFetchLimit(null);
                        }
                        PerformanceLogger perfLog = new PerformanceLogger();
                        String sql = documentSearchGenerator.generateSearchSql(criteria);
                        perfLog.log("Time to generate search sql from documentSearchGenerator class: " + documentSearchGenerator.getClass().getName(), true);
                        LOG.info("Executing document search with statement max rows: " + statement.getMaxRows());
                        LOG.info("Executing document search with statement fetch size: " + statement.getFetchSize());
                        perfLog = new PerformanceLogger();
                        final ResultSet rs = statement.executeQuery(sql);
                        try {
                            perfLog.log("Time to execute doc search database query.", true);
                            // TODO delyea - look at refactoring
                            final Statement searchAttributeStatement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                            try {
                            	if(documentSearchGenerator.isProcessResultSet()){
                            		return documentSearchGenerator.processResultSet(searchAttributeStatement, rs, criteria, principalId);
                            	}else{
                            		return new StandardDocumentSearchGenerator().processResultSet(searchAttributeStatement, rs, criteria, principalId);
                            	}
                            } finally {
                                try {
                                    searchAttributeStatement.close();
                                } catch (SQLException e) {
                                    LOG.warn("Could not close search attribute statement.");
                                }
                            }
                        } finally {
                            try {
                                rs.close();
                            } catch (SQLException e) {
                                LOG.warn("Could not close result set.");
                            }
                        }
                    } finally {
                        try {
                            statement.close();
                        } catch (SQLException e) {
                            LOG.warn("Could not close statement.");
                        }
                    }
                }
            });

        } catch (DataAccessException dae) {
            String errorMsg = "DataAccessException: " + dae.getMessage();
            LOG.error("getList() " + errorMsg, dae);
            throw new RuntimeException(errorMsg, dae);
        } catch (Exception e) {
            String errorMsg = "LookupException: " + e.getMessage();
            LOG.error("getList() " + errorMsg, e);
            throw new RuntimeException(errorMsg, e);
        }
    }

    private int getSearchResultCap(DocumentSearchGenerator docSearchGenerator) {
        int resultCap = docSearchGenerator.getDocumentSearchResultSetLimit();
        String resultCapValue = CoreFrameworkServiceLocator.getParameterService().getParameterValueAsString(KEWConstants.KEW_NAMESPACE, KNSConstants.DetailTypes.DOCUMENT_SEARCH_DETAIL_TYPE, KEWConstants.DOC_SEARCH_RESULT_CAP);
        if (!StringUtils.isBlank(resultCapValue)) {
            try {
                Integer maxResultCap = Integer.parseInt(resultCapValue);
                if (resultCap > maxResultCap) {
                    LOG.warn("Document Search Generator (" + docSearchGenerator.getClass().getName() + ") gives result set cap of " + resultCap + " which is greater than parameter " + KEWConstants.DOC_SEARCH_RESULT_CAP + " value of " + maxResultCap);
                    resultCap = maxResultCap;
                } else if (maxResultCap <= 0) {
                    LOG.warn(KEWConstants.DOC_SEARCH_RESULT_CAP + " was less than or equal to zero.  Please use a positive integer.");
                }
            } catch (NumberFormatException e) {
                LOG.warn(KEWConstants.DOC_SEARCH_RESULT_CAP + " is not a valid number.  Value was " + resultCapValue);
            }
        }
        return resultCap;
    }

    // TODO delyea: use searchable attribute count here?
    private int getFetchMoreIterationLimit() {
        int fetchMoreLimit = DEFAULT_FETCH_MORE_ITERATION_LIMIT;
        String fetchMoreLimitValue = CoreFrameworkServiceLocator.getParameterService().getParameterValueAsString(KEWConstants.KEW_NAMESPACE, KNSConstants.DetailTypes.DOCUMENT_SEARCH_DETAIL_TYPE, KEWConstants.DOC_SEARCH_FETCH_MORE_ITERATION_LIMIT);
        if (!StringUtils.isBlank(fetchMoreLimitValue)) {
            try {
                fetchMoreLimit = Integer.parseInt(fetchMoreLimitValue);
                if (fetchMoreLimit < 0) {
                    LOG.warn(KEWConstants.DOC_SEARCH_FETCH_MORE_ITERATION_LIMIT + " was less than zero.  Please use a value greater than or equal to zero.");
                    fetchMoreLimit = DEFAULT_FETCH_MORE_ITERATION_LIMIT;
                }
            } catch (NumberFormatException e) {
                LOG.warn(KEWConstants.DOC_SEARCH_FETCH_MORE_ITERATION_LIMIT + " is not a valid number.  Value was " + fetchMoreLimitValue);
            }
        }
        return fetchMoreLimit;
    }

    //
    //    protected DatabasePlatform getPlatform() {
    //    	return (DatabasePlatform)GlobalResourceLoader.getService(KEWServiceLocator.DB_PLATFORM);
    //    }
}
