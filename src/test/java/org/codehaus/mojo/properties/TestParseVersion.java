/**
 * Copyright 2009-2012 The Kuali Foundation
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
package org.codehaus.mojo.properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import org.junit.Test;

public class TestParseVersion {

    // @Test
    public void test1() {
        try {
            WriteProjectProperties wpp = new WriteProjectProperties();
            wpp.setEscapeChars("cr,lf,tab");
            String filename = "/Users/jeffcaddel/sts/3.0.0.M3/workspace/properties-maven-plugin/src/test/resources/3.properties";
            File file = new File(filename);
            InputStream in = new FileInputStream(file);
            Properties props = new Properties();
            props.load(in);

            List<String> escapeTokens = wpp.getEscapeChars(wpp.getEscapeChars());
            String content = wpp.getContent(null, props, escapeTokens);

            System.out.println(props.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test() {
        ParseVersionPropertiesMojo mojo = new ParseVersionPropertiesMojo();

        String s = "2.2.0-build-22-SNAPSHOT";
        Version version = mojo.parseVersion(s);
        System.out.println(version.getQualifier());
        System.out.println(mojo.trimSnapshot(s));
    }

}
