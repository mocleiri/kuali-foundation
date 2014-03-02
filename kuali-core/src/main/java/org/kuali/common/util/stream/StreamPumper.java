package org.kuali.common.util.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.codehaus.plexus.util.IOUtil;
import org.codehaus.plexus.util.cli.AbstractStreamHandler;
import org.codehaus.plexus.util.cli.StreamConsumer;

/**
 * Copied from Plexus in order to add constructors that allow you to provide a BufferedReader directly, or to provide an explicit character encoding.
 * 
 * This class would be entirely unnecessary if the Plexus version had a constructor that allowed you to provide a BufferedReader
 */
public class StreamPumper extends AbstractStreamHandler {

	private final BufferedReader in;
	private final StreamConsumer consumer;
	private final PrintWriter out;
	private volatile Exception exception = null;
	private static final int SIZE = 1024;

	public StreamPumper(InputStream in) {
		this(in, (StreamConsumer) null);
	}

	public StreamPumper(InputStream in, StreamConsumer consumer) {
		this(in, (PrintWriter) null, consumer);
	}

	public StreamPumper(InputStream in, PrintWriter writer) {
		this(in, writer, null);
	}

	public StreamPumper(InputStream in, PrintWriter writer, StreamConsumer consumer) {
		this(new BufferedReader(new InputStreamReader(in), SIZE), writer, consumer);
	}

	public StreamPumper(InputStream in, String encoding, StreamConsumer consumer) throws IOException {
		this(new BufferedReader(new InputStreamReader(in, encoding), SIZE), null, consumer);
	}

	public StreamPumper(BufferedReader in, PrintWriter writer, StreamConsumer consumer) {
		this.in = in;
		this.out = writer;
		this.consumer = consumer;
	}

	@Override
	public void run() {
		try {
			for (String line = in.readLine(); line != null; line = in.readLine()) {
				try {
					if (exception == null) {
						consumeLine(line);
					}
				} catch (Exception t) {
					exception = t;
				}

				if (out != null) {
					out.println(line);
					out.flush();
				}
			}
		} catch (IOException e) {
			exception = e;
		} finally {
			IOUtil.close(in);
			synchronized (this) {
				setDone();
				this.notifyAll();
			}
		}
	}

	public void flush() {
		if (out != null) {
			out.flush();
		}
	}

	public void close() {
		IOUtil.close(out);
	}

	public Exception getException() {
		return exception;
	}

	private void consumeLine(String line) {
		if (consumer != null && !isDisabled()) {
			consumer.consumeLine(line);
		}
	}
}
