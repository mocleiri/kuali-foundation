package org.kuali.common.util;

public class LogMsg {

	LoggerLevel level = LoggerLevel.INFO;
	String message;
	Object[] args;

	public LogMsg() {
		this(null, null, LoggerLevel.INFO);
	}

	public LogMsg(String message, Object[] args) {
		this(message, args, LoggerLevel.INFO);
	}

	public LogMsg(String message, Object[] args, LoggerLevel level) {
		super();
		this.message = message;
		this.args = args;
		this.level = level;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}

	public LoggerLevel getLevel() {
		return level;
	}

	public void setLevel(LoggerLevel level) {
		this.level = level;
	}

}
