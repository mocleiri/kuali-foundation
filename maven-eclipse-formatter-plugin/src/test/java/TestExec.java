import java.io.InputStream;

public class TestExec {
	private static boolean done = false;

	private class Printer implements Runnable {
		InputStream in;

		public Printer(InputStream in) {
			super();
			this.in = in;
		}

		public void run() {
			try {
				while (true) {
					if (done) {
						break;
					}
					Thread.sleep(100);
					int available = in.available();
					if (available == 0) {
						continue;
					} else {
						byte[] buf = new byte[available];
						in.read(buf);
						System.out.print(new String(buf));
					}
				}
			} catch (Throwable t) {
				t.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		new TestExec().execute(args);
	}

	protected String getCommand(String[] args) {
		if (isLinux(args)) {
			return getLinuxCommand();
		} else {
			return getWindowsCommand();
		}
	}

	protected boolean isLinux(String[] args) {
		if (args == null) {
			return false;
		}
		if (args.length == 0) {
			return false;
		}
		return args[0].equalsIgnoreCase("linux");
	}

	protected void execute(String[] args) {
		try {
			String command = getCommand(args);
			System.out.println(command);
			Runtime runtime = Runtime.getRuntime();
			Process process = runtime.exec(command);
			Thread t = new Thread(new Printer(process.getInputStream()));
			t.start();
			int exitValue = process.waitFor();
			System.out.println(exitValue);
			done = true;
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	protected String getWindowsCommand() {
		StringBuilder sb = new StringBuilder();
		sb.append("C:\\eclipse\\3.7\\eclipse.exe");
		sb.append(" -application org.eclipse.jdt.core.JavaCodeFormatter");
		sb.append(" -vm \"C:\\Program Files\\Java\\jdk1.6.0_18\\jre\\bin\\javaw.exe\"");
		sb.append(" -config C:\\eclipse\\sts\\2.6.1\\ide\\ws\\maven-eclipse-formatter-plugin\\src\\main\\resources\\eclipse.prefs");
		sb.append(" -nosplash");
		sb.append(" -verbose");
		sb.append(" C:\\eclipse\\sts\\2.6.1\\ide\\ws\\rice\\core\\api\\src\\main\\java");
		return sb.toString();
	}

	protected String getLinuxCommand() {
		StringBuilder sb = new StringBuilder();
		sb.append("/home/ubuntu/eclipse-3.7/eclipse");
		sb.append(" -application \"org.eclipse.jdt.core.JavaCodeFormatter\"");
		sb.append(" -vm \"/opt/java/jdk1.6.0_25/jre/bin/java\"");
		sb.append(" -config \"/home/ubuntu/eclipse.prefs\"");
		sb.append(" -nosplash");
		sb.append(" -verbose");
		sb.append(" /opt/jenkins-home/jobs/rice-trunk-checkstyle/workspace/trunk/core/api/src/main/java");
		return sb.toString();
	}

}
