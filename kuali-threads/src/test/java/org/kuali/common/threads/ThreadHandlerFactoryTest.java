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
package org.kuali.common.threads;

import junit.framework.Assert;

import org.junit.Test;

public class ThreadHandlerFactoryTest {

    ThreadHandlerFactory factory = new ThreadHandlerFactory();

    @Test
    public void testGetDivideEvenly() {
        int[] split = factory.getDivideEvenly(100, 7);

        Assert.assertEquals(15, split[0]);
        Assert.assertEquals(15, split[1]);
        Assert.assertEquals(14, split[2]);
        Assert.assertEquals(14, split[3]);
        Assert.assertEquals(14, split[4]);
        Assert.assertEquals(14, split[5]);
        Assert.assertEquals(14, split[6]);
    }

    @Test
    public void testGetThreadCount() {
        int threads1 = factory.getThreadCount(1, 0, 0, 0);
        int threads2 = factory.getThreadCount(50, 10, 21826, 0);
        System.out.println(threads1);
        System.out.println(threads2);
    }

}
