package org.kuali.common.util.inform;

import java.io.PrintStream;

import org.kuali.common.util.Assert;
import org.kuali.common.util.log.LogMsg;

public final class Inform {

	public static final PrintStream DEFAULT_PRINT_STREAM = System.out;
	public static final String DEFAULT_START_TOKEN = "[INFO] Progress: ";
	public static final String DEFAULT_PROGRESS_TOKEN = ".";
	public static final String DEFAULT_COMPLETE_TOKEN = "\n";
	public static final LogMsg DEFAULT_START_MESSAGE = LogMsg.NOOP;
	public static final LogMsg DEFAULT_STOP_MESSAGE = LogMsg.NOOP;

	public static final Inform DEFAULT_INFORM = new Inform();

	public Inform() {
		this(DEFAULT_PRINT_STREAM, DEFAULT_START_TOKEN, DEFAULT_PROGRESS_TOKEN, DEFAULT_COMPLETE_TOKEN, DEFAULT_START_MESSAGE, DEFAULT_STOP_MESSAGE);
	}

	public Inform(LogMsg startMessage) {
		this(DEFAULT_PRINT_STREAM, DEFAULT_START_TOKEN, DEFAULT_PROGRESS_TOKEN, DEFAULT_COMPLETE_TOKEN, startMessage, DEFAULT_STOP_MESSAGE);
	}

	public Inform(LogMsg startMessage, LogMsg stopMessage) {
		this(DEFAULT_PRINT_STREAM, DEFAULT_START_TOKEN, DEFAULT_PROGRESS_TOKEN, DEFAULT_COMPLETE_TOKEN, startMessage, stopMessage);
	}

	public Inform(PrintStream printStream, String startToken, String progressToken, String completeToken, LogMsg startMessage, LogMsg stopMessage) {
		Assert.noNulls(printStream, startMessage, stopMessage, completeToken);
		// Printing a whitespace character to indicate progress completion is ok
		// Assert.noBlanks(startToken, progressToken,completeToken);
		Assert.noBlanks(startToken, progressToken);
		this.printStream = printStream;
		this.startToken = startToken;
		this.progressToken = progressToken;
		this.completeToken = completeToken;
		this.startMessage = startMessage;
		this.stopMessage = stopMessage;
	}

	private final PrintStream printStream;
	private final String startToken;
	private final String progressToken;
	private final String completeToken;
	private final LogMsg startMessage;
	private final LogMsg stopMessage;

	public PrintStream getPrintStream() {
		return printStream;
	}

	public String getStartToken() {
		return startToken;
	}

	public String getCompleteToken() {
		return completeToken;
	}

	public LogMsg getStartMessage() {
		return startMessage;
	}

	public LogMsg getStopMessage() {
		return stopMessage;
	}

	public String getProgressToken() {
		return progressToken;
	}

}
