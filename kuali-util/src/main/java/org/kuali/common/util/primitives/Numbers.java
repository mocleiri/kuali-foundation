package org.kuali.common.util.primitives;

public final class Numbers {

	private Numbers() {
	}

	public static boolean isByte(long number) {
		return number >= Byte.MIN_VALUE && number <= Byte.MAX_VALUE;
	}

	public static boolean isShort(long number) {
		return number >= Short.MIN_VALUE && number <= Short.MAX_VALUE;
	}

	public static boolean isInt(long number) {
		return number >= Integer.MIN_VALUE && number <= Integer.MAX_VALUE;
	}

	public static Number narrow(int number) {
		if (isByte(number)) {
			return (byte) number;
		} else if (isShort(number)) {
			return (short) number;
		} else {
			return number;
		}
	}

	public static Number narrow(long number) {
		if (isByte(number)) {
			return (byte) number;
		} else if (isShort(number)) {
			return (short) number;
		} else if (isInt(number)) {
			return (int) number;
		} else {
			return number;
		}
	}
}
