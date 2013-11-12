package org.kuali.common.util.channel.impl;

import java.io.IOException;

import org.codehaus.plexus.util.cli.StreamFeeder;
import org.codehaus.plexus.util.cli.StreamPumper;
import org.kuali.common.util.Assert;
import org.kuali.common.util.channel.model.CommandContext;

import com.google.common.base.Optional;
import com.jcraft.jsch.ChannelExec;

public final class StreamHandler {

	public StreamHandler(CommandContext context) {
		Assert.noNulls(context);
		this.context = context;
	}

	private final CommandContext context;

	private Optional<StreamFeeder> inputFeeder;
	private StreamPumper outputPumper;
	private StreamPumper errorPumper;

	public void openStreams(ChannelExec exec) throws IOException {
		Assert.noNulls(exec);
		this.inputFeeder = getInputFeeder(context, exec);
		this.outputPumper = new StreamPumper(exec.getInputStream(), context.getStdout());
		this.errorPumper = new StreamPumper(exec.getErrStream(), context.getStderr());
	}

	public void startPumping() {
		Assert.noNulls(inputFeeder, outputPumper, errorPumper);
		if (inputFeeder.isPresent()) {
			inputFeeder.get().start();
		}
		errorPumper.start();
		outputPumper.start();
	}

	public void waitUntilDone() throws InterruptedException {
		if (inputFeeder.isPresent()) {
			inputFeeder.get().waitUntilDone();
		}
		outputPumper.waitUntilDone();
		errorPumper.waitUntilDone();
	}

	public void validate() throws InterruptedException {
		if (outputPumper.getException() != null) {
			throw new IllegalStateException("Error inside systemOut parser", outputPumper.getException());
		}
		if (errorPumper.getException() != null) {
			throw new IllegalStateException("Error inside systemErr parser", errorPumper.getException());
		}
	}

	public void disableQuietly() {
		if (inputFeeder != null && inputFeeder.isPresent()) {
			inputFeeder.get().disable();
		}
		if (errorPumper != null) {
			errorPumper.disable();
		}
		if (outputPumper != null) {
			outputPumper.disable();
		}
	}

	public void closeQuietly() {
		if (inputFeeder != null && inputFeeder.isPresent()) {
			inputFeeder.get().close();
		}
		if (errorPumper != null) {
			errorPumper.close();
		}
		if (outputPumper != null) {
			outputPumper.close();
		}
	}

	public CommandContext getContext() {
		return context;
	}

	protected Optional<StreamFeeder> getInputFeeder(CommandContext context, ChannelExec exec) throws IOException {
		if (context.getStdin().isPresent()) {
			StreamFeeder feeder = new StreamFeeder(context.getStdin().get(), exec.getOutputStream());
			return Optional.of(feeder);
		} else {
			return Optional.absent();
		}
	}

}
