/**
 * Copyright 2010-2013 The Kuali Foundation
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
package org.kuali.common.util.base;

import static com.google.common.collect.Lists.newArrayList;
import static org.kuali.common.util.base.Exceptions.illegalState;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Callables {

	public static <T> List<T> submit(List<Callable<T>> callables) {
		ExecutorService pool = Executors.newFixedThreadPool(callables.size());
		List<Future<T>> futures = newArrayList();
		for (Callable<T> callable : callables) {
			Future<T> future = pool.submit(callable);
			futures.add(future);
		}
		List<T> elements = newArrayList();
		try {
			for (Future<T> future : futures) {
				T element = future.get();
				elements.add(element);
			}
		} catch (Exception e) {
			throw illegalState(e, "unexpected exception waiting for future");
		}
		return elements;
	}
}
