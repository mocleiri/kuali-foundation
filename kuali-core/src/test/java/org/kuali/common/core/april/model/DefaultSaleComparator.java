package org.kuali.common.core.april.model;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;

public enum DefaultSaleComparator implements Comparator<Sale> {
	INSTANCE;

	@Override
	public int compare(Sale one, Sale two) {
		return ComparisonChain.start().compare(one.getLevel(), two.getLevel()).compare(one.getArea(), two.getArea()).result();
	}

}
