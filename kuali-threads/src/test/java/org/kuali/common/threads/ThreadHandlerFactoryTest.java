package org.kuali.common.threads;

import junit.framework.Assert;

import org.junit.Test;

public class ThreadHandlerFactoryTest {

    ThreadHandlerFactory factory = new ThreadHandlerFactory();

    @Test
    public void testGetInitialThreadCount() {
        int[] split = factory.getDivideEvenly(100, 7);

        Assert.assertEquals(15, split[0]);
        Assert.assertEquals(15, split[1]);
        Assert.assertEquals(14, split[2]);
        Assert.assertEquals(14, split[3]);
        Assert.assertEquals(14, split[4]);
        Assert.assertEquals(14, split[5]);
        Assert.assertEquals(14, split[6]);
    }

}
