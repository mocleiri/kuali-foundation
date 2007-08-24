package org.apache.ojb.broker.core;

/* Copyright 2003-2005 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.ojb.broker.accesslayer.ReportQueryRsIterator;
import org.apache.ojb.broker.accesslayer.RsIterator;
import org.apache.ojb.broker.accesslayer.RsQueryObject;
import org.apache.ojb.broker.accesslayer.SqlBasedReportQueryRsIterator;
import org.apache.ojb.broker.metadata.ClassDescriptor;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryBySQL;

/**
 * Factory for ReportQueryRsIterator
 *
 * @author <a href="mailto:jbraeuchi@hotmail.com">Jakob Braeuchi</a>
 * @version $Id: ReportRsIteratorFactoryImpl.java,v 1.1 2007-08-24 22:17:35 ewestfal Exp $
 */
class ReportRsIteratorFactoryImpl implements RsIteratorFactory
{
	private static RsIteratorFactory instance;

	/**
	 * Constructor for RsIteratorFactoryImpl.
	 */
	public ReportRsIteratorFactoryImpl()
	{
		super();
	}

	static RsIteratorFactory getInstance()
	{
		if (instance == null)
		{
			instance = new ReportRsIteratorFactoryImpl();
		}

		return instance;
	}

	/**
	 * @see org.apache.ojb.broker.core.RsIteratorFactory#createRsIterator(Query, ClassDescriptor, PersistenceBrokerImpl)
	 */
	public RsIterator createRsIterator(Query query, ClassDescriptor cld, PersistenceBrokerImpl broker)
	{
		return new ReportQueryRsIterator(RsQueryObject.get(cld, query), broker);
	}

	/**
	 * @see org.apache.ojb.broker.core.RsIteratorFactory#createRsIterator(org.apache.ojb.broker.query.QueryBySQL, ClassDescriptor, PersistenceBrokerImpl)
	 */
	public RsIterator createRsIterator(QueryBySQL query, ClassDescriptor cld, PersistenceBrokerImpl broker)
	{
		return new SqlBasedReportQueryRsIterator(RsQueryObject.get(cld, query), broker);
	}

}
