/**
 * Copyright 2004-2011 The Kuali Foundation
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
package org.kuali.maven.common;

import java.util.Properties;

import junit.framework.Assert;

import org.junit.Test;
import org.springframework.util.PropertyPlaceholderHelper;

public class PropertiesUtilsTest {

    @Test
    public void test() {
        Properties properties = new Properties();
        properties.setProperty("a", "1");
        properties.setProperty("b", "2");
        properties.setProperty("c", "3");
        properties.setProperty("foo", "${a}");

        String s = "The value is ${foo}";
        PropertyPlaceholderHelper helper = new PropertyPlaceholderHelper("${", "}");
        String expected = "The value is 1";
        String resolved = helper.replacePlaceholders(s, properties);
        Assert.assertEquals(expected, resolved);
    }

}
