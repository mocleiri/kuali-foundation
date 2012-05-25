package org.codehaus.mojo.properties;

import org.junit.Test;

public class TestParseVersion {

    @Test
    public void test() {
        ParseVersionPropertiesMojo mojo = new ParseVersionPropertiesMojo();

        String s = "2.2.0-build-22-SNAPSHOT";
        Version version = mojo.parseVersion(s);
        System.out.println(version.getQualifier());
        System.out.println(mojo.trimSnapshot(s));
    }

}
