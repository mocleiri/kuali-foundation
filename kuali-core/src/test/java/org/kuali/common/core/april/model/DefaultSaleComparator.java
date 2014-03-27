/**
 * Copyright 2014-2014 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl2.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.common.core.april.model;

import java.util.Comparator;

import com.google.common.collect.ComparisonChain;

// Singleton enum pattern
public enum DefaultSaleComparator implements Comparator<Sale> {
	INSTANCE;

	@Override
	public int compare(Sale one, Sale two) {
		// return ComparisonChain.start().compare(one.getLevel(), two.getLevel()).compare(one.getArea(), two.getArea()).compare(one.getSection(), two.getSection())
		// .compare(one.getRow(), two.getRow()).compare(one.getPrice(), two.getPrice()).result();
		// return ComparisonChain.start().compare(one.getLevel(), two.getLevel()).compare(one.getArea(), two.getArea()).compare(one.getPrice() * -1, two.getPrice() * -1).result();
		return ComparisonChain.start().compare(one.getDate(), two.getDate()).compare(one.getLevel(), two.getLevel()).compare(one.getArea(), two.getArea())
				.compare(one.getPrice(), two.getPrice()).result();
	}

}
