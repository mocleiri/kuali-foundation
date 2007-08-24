package xdoclet.modules.ojb.tests;

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

import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.accesslayer.QueryCustomizer;
import org.apache.ojb.broker.metadata.CollectionDescriptor;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;

/**
 * Query customizer class used in some of the unit tests.
 */
public class TestQueryCustomizer implements QueryCustomizer
{
    public Query customizeQuery(Object anObject, PersistenceBroker aBroker, CollectionDescriptor aCod, QueryByCriteria aQuery)
    {
        return null;
    }

    public void addAttribute(String attributeName, String attributeValue)
    {
    }

    public String getAttribute(String attributeName, String defaultValue)
    {
        return null;
    }

    public String getAttribute(String attributeName)
    {
        return null;
    }
}
