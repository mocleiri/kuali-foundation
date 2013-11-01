package org.kuali.common.devops.aws;

import com.amazonaws.services.ec2.model.Tag;

public class Tags {

	public enum Name {

		MASTER("ci.master"), SLAVE("ci.slave"), NEXUS("nexus");

		public static final String NAME = "Name";

		private final String value;

		private Name(String value) {
			this.value = value;
		}

		public Tag getTag() {
			return new Tag(NAME, value);
		}
	}

	public enum Vendor {

		JENKINS("jenkins"), TOMCAT("tomcat"), ORACLE("oracle"), MYSQL("mysql");

		public static final String NAME = "Vendor";

		private final String value;

		private Vendor(String value) {
			this.value = value;
		}

		public Tag getTag() {
			return new Tag(NAME, value);
		}
	}

	public enum Stack {

		DEVELOPMENT("development"), TESTING("testing"), STAGING("staging"), PRODUCTION("production");

		public static final String NAME = "Stack";

		private final String value;

		private Stack(String value) {
			this.value = value;
		}

		public Tag getTag() {
			return new Tag(NAME, value);
		}
	}

	public enum Team {

		DEVOPS("devops"), INFRASTRUCTURE("infrastructure");

		public static final String NAME = "Team";

		private final String value;

		private Team(String value) {
			this.value = value;
		}

		public Tag getTag() {
			return new Tag(NAME, value);
		}
	}

	public enum Project {

		SHARED("shared"), STUDENT("student"), OLE("ole"), RICE("rice"), MOBILITY("mobility"), KPME("kpme"), KFS("kfs"), READY("ready");

		public static final String NAME = "Project";

		private final String value;

		private Project(String value) {
			this.value = value;
		}

		public Tag getTag() {
			return new Tag(NAME, value);
		}

	}

}
