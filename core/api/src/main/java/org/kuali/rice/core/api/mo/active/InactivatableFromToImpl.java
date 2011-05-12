/*
 * Copyright 2006-2011 The Kuali Foundation
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

package org.kuali.rice.core.api.mo.active;

import java.sql.Timestamp;

public abstract class InactivatableFromToImpl implements InactivatableFromTo {
    protected Timestamp activeFromDate;
	protected Timestamp activeToDate;


    @Override
    public Timestamp getActiveFromDate() {
        return activeFromDate;
    }

    @Override
    public Timestamp getActiveToDate() {
        return activeToDate;
    }


    public boolean isActive() {
        long asOfDate = System.currentTimeMillis();

		return computeActive(asOfDate);
    }


    public boolean isActive(Timestamp activeAsOfDate) {
        long asOfDate = System.currentTimeMillis();
		if (activeAsOfDate != null) {
			asOfDate = activeAsOfDate.getTime();
		}

		return computeActive(asOfDate);
    }


    private boolean computeActive(Long asOfDate) {
          return (activeFromDate == null || asOfDate >= activeFromDate.getTime())
				&& (activeToDate == null || asOfDate < activeToDate.getTime());
    }


}
