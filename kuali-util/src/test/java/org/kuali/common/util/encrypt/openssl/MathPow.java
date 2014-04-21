package org.kuali.common.util.encrypt.openssl;

public final class MathPow {

	public MathPow(String label, double base, double pow) {
		this.label = label;
		this.base = base;
		this.pow = pow;
	}

	private final String label;
	private final double base;
	private final double pow;

	public double getBase() {
		return base;
	}

	public double getPow() {
		return pow;
	}

	public String getLabel() {
		return label;
	}

}
