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

package org.kuali.rice.ksb.messaging;

import org.kuali.rice.core.api.config.property.ConfigContext;
import org.kuali.rice.ksb.messaging.resourceloader.KSBResourceLoaderFactory;
import org.kuali.rice.ksb.util.KSBConstants;


/**
 * Holder for common testing code. 
 * 
 * @author Kuali Rice Team (rice.collab@kuali.org)
 *
 */
public final class KSBTestUtils {
	
	private KSBTestUtils() {
		throw new UnsupportedOperationException("do not call");
	}

    public static void setMessagingToAsync() {
        setupMessaging("async");
    }

    public static void setMessagingToSync() {
        setupMessaging(KSBConstants.MESSAGING_SYNCHRONOUS);
    }

    private static void setupMessaging(String value) {
        ConfigContext.getCurrentContextConfig().putProperty(KSBConstants.Config.MESSAGE_DELIVERY, value);
        ((Runnable) KSBResourceLoaderFactory.getRemoteResourceLocator()).run();
    }

}
