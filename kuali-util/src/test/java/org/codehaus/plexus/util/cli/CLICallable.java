package org.codehaus.plexus.util.cli;

public class CLICallable implements CommandLineCallable {

	public CLICallable(int timeoutInSeconds, StreamFeeder inputFeeder, StreamPumper outputPumper, StreamPumper errorPumper, Process p, ProcessHook processHook) {
		this.timeoutInSeconds = timeoutInSeconds;
		this.inputFeeder = inputFeeder;
		this.outputPumper = outputPumper;
		this.errorPumper = errorPumper;
		this.p = p;
		this.processHook = processHook;
	}

	private final int timeoutInSeconds;
	private final StreamFeeder inputFeeder;
	private final StreamPumper outputPumper;
	private final StreamPumper errorPumper;
	private final Process p;
	private final ProcessHook processHook;

	@Override
	public Integer call() throws CommandLineException {
		try {
			int returnValue;
			if (timeoutInSeconds <= 0) {
				returnValue = p.waitFor();
			} else {
				long now = System.currentTimeMillis();
				long timeoutInMillis = 1000L * timeoutInSeconds;
				long finish = now + timeoutInMillis;
				while (CommandLineUtils.isAlive(p) && (System.currentTimeMillis() < finish)) {
					Thread.sleep(10);
				}
				if (CommandLineUtils.isAlive(p)) {
					throw new InterruptedException("Process timeout out after " + timeoutInSeconds + " seconds");
				}
				returnValue = p.exitValue();
			}

			CommandLineUtils.waitForAllPumpers(inputFeeder, outputPumper, errorPumper);

			if (outputPumper.getException() != null) {
				throw new CommandLineException("Error inside systemOut parser", outputPumper.getException());
			}

			if (errorPumper.getException() != null) {
				throw new CommandLineException("Error inside systemErr parser", errorPumper.getException());
			}

			return returnValue;
		} catch (InterruptedException ex) {
			if (inputFeeder != null) {
				inputFeeder.disable();
			}
			outputPumper.disable();
			errorPumper.disable();
			throw new CommandLineTimeOutException("Error while executing external command, process killed.", ex);
		} finally {
			ShutdownHookUtils.removeShutdownHook(processHook);

			processHook.run();

			if (inputFeeder != null) {
				inputFeeder.close();
			}

			outputPumper.close();

			errorPumper.close();
		}
	}

	public int getTimeoutInSeconds() {
		return timeoutInSeconds;
	}

	public StreamFeeder getInputFeeder() {
		return inputFeeder;
	}

	public StreamPumper getOutputPumper() {
		return outputPumper;
	}

	public StreamPumper getErrorPumper() {
		return errorPumper;
	}

	public Process getP() {
		return p;
	}

	public ProcessHook getProcessHook() {
		return processHook;
	}

}
