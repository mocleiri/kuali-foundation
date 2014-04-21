package org.kuali.common.util.encrypt.openssl;

public final class MathPow {

	public MathPow(int base, int pow) {
		this.base = base;
		this.pow = pow;
	}

	private final int base;
	private final int pow;

	public int getBase() {
		return base;
	}

	public int getPow() {
		return pow;
	}

}
