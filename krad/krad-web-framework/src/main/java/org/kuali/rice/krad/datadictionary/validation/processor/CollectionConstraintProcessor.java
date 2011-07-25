/*
 * Copyright 2011 The Kuali Foundation
 *
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.opensource.org/licenses/ecl1.php
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kuali.rice.krad.datadictionary.validation.processor;

import org.kuali.rice.krad.datadictionary.validation.constraint.Constraint;

import java.util.Collection;

/**
 * This is a marker interface for 'collection constraint processors', that is - a constraint processor that tests collections
 * rather than their elements. Maybe the best example of a collection-based constraint is a constraint on the number of elements 
 * in that collection -- for example, a constraint that ensures that there are between 1 and 10 elements in a collection. 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org) 
 */
public interface CollectionConstraintProcessor<T extends Collection<?>, C extends Constraint> extends ConstraintProcessor<T, C> {
	// Empty
}
