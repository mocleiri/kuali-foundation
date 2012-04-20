/**
 * Copyright 2004-2012 The Kuali Foundation
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
package org.kuali.db;

import java.util.Date;

/**
 * Pojo for a database event
 */
public class DatabaseEvent {
	String message;
	MessagePriority priority;
	Date timestamp;
	Throwable exception;
	Transaction transaction;
	int totalStatements;
	int successfulStatements;
	String sql;
	int updateCountTotal;

	public DatabaseEvent() {
		this(null);
	}

	public DatabaseEvent(String message) {
		this(message, MessagePriority.INFO);
	}

	public DatabaseEvent(String message, MessagePriority priority) {
		this(message, priority, new Date(), null, null);
	}

	public DatabaseEvent(String message, MessagePriority priority, Date timestamp, Throwable exception, Transaction transaction) {
		super();
		this.message = message;
		this.priority = priority;
		this.timestamp = timestamp;
		this.exception = exception;
		this.transaction = transaction;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public MessagePriority getPriority() {
		return priority;
	}

	public void setPriority(MessagePriority priority) {
		this.priority = priority;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public Throwable getException() {
		return exception;
	}

	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public Transaction getTransaction() {
		return transaction;
	}

	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}

	public int getTotalStatements() {
		return totalStatements;
	}

	public void setTotalStatements(int totalStatements) {
		this.totalStatements = totalStatements;
	}

	public int getSuccessfulStatements() {
		return successfulStatements;
	}

	public void setSuccessfulStatements(int successfulStatements) {
		this.successfulStatements = successfulStatements;
	}

	public String getSql() {
		return sql;
	}

	public void setSql(String sql) {
		this.sql = sql;
	}

	public int getUpdateCountTotal() {
		return updateCountTotal;
	}

	public void setUpdateCountTotal(int updateCountTotal) {
		this.updateCountTotal = updateCountTotal;
	}
}
