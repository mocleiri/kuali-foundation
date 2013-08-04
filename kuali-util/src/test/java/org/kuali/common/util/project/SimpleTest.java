package org.kuali.common.util.project;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.kuali.common.util.identity.ImmutableIdentifier;

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
			ImmutableIdentifier aa = new ImmutableIdentifier("Aa");
			ImmutableIdentifier bb = new ImmutableIdentifier("BB");
			System.out.println(aa.hashCode());
			System.out.println(bb.hashCode());
			System.out.println(aa.equals(bb));
			Map<ImmutableIdentifier, String> map = new HashMap<ImmutableIdentifier, String>();
			map.put(aa, "foo");
			map.put(bb, "bar");
			System.out.println(map.size());
			ImmutableIdentifier test = new ImmutableIdentifier("Aa");
			String string = map.get(test);
			System.out.println(string);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
