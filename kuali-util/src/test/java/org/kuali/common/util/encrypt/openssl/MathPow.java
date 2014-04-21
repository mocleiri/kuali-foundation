package org.kuali.common.util.encrypt.openssl;

public final class MathPow {

	public MathPow(String label, int base, int pow) {
		this.label = label;
		this.base = base;
		this.pow = pow;
	}

	private final String label;
	private final int base;
	private final int pow;

	public int getBase() {
		return base;
	}

	public int getPow() {
		return pow;
	}

	public String getLabel() {
		return label;
	}

}
