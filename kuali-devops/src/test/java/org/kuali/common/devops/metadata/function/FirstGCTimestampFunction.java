package org.kuali.common.devops.metadata.function;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.fromNullable;
import static org.apache.commons.lang.StringUtils.substringBetween;
import static org.kuali.common.util.base.Precondition.checkNotBlank;
import static org.kuali.common.util.base.Precondition.checkNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;

public final class FirstGCTimestampFunction implements Function<String, Optional<Long>> {

	public FirstGCTimestampFunction() {
		this("yyyy-MM-dd'T'HH:mm:ss.SSSZZZZZ");
	}

	public FirstGCTimestampFunction(String timestampFormat) {
		this.timestampFormat = checkNotBlank(timestampFormat, "timestampFormat");
	}

	private final String timestampFormat;

	@Override
	public Optional<Long> apply(String content) {
		checkNotNull(content, "content");
		return getFirstGCTimestamp(content);
	}

	/**
	 * 1 - Identify the line containing the timestamp<br>
	 * 2 - Extract the timestamp from that line<br>
	 * 3 - Convert the timestamp into milliseconds<br>
	 */
	protected Optional<Long> getFirstGCTimestamp(String content) {
		Optional<String> line = getTimestampLine(content);
		if (!line.isPresent()) {
			return absent();
		}
		Optional<String> timestamp = getTimestamp(line.get());
		if (!timestamp.isPresent()) {
			return absent();
		} else {
			return getMillis(timestamp.get());
		}
	}

	/**
	 * A HotSpot garbage collection event is always bracketed by {} and contains a timestamp line that starts with the current year.
	 * 
	 * The timestamp line is the only line that starts with an integer. All other lines start with letters.
	 * 
	 * <pre>
	 * {Heap before GC invocations=36 (full 14):
	 *  PSYoungGen      total 644608K, used 320K [0x00000007d5500000, 0x00000007ffe00000, 0x0000000800000000)
	 *   eden space 591360K, 0% used [0x00000007d5500000,0x00000007d5500000,0x00000007f9680000)
	 *   from space 53248K, 0% used [0x00000007fca00000,0x00000007fca50040,0x00000007ffe00000)
	 *   to   space 52736K, 0% used [0x00000007f9680000,0x00000007f9680000,0x00000007fca00000)
	 *  ParOldGen       total 349696K, used 72060K [0x0000000780000000, 0x0000000795580000, 0x00000007d5500000)
	 *   object space 349696K, 20% used [0x0000000780000000,0x000000078465f240,0x0000000795580000)
	 *  PSPermGen       total 93696K, used 71086K [0x0000000770000000, 0x0000000775b80000, 0x0000000780000000)
	 *   object space 93696K, 75% used [0x0000000770000000,0x000000077456b970,0x0000000775b80000)
	 * 2014-02-08T11:27:30.503+0000: 47666.362: [Full GC [PSYoungGen: 320K->0K(644608K)] [ParOldGen: 72060K->72013K(349696K)] 72380K->72013K(994304K) [PSPermGen: 71086K->71080K(90112K)], 0.4259030 secs] [Times: user=0.74 sys=0.00, real=0.43 secs] 
	 * Heap after GC invocations=36 (full 14):
	 *  PSYoungGen      total 644608K, used 0K [0x00000007d5500000, 0x00000007ffe00000, 0x0000000800000000)
	 *   eden space 591360K, 0% used [0x00000007d5500000,0x00000007d5500000,0x00000007f9680000)
	 *   from space 53248K, 0% used [0x00000007fca00000,0x00000007fca00000,0x00000007ffe00000)
	 *   to   space 52736K, 0% used [0x00000007f9680000,0x00000007f9680000,0x00000007fca00000)
	 *  ParOldGen       total 349696K, used 72013K [0x0000000780000000, 0x0000000795580000, 0x00000007d5500000)
	 *   object space 349696K, 20% used [0x0000000780000000,0x0000000784653628,0x0000000795580000)
	 *  PSPermGen       total 90112K, used 71080K [0x0000000770000000, 0x0000000775800000, 0x0000000780000000)
	 *   object space 90112K, 78% used [0x0000000770000000,0x000000077456a208,0x0000000775800000)
	 * }
	 * </pre>
	 */
	protected Optional<String> getTimestampLine(String content) {
		Optional<String> gc = fromNullable(substringBetween(content, "{", "}"));
		if (!gc.isPresent()) {
			return absent();
		}
		List<String> lines = Splitter.on('\n').splitToList(gc.get());
		for (String line : lines) {
			String trimmed = line.trim();
			if (trimmed.startsWith("20")) { // Only works until the end of 2099
				return Optional.of(trimmed);
			}
		}
		return absent();
	}

	/**
	 * Timestamp line format -> 2014-01-06T21:23:15.299+0000: 0.957: [GC
	 */
	protected Optional<String> getTimestamp(String line) {
		String token = ": "; // A colon followed by a space signifies the end of the timestamp
		int pos = line.indexOf(token);
		if (pos == -1) {
			return absent();
		}
		return Optional.of(line.substring(0, pos));
	}

	/**
	 * Timestamp format -> 2014-01-06T21:23:15.299+0000
	 */
	protected Optional<Long> getMillis(String timestamp) {
		try {
			// New parser every single time because of SimpleDateFormat's thread safety issues
			SimpleDateFormat parser = new SimpleDateFormat(timestampFormat);
			Date date = parser.parse(timestamp);
			return Optional.of(date.getTime());
		} catch (ParseException e) {
			// Don't rethrow since the general expectation for Function.apply() is to not have side effects
			return absent();
		}
	}

}
