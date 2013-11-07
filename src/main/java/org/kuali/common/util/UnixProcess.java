/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util;

/**
 * This is a pojo representing what is returned when executing "ps -f" on an Amazon Linux server
 */
public class UnixProcess {
	// UID PID PPID C STIME TTY TIME CMD

	String userId;
	Integer processId;
	Integer parentProcessId;
	String cpu;
	String startTime;
	String terminal;
	String totalCpuUsage;
	String command;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getProcessId() {
		return processId;
	}

	public void setProcessId(Integer processId) {
		this.processId = processId;
	}

	public Integer getParentProcessId() {
		return parentProcessId;
	}

	public void setParentProcessId(Integer parentProcessId) {
		this.parentProcessId = parentProcessId;
	}

	public String getCpu() {
		return cpu;
	}

	public void setCpu(String cpu) {
		this.cpu = cpu;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTerminal() {
		return terminal;
	}

	public void setTerminal(String terminal) {
		this.terminal = terminal;
	}

	public String getTotalCpuUsage() {
		return totalCpuUsage;
	}

	public void setTotalCpuUsage(String totalCpuUsage) {
		this.totalCpuUsage = totalCpuUsage;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}

}
