package org.kuali.common.jdbc;

public class JdbcException extends RuntimeException {

	private static final long serialVersionUID = 7049873823068519301L;

	public JdbcException() {
	}

	public JdbcException(String msg) {
		super(msg);
	}

	public JdbcException(Throwable e) {
		super(e);
	}

	public JdbcException(String msg, Throwable e) {
		super(msg, e);
	}

}
