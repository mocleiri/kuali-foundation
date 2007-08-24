package org.apache.ojb.broker.accesslayer;

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
import org.apache.ojb.broker.util.factory.ConfigurableFactory;

/**
 *
 * @author <a href="mailto:armin@codeAuLait.de">Armin Waibel</a>
 * @version $Id: JdbcAccessFactory.java,v 1.1 2007-08-24 22:17:30 ewestfal Exp $
 */
public class JdbcAccessFactory extends ConfigurableFactory
{
    private static JdbcAccessFactory singleton;

    public static synchronized JdbcAccessFactory getInstance()
    {
        if (singleton == null)
        {
            singleton = new JdbcAccessFactory();
        }
        return singleton;
    }

    protected String getConfigurationKey()
    {
        return "JdbcAccessClass";
    }

    public JdbcAccess createJdbcAccess(PersistenceBroker broker)
    {
        return (JdbcAccess) createNewInstance(PersistenceBroker.class, broker);
    }
}
