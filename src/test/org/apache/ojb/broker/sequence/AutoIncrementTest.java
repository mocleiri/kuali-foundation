package org.apache.ojb.broker.sequence;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.ojb.broker.Identity;
import org.apache.ojb.junit.PBTestCase;

/**
 * Test autoincrement behaviour of PK and non PK fields.
 *
 * @author <a href="mailto:arminw@apache.org">Armin Waibel</a>
 * @version $Id: AutoIncrementTest.java,v 1.1 2007-08-24 22:17:37 ewestfal Exp $
 */
public class AutoIncrementTest extends PBTestCase
{
   public static void main(String[] args)
    {
        String[] arr = {AutoIncrementTest.class.getName()};
        junit.textui.TestRunner.main(arr);
    }

    public void testAutoIncrement()
    {
        IncrementObject obj_1 = new IncrementObject();
        IncrementObject obj_2 = new IncrementObject();
        broker.beginTransaction();
        broker.store(obj_1);
        broker.store(obj_2);
        broker.commitTransaction();
        // we assume different autogenerated values > 0
        assertTrue(obj_1.getIntId() < obj_2.getIntId());
        assertTrue(obj_1.getIntegerId().intValue() < obj_2.getIntegerId().intValue());
        assertTrue(!obj_1.getStr().equals(obj_2.getStr()));
        assertTrue(obj_2.getIntId() > 0);
        assertTrue(obj_2.getIntegerId().intValue() > 0);
        assertTrue((new Integer(obj_2.getStr())).intValue() > 0);

        Identity oid = new Identity(obj_2, broker);
        IncrementObject newObj_2 = (IncrementObject) broker.getObjectByIdentity(oid);
        broker.beginTransaction();
        broker.store(obj_1);
        broker.store(obj_2);
        broker.commitTransaction();
        assertEquals(obj_2.getIntId(), newObj_2.getIntId());
        assertEquals(obj_2.getIntegerId(), newObj_2.getIntegerId());
        assertEquals(obj_2.getStr(), newObj_2.getStr());
    }

    public static class IncrementObject
    {
        int intId;
        Integer integerId;
        String str;

        public IncrementObject()
        {
        }

        public Integer getIntegerId()
        {
            return integerId;
        }

        public void setIntegerId(Integer integerId)
        {
            this.integerId = integerId;
        }

        public int getIntId()
        {
            return intId;
        }

        public void setIntId(int intId)
        {
            this.intId = intId;
        }

        public String getStr()
        {
            return str;
        }

        public void setStr(String str)
        {
            this.str = str;
        }

        public String toString()
        {
            return ToStringBuilder.reflectionToString(this);
        }
    }
}
