package org.kuali.common.devops.aws.tag;

public class Tags {

	public enum Name {

		MASTER("ci.master"), SLAVE("ci.slave"), NEXUS("nexus");

		public static final String NAME = "Name";

		private final String value;

		private Name(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum Stack {

		DEVELOPMENT("development"), TESTING("testing"), STAGING("staging"), PRODUCTION("production");

		public static final String NAME = "Stack";

		private final String value;

		private Stack(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum Team {

		DEVOPS("devops"), INFRASTRUCTURE("infrastructure");

		public static final String NAME = "Team";

		private final String value;

		private Team(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}
	}

	public enum Project {

		SHARED("shared"), KS("ks"), OLE("ole"), RICE("rice"), MOBILITY("mobility"), KPME("kpme"), KFS("kfs"), READY("ready");

		public static final String NAME = "Project";

		private final String value;

		private Project(String value) {
			this.value = value;
		}

		public String getValue() {
			return value;
		}

	}

}
