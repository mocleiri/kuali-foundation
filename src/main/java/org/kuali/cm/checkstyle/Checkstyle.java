package org.kuali.cm.checkstyle;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

public class Checkstyle {

	public static void main(String[] args) {
		new Checkstyle().execute();
	}

	protected Set<String> getUniqueMessages(List<Error> errors) {
		Map<String, String> map = new TreeMap<String, String>();
		for (Error e : errors) {
			String msg = translate(e.getMsg());
			map.put(msg, msg);
		}
		return map.keySet();
	}

	protected Set<String> getUniqueFiles(List<Error> errors) {
		Map<String, String> map = new TreeMap<String, String>();
		for (Error e : errors) {
			map.put(e.getSrc(), e.getSrc());
		}
		return map.keySet();
	}

	protected Set<SourceFile> getSourceFiles(Set<String> messages, Set<String> files, List<Error> errors) {
		Set<SourceFile> set = new HashSet<SourceFile>();
		return set;
	}

	protected Set<String> getUnmappedMessages(Properties props, Set<String> msgs) {
		Map<String, String> unmapped = new TreeMap<String, String>();
		for (String msg : msgs) {
			if (props.containsValue(msg)) {
				continue;
			}
			unmapped.put(msg, msg);

		}
		return unmapped.keySet();
	}

	protected void execute() {
		try {
			List<Error> errors = getErrorObjects(getErrors());
			Set<String> msgs = getUniqueMessages(errors);
			Set<String> files = getUniqueFiles(errors);
			Properties props = getCheckStyleProps();
			Set<String> unmapped = getUnmappedMessages(props, msgs);
			System.out.println(unmapped.size());
			for (String msg : unmapped) {
				System.out.println(msg);
			}

		} catch (Throwable t) {
			t.printStackTrace();
		}
	}

	protected void execute2() {
		/*
		 * try { List<Error> errors = getErrorObjects(getErrors()); Set<String> messages = getUniqueMessages(errors);
		 * Set<String> files = getUniqueFiles(errors); Map<String, String> issues = new TreeMap<String, String>();
		 * Map<String, SourceFile> files = new TreeMap<String, SourceFile>(); Properties props = getCheckStyleProps();
		 * for (Error e : errors) { String msg = translate(e.getMsg()); String src = e.getSrc(); issues.put(msg, msg);
		 * SourceFile sf = files.get(src); if (sf == null) { sf = new SourceFile(); sf.setName(src); Set<String>
		 * violations = new HashSet<String>(); sf.setViolations(violations); files.put(src, sf); } List<String> names =
		 * getCheckStyleNames(msg, props); sf.getViolations().addAll(names); } for (String key : issues.keySet()) { //
		 * System.out.println(key); } // System.out.println(); // System.out.println("Files: " + files.size() +
		 * " Issues: " + issues.size()); String xml = toXML(files.values()); System.out.println(xml); } catch (Throwable
		 * t) { t.printStackTrace(); }
		 */
	}

	protected String toXML(Collection<SourceFile> c) {

		StringBuilder sb = new StringBuilder();
		sb.append("<?xml version=\"1.0\"?>\n");
		sb.append("<!DOCTYPE suppressions PUBLIC ");
		sb.append("\"-//Puppy Crawl//DTD Suppressions 1.0//EN\" ");
		sb.append("\"http://www.puppycrawl.com/dtds/suppressions_1_0.dtd\">\n");
		sb.append("<suppressions>\n");
		for (SourceFile sf : c) {
			sb.append(toXML(sf));
		}
		sb.append("</suppressions>\n");
		return sb.toString();
	}

	protected String toXML(SourceFile sf) {
		String file = sf.getName();
		Set<Violation> violations = sf.getViolations();
		StringBuilder sb = new StringBuilder();
		sb.append("  <suppress ");
		sb.append("files=\"" + file + "\" ");
		sb.append("checks=\"");
		int count = 0;
		for (Violation violation : violations) {
			if (count != 0) {
				sb.append("|");
			}
			sb.append(violation.getName());
			count++;
		}
		sb.append('"');
		sb.append("/>\n");
		return sb.toString();
	}

	protected List<String> getCheckStyleNames(String msg, Properties props) {
		List<String> names = new ArrayList<String>();
		for (String key : props.stringPropertyNames()) {
			String value = props.getProperty(key);
			if (value.trim().equals(msg.trim())) {
				int pos = key.indexOf(".");
				if (pos != -1) {
					key = key.substring(0, pos);
				}
				names.add(key);
			}
		}
		if (names.size() == 0) {
			throw new RuntimeException("Unknown message " + msg);
		}
		return names;
	}

	protected Properties getCheckStyleProps() throws IOException {
		String location = "classpath:checkstyle.properties";
		ResourceLoader loader = new DefaultResourceLoader();
		Resource resource = loader.getResource(location);
		Properties props = new Properties();
		props.load(resource.getInputStream());
		return props;
	}

	protected String translate(String msg) {
		if (msg.contains("is a magic number.")) {
			return "is a magic number.";
		}
		if (msg.contains("is preceded with whitespace.")) {
			return "is preceded with whitespace.";
		}
		if (msg.contains("should be declared as final.")) {
			return "should be declared as final.";
		}
		if (msg.contains("Duplicate import")) {
			return "Duplicate import";
		}
		if (msg.contains("Redundant import")) {
			return "Redundant import";
		}
		if (msg.contains("Redundant throws:")) {
			return "Redundant throws:";
		}
		if (msg.contains("Unused import -")) {
			return "Unused import -";
		}
		if (msg.contains("Using the '.*' form of import should be avoided -")) {
			return "Using the '.*' form of import should be avoided -";
		}
		if (msg.contains("must be private and have accessor methods.")) {
			return "must be private and have accessor methods.";
		}
		if (msg.contains("must match pattern '^[a-z][a-zA-Z0-9]*$'.")) {
			return "must match pattern '^[a-z][a-zA-Z0-9]*$'.";
		}
		if (msg.contains("must match pattern '^[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$'.")) {
			return "must match pattern '^[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$'.";
		}
		if (msg.contains("must match pattern '(^[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$)|log|logger'.")) {
			return "must match pattern '(^[A-Z][A-Z0-9]*(_[A-Z0-9]+)*$)|log|logger'.";
		}
		if (msg.contains("must match pattern '^[a-z_][a-zA-Z0-9]*$'.")) {
			return "must match pattern '^[a-z_][a-zA-Z0-9]*$'.";
		}
		if (msg.contains("must match pattern '^[A-Z][a-zA-Z0-9]*$'.")) {
			return "must match pattern '^[A-Z][a-zA-Z0-9]*$'.";
		}
		if (msg.contains("Method length is")) {
			return "Method length is";
		}
		if (msg.contains("File length is")) {
			return "File length is";
		}
		if (msg.contains("construct must use '{}'s.")) {
			return "construct must use '{}'s.";
		}
		if (msg.contains("is not preceded with whitespace.")) {
			return "is not preceded with whitespace.";
		}
		if (msg.contains("is not followed by whitespace.")) {
			return "is not followed by whitespace.";
		}
		if (msg.contains("should be on a new line.")) {
			return "should be on a new line.";
		}
		if (msg.contains("should be on the same line.")) {
			return "should be on the same line.";
		}
		if (msg.contains("Got an exception -")) {
			return "Got an exception -";
		}
		return msg;
	}

	protected List<Error> getErrorObjects(List<String> errors) {
		List<Error> list = new ArrayList<Error>();
		for (String error : errors) {
			list.add(getError(error));
		}
		Collections.sort(errors);
		return list;
	}

	protected Error getError(String s) {
		Error error = new Error();
		error.setSrc(getSrc(s));
		error.setMsg(getMsg(s));
		return error;
	}

	protected String getSrc(String s) {
		String src = StringUtils.substringBetween(s, "[ERROR]", "[").trim();
		return src;
	}

	protected String getMsg(String s) {
		int pos = s.indexOf(".java");
		String msg = s.substring(pos);
		pos = msg.indexOf("]") + 1;
		return msg.substring(pos).trim();
	}

	protected List<String> getErrors() throws IOException {
		InputStream in = null;
		try {
			String location = "classpath:checkstyle.txt";
			ResourceLoader loader = new DefaultResourceLoader();
			Resource resource = loader.getResource(location);
			in = resource.getInputStream();
			String s = IOUtils.toString(in);
			String[] tokens = StringUtils.splitByWholeSeparator(s, "\n");
			List<String> errors = new ArrayList<String>();
			for (int i = 0; i < tokens.length; i++) {
				int pos = tokens[i].indexOf("ERROR");
				if (pos != -1) {
					errors.add(tokens[i].trim());
				}
			}
			return errors;
		} finally {
			IOUtils.closeQuietly(in);
		}
	}
}
