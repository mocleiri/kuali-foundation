package org.kuali.common.util.metainf.service;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.kuali.common.util.LocationUtils;
import org.kuali.common.util.file.CanonicalFile;

public class SimpleTest {

	@Test
	public void test() {
		try {
			String one = "one";
			String two = new String("one");
			String three = new StringBuilder("one").toString();

			System.out.println(one.hashCode());
			System.out.println(two.hashCode());
			System.out.println(three.hashCode());

			Map<String, String> map = new HashMap<String, String>();
			map.put(one, one);
			map.put(two, two);

			File f1 = new CanonicalFile("/Users/jcaddel/ws");
			File f2 = new CanonicalFile("/Users/jcaddel/sts/3.1.0.RELEASE/workspace");
			File f3 = new File(LocationUtils.getCanonicalPath(f1));
			File f4 = new File(f1, "kuali-util");
			File f5 = new File(f3, "kuali-util");
			File f6 = new CanonicalFile("blibbity-blabbity");
			File f7 = new CanonicalFile("");
			File f8 = new CanonicalFile(".");
			System.out.println(f5.equals(f4));

			System.out.println(f1.hashCode());
			System.out.println(f2.hashCode());
			System.out.println(f3.hashCode());
			System.out.println(f1.equals(f2));
			System.out.println(f2.equals(f3));
			System.out.println(f6);
			System.out.println(f7);
			System.out.println(f8);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
