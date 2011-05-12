/*
 * Copyright 2007 The Kuali Foundation
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
package org.kuali.rice.core.framework.persistence.jpa.type;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.hibernate.HibernateException;
import org.hibernate.type.StandardBasicTypes;
import org.kuali.rice.core.util.type.KualiDecimal;
import org.kuali.rice.core.util.type.KualiInteger;

/**
 * Kuali integer percentage type converteger
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public class HibernateKualiIntegerPercentageFieldType extends HibernateKualiDecimalPercentageFieldType {

	/**
	 * This overridden method ...
	 * 
	 * @see HibernateImmutableValueUserType#nullSafeGet(java.sql.ResultSet, java.lang.String[], java.lang.Object)
	 */
	@Override
	public Object nullSafeGet(ResultSet rs, String[] names, Object owner)
	throws HibernateException, SQLException {
		
		Object source = StandardBasicTypes.BIG_DECIMAL.nullSafeGet(rs, names[0]);
		
		if (source != null && source instanceof BigDecimal) {
			return new KualiInteger(((KualiDecimal) (super.getConvertedToKualiDecimal(source))).bigDecimalValue());
		} else {
			return null;
		}
	}

	/**
	 * This overridden method ...
	 * 
	 * @see HibernateImmutableValueUserType#nullSafeSet(java.sql.PreparedStatement, java.lang.Object, int)
	 */
	@Override
	public void nullSafeSet(PreparedStatement st, Object source, int index)
	throws HibernateException, SQLException {
		
		Object converted = null;
		if (source != null && source instanceof BigDecimal) {
			converted = new KualiInteger(((KualiDecimal) (super.getConvertedPercentage(source))).bigDecimalValue());
		} 
		
        if (converted == null) {
        	st.setNull(index, Types.BIGINT); 
        } else {
        	st.setLong(index, ((Long)converted).longValue()); 
        }
	}

	/**
	 * Returns String.class
	 * 
	 * @see org.hibernate.usertype.UserType#returnedClass()
	 */
	public Class returnedClass() {
		return BigInteger.class;
	}

	/**
	 * Returns an array with the SQL VARCHAR type as the single member
	 * 
	 * @see org.hibernate.usertype.UserType#sqlTypes()
	 */
	public int[] sqlTypes() {
		return new int[] { Types.BIGINT};
	}


}
