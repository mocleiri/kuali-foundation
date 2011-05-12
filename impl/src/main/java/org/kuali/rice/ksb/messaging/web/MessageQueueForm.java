/*
 * Copyright 2005-2007 The Kuali Foundation
 *
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
package org.kuali.rice.ksb.messaging.web;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.struts.action.ActionForm;
import org.kuali.rice.core.util.KeyValue;
import org.kuali.rice.core.util.SqlTimestampConverter;
import org.kuali.rice.ksb.messaging.PersistedMessageBO;
import org.kuali.rice.ksb.util.CodeTranslator;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * Struts ActionForm for the {@link MessageQueueAction}.
 *
 * @author Kuali Rice Team (rice.collab@kuali.org)
 */
public class MessageQueueForm extends ActionForm {

    private static final long serialVersionUID = -247925603792980036L;
    public static final int DEFAULT_MAX_ROWS = 1000;
    public static final int DEFAULT_PAGE_SIZE = 100;

    private int maxRows = DEFAULT_MAX_ROWS;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private Long messageId;
    private String methodToCall = "";
    private Collection<PersistedMessageBO> messageQueueRows;
    private String showEdit;
    private String command;
    private PersistedMessageBO messageQueueFromDatabase;
    private PersistedMessageBO messageQueueFromForm;
    private String newQueueDate;
    private String existingQueueDate;
    private String ipAddress;

    //  filter stuff
    private String routeQueueIdFilter;
    private String serviceNameFilter;
    private String serviceNamespaceFilter;
    private String queueStatusFilter;
    private String ipNumberFilter;
    private String value1Filter;
    private String value2Filter;
    private String filterApplied;

    private Integer maxMessageFetcherMessages = Integer.valueOf(50);

    private String myIpAddress;
    private String myServiceNamespace;
    private String messagePersistence;
    private String messageDelivery;
    private String messageOff;
    private List<KeyValue> ipAddresses = new ArrayList<KeyValue>();

    static {
        ConvertUtils.register(new SqlTimestampConverter(), Timestamp.class);
    }

    public MessageQueueForm() {
        messageQueueFromForm = new PersistedMessageBO();
        maxRows = DEFAULT_MAX_ROWS;
        pageSize = DEFAULT_PAGE_SIZE;
    }

    public String getRouteQueueStatusLabel(String statusCode) {
        return CodeTranslator.getRouteQueueStatusLabel(statusCode);
    }

    public String getMethodToCall() {
        return methodToCall;
    }

    public void setMethodToCall(String methodToCall) {
        this.methodToCall = methodToCall;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long routeQueueId) {
        this.messageId = routeQueueId;
    }

    public Collection<PersistedMessageBO> getMessageQueueRows() {
        return messageQueueRows;
    }

    public void setMessageQueueRows(Collection<PersistedMessageBO> routeQueueRows) {
        this.messageQueueRows = routeQueueRows;
    }

    public int getMessageQueueRowsSize() {
        if (messageQueueRows == null) {
            return 0;
        }
        return messageQueueRows.size();
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public PersistedMessageBO getMessageQueueFromDatabase() {
        return messageQueueFromDatabase;
    }

    public void setMessageQueueFromDatabase(PersistedMessageBO existingRouteQueue) {
        this.messageQueueFromDatabase = existingRouteQueue;
    }

    public PersistedMessageBO getMessageQueueFromForm() {
        return messageQueueFromForm;
    }

    public void setMessageQueueFromForm(PersistedMessageBO routeQueue) {
        this.messageQueueFromForm = routeQueue;
    }

    public String getShowEdit() {
        return showEdit;
    }

    public void setShowEdit(String showEdit) {
        this.showEdit = showEdit;
    }

    public String getExistingQueueDate() {
        return existingQueueDate;
    }

    public void setExistingQueueDate(String existingQueueDate) {
        this.existingQueueDate = existingQueueDate;
    }

    public String getNewQueueDate() {
        return newQueueDate;
    }

    public void setNewQueueDate(String newQueueDate) {
        this.newQueueDate = newQueueDate;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * @return the ipNumberFilter
     */
    public String getIpNumberFilter() {
      return ipNumberFilter;
    }

    /**
     * @param ipNumberFilter the ipNumberFilter to set
     */
    public void setIpNumberFilter(String ipNumberFilter) {
      this.ipNumberFilter = ipNumberFilter;
    }

    /**
     * @return the serviceNamespaceFilter
     */
    public String getServiceNamespaceFilter() {
      return serviceNamespaceFilter;
    }

    /**
     * @param serviceNamespaceFilter the serviceNamespaceFilter to set
     */
    public void setServiceNamespaceFilter(String ServiceNamespaceFilter) {
      this.serviceNamespaceFilter = ServiceNamespaceFilter;
    }

    /**
     * @return the queueStatusFilter
     */
    public String getQueueStatusFilter() {
      return queueStatusFilter;
    }

    /**
     * @param queueStatusFilter the queueStatusFilter to set
     */
    public void setQueueStatusFilter(String queueStatusFilter) {
      this.queueStatusFilter = queueStatusFilter;
    }

    /**
     * @return the serviceNameFilter
     */
    public String getServiceNameFilter() {
      return serviceNameFilter;
    }

    /**
     * @param serviceNameFilter the serviceNameFilter to set
     */
    public void setServiceNameFilter(String serviceNameFilter) {
      this.serviceNameFilter = serviceNameFilter;
    }

    /**
     * @return the filterApplied
     */
    public String getFilterApplied() {
      return filterApplied;
    }

    /**
     * @param filterApplied the filterApplied to set
     */
    public void setFilterApplied(String filterApplied) {
      this.filterApplied = filterApplied;
    }

    public String getRouteQueueIdFilter() {
        return this.routeQueueIdFilter;
    }

    public void setRouteQueueIdFilter(String messageIdFilter) {
        this.routeQueueIdFilter = messageIdFilter;
    }

    public String getValue1Filter() {
        return this.value1Filter;
    }

    public void setValue1Filter(String value1Filter) {
        this.value1Filter = value1Filter;
    }

    public String getValue2Filter() {
        return this.value2Filter;
    }

    public void setValue2Filter(String value2Filter) {
        this.value2Filter = value2Filter;
    }

    public List<KeyValue> getIpAddresses() {
        return this.ipAddresses;
    }

    public void setIpAddresses(List<KeyValue> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public Integer getMaxMessageFetcherMessages() {
        return this.maxMessageFetcherMessages;
    }

    public void setMaxMessageFetcherMessages(Integer maxMessageFetcherMessages) {
        this.maxMessageFetcherMessages = maxMessageFetcherMessages;
    }

    public String getMyIpAddress() {
        return this.myIpAddress;
    }

    public void setMyIpAddress(String myIpAddress) {
        this.myIpAddress = myIpAddress;
    }

    public String getMyServiceNamespace() {
        return this.myServiceNamespace;
    }

    public void setMyServiceNamespace(String myServiceNamespace) {
        this.myServiceNamespace = myServiceNamespace;
    }

    public String getMessageDelivery() {
        return this.messageDelivery;
    }

    public void setMessageDelivery(String messageDelivery) {
        this.messageDelivery = messageDelivery;
    }

    public String getMessageOff() {
        return this.messageOff;
    }

    public void setMessageOff(String messageOff) {
        this.messageOff = messageOff;
    }

    public String getMessagePersistence() {
        return this.messagePersistence;
    }

    public void setMessagePersistence(String messagePersistence) {
        this.messagePersistence = messagePersistence;
    }


}
