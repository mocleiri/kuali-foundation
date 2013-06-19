/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.common.impex.schema.impl;

import java.security.InvalidParameterException;

import org.kuali.common.impex.model.ForeignKeyConstraintType;

public abstract class AbstractForeignKeySqlProducer {

    protected static final String CASCADE_CONSTRAINT = "CASCADE";
    protected static final String SET_DEFAULT_CONSTRAINT = "SET DEFAULT";
    protected static final String SET_NULL_CONSTRAINT = "SET NULL";
    protected static final String RESTRICT_CONSTRAINT = "RESTRICT";
    protected static final String NO_ACTION_CONSTRAINT = "NO ACTION";

    protected String translateForeignKeyConstraint(ForeignKeyConstraintType constraintType) {
        switch (constraintType) {
            case CASCADE:
                return CASCADE_CONSTRAINT;
            case SET_DEFAULT:
                return SET_DEFAULT_CONSTRAINT;
            case SET_NULL:
                return SET_NULL_CONSTRAINT;
            case RESTRICT:
                return RESTRICT_CONSTRAINT;
            case NO_ACTION:
                return NO_ACTION_CONSTRAINT;
        }
        
        throw new InvalidParameterException("Unable to translate ForeignKeyConstraintType of " + constraintType);
    }
    
}
