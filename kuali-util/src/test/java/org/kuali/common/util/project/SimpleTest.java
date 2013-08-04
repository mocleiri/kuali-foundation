package org.kuali.common.util.project;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.kuali.common.util.identify.StringIdentifier;

public class SimpleTest {

	@Test
	public void test() {
		try {
			ProjectIdentifier p1 = new ImmutableProjectIdentifier("org.kuali.common", "kuali-util");
			ProjectIdentifier p2 = new ImmutableProjectIdentifier("org.kuali.common", "kuali-util");
			System.out.println(p1);
			System.out.println(p2);
			System.out.println(p1.equals(p2));
			System.out.println(p1.hashCode());
			System.out.println(p2.hashCode());
			StringIdentifier aa = new StringIdentifier("Aa");
			StringIdentifier bb = new StringIdentifier("BB");
			// Prove that the hash codes are the same
			Assert.assertEquals(aa.hashCode(), bb.hashCode());
			// Prove that an equals comparison fails even though the hash codes are identical
			Assert.assertFalse(aa.equals(bb));
			Map<StringIdentifier, String> map = new HashMap<StringIdentifier, String>();
			map.put(aa, "foo");
			map.put(bb, "bar");
			System.out.println(map.size());
			StringIdentifier test = new StringIdentifier("Aa");
			String string = map.get(test);
			System.out.println(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
