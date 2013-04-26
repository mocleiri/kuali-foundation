package org.kuali.common.util;

/**
 * This is a pojo representing what is returned when executing "ps -f" on a Unix machine
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
