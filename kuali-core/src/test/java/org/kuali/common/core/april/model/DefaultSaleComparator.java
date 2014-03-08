package org.kuali.common.core.april.model;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;

// Singleton enum pattern
public enum DefaultSaleComparator implements Comparator<Sale> {
	INSTANCE;

	@Override
	public int compare(Sale one, Sale two) {
		return ComparisonChain.start().compare(one.getLevel(), two.getLevel()).compare(one.getArea(), two.getArea()).compare(one.getSection(), two.getSection())
				.compare(one.getRow(), two.getRow()).compare(one.getPrice(), two.getPrice()).result();
	}

}
