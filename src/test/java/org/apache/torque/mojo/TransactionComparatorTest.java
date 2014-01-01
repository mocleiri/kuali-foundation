/**
 * Copyright 2004-2014 The Kuali Foundation
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
package org.apache.torque.mojo;

import java.util.Collections;
import java.util.Comparator;
import java.util.Vector;

import org.apache.torque.util.TransactionComparator;
import org.junit.Test;
import org.kuali.db.Transaction;

public class TransactionComparatorTest {

	@Test
	public void testComparator() {
		Comparator<Transaction> comparator = new TransactionComparator<Transaction>("ks-embedded-db");
		Vector<Transaction> transactions = new Vector<Transaction>();
		transactions.add(getTransaction("003.sql"));
		transactions.add(getTransaction("004.sql"));
		transactions.add(getTransaction("001.sql"));
		transactions.add(getTransaction("sql/oracle/ks-embedded-db.sql"));
		transactions.add(getTransaction("002.sql"));
		transactions.add(getTransaction("sql/oracle/ks-embedded-db-constraints.sql"));
		transactions.add(getTransaction("001.sql"));
		transactions.add(getTransaction("000.sql"));
		transactions.add(getTransaction("zzz000.sql"));
		Collections.sort(transactions, comparator);
		showTransactions(transactions);
	}

	protected void showTransactions(Vector<Transaction> transactions) {
		for (Transaction t : transactions) {
			System.out.println(t.getResourceLocation());
		}
	}

	protected Transaction getTransaction(String location) {
		Transaction t = new Transaction();
		t.setResourceLocation(location);
		return t;
	}
}
