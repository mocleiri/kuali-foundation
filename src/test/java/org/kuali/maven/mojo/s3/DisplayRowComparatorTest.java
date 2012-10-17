/**
 * Copyright 2004-2012 The Kuali Foundation
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
/**
 * 
 */
package org.kuali.maven.mojo.s3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

/**
 * 
 */
public class DisplayRowComparatorTest {

	@Test
	public void gettersAndSetters() {
		DisplayRowComparator c = new DisplayRowComparator();
		c.setSeparators(DisplayRowComparator.DEFAULT_SEPARATORS);
		Assert.assertEquals(DisplayRowComparator.DEFAULT_SEPARATORS, c.getSeparators());
	}

	@Test
	public void bothNull() {
		DisplayRow dr1 = new DisplayRow();
		DisplayRow dr2 = new DisplayRow();
		List<DisplayRow> drs = new ArrayList<DisplayRow>();
		Comparator<DisplayRow> c = new DisplayRowComparator();
		drs.add(dr1);
		drs.add(dr2);

		Collections.sort(drs, c);
	}

	@Test
	public void oneNull() {
		DisplayRow dr1 = new DisplayRow();
		dr1.setShow("1.1");
		DisplayRow dr2 = new DisplayRow();
		List<DisplayRow> drs = new ArrayList<DisplayRow>();
		Comparator<DisplayRow> c = new DisplayRowComparator();
		drs.add(dr1);
		drs.add(dr2);

		Collections.sort(drs, c);
		Assert.assertEquals(null, drs.get(0).getShow());
	}

	@Test
	public void twoNull() {
		DisplayRow dr1 = new DisplayRow();
		DisplayRow dr2 = new DisplayRow();
		dr2.setShow("1.1");
		DisplayRow dr3 = new DisplayRow();
		List<DisplayRow> drs = new ArrayList<DisplayRow>();
		Comparator<DisplayRow> c = new DisplayRowComparator();
		drs.add(dr1);
		drs.add(dr2);
		drs.add(dr3);

		Collections.sort(drs, c);
		Assert.assertEquals(null, drs.get(0).getShow());
		Assert.assertEquals("1.1", drs.get(2).getShow());
	}

	@Test
	public void basicVersionCompareTo() {
		String show1 = "1.1.1";
		String show2 = "1.1.0";
		DisplayRow dr1 = new DisplayRow();
		dr1.setShow(show1);
		DisplayRow dr2 = new DisplayRow();
		dr2.setShow(show2);
		List<DisplayRow> drs = new ArrayList<DisplayRow>();
		Comparator<DisplayRow> c = new DisplayRowComparator();
		drs.add(dr1);
		drs.add(dr2);

		Collections.sort(drs, c);

		Assert.assertEquals("1.1.0", drs.get(0).getShow());
	}

	@Test
	public void nonAlphaVersionCompareTo() {
		String show1 = "1.1.17";
		String show2 = "1.1.2";
		DisplayRow dr1 = new DisplayRow();
		dr1.setShow(show1);
		DisplayRow dr2 = new DisplayRow();
		dr2.setShow(show2);
		List<DisplayRow> drs = new ArrayList<DisplayRow>();
		Comparator<DisplayRow> c = new DisplayRowComparator();
		drs.add(dr1);
		drs.add(dr2);

		Collections.sort(drs, c);

		Assert.assertEquals("1.1.2", drs.get(0).getShow());
	}

	@Test
	public void snapshotCompareTo() {
		String show1 = "1.1.17-SNAPSHOT";
		String show2 = "1.1.17";
		DisplayRow dr1 = new DisplayRow();
		dr1.setShow(show1);
		DisplayRow dr2 = new DisplayRow();
		dr2.setShow(show2);
		List<DisplayRow> drs = new ArrayList<DisplayRow>();
		Comparator<DisplayRow> c = new DisplayRowComparator();
		drs.add(dr1);
		drs.add(dr2);

		Collections.sort(drs, c);

		Assert.assertEquals("1.1.17-SNAPSHOT", drs.get(0).getShow());
	}

	@Test
	public void milestoneCompareTo() {
		String show1 = "1.1.17-M2";
		String show2 = "1.1.17-M2-SNAPSHOT";
		DisplayRow dr1 = new DisplayRow();
		dr1.setShow(show1);
		DisplayRow dr2 = new DisplayRow();
		dr2.setShow(show2);
		List<DisplayRow> drs = new ArrayList<DisplayRow>();
		Comparator<DisplayRow> c = new DisplayRowComparator();
		drs.add(dr1);
		drs.add(dr2);

		Collections.sort(drs, c);

		Assert.assertEquals("1.1.17-M2-SNAPSHOT", drs.get(0).getShow());
	}

	@Test
	public void sameCompareTo() {
		String show1 = "1.1.17-M2";
		String show2 = "1.1.17-M2";
		DisplayRow dr1 = new DisplayRow();
		dr1.setShow(show1);
		DisplayRow dr2 = new DisplayRow();
		dr2.setShow(show2);
		List<DisplayRow> drs = new ArrayList<DisplayRow>();
		Comparator<DisplayRow> c = new DisplayRowComparator();
		drs.add(dr1);
		drs.add(dr2);

		Collections.sort(drs, c);

		Assert.assertEquals("1.1.17-M2", drs.get(0).getShow());
	}
}
