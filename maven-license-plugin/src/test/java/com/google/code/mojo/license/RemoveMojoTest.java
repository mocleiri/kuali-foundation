/**
 * Copyright (C) 2008 http://code.google.com/p/maven-license-plugin/
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *         http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.code.mojo.license;

import com.google.code.mojo.license.util.FileUtils;
import org.apache.maven.plugin.testing.stubs.MavenProjectStub;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

import java.io.File;

/**
 * @author Mathieu Carbou (mathieu.carbou@gmail.com)
 */
public final class RemoveMojoTest {

    @Test
    public void test_remove() throws Exception {
        File tmp = new File("target/test/remove");
        tmp.mkdirs();
        FileUtils.copyFileToFolder(new File("src/test/resources/remove/doc1.txt"), tmp);
        FileUtils.copyFileToFolder(new File("src/test/resources/remove/doc2.txt"), tmp);
        
        LicenseRemoveMojo remove = new LicenseRemoveMojo();
        remove.basedir = tmp;
        remove.header = "src/test/resources/remove/header.txt";
        remove.project = new MavenProjectStub();
        remove.execute();

        assertEquals(FileUtils.read(new File(tmp, "doc1.txt"), System.getProperty("file.encoding")), "some data\r\n");
        assertEquals(FileUtils.read(new File(tmp, "doc2.txt"), System.getProperty("file.encoding")), "some data\r\n");
    }
    
}