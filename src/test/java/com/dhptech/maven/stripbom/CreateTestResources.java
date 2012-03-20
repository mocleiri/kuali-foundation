/**
 * Copyright 2011-2012 The Kuali Foundation
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
package com.dhptech.maven.stripbom;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import org.apache.commons.io.FileUtils;

/**
 *
 */
public class CreateTestResources {
    public static void main(String[] args) {
        try {
            String s = "The quick brown fox jumped over the lazy dog";
            byte[] normal = s.getBytes();
            List<byte[]> boms = new StripMojo().getBoms();
            File directory = new File("/Users/jeffcaddel/ws/bom-maven-plugin/src/test/resources");
            File file1 = new File(directory + "/normal.txt");
            FileUtils.writeByteArrayToFile(file1, normal);
            File file2 = new File(directory + "/utf8-bom.txt");
            File file3 = new File(directory + "/utf16-bom1.txt");
            File file4 = new File(directory + "/utf16-bom2.txt");
            write(file2, boms.get(0), normal);
            write(file3, boms.get(1), normal);
            write(file4, boms.get(2), normal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected static void write(File file, byte[] bom, byte[] data) throws IOException {
        OutputStream out = FileUtils.openOutputStream(file);
        out.write(bom);
        out.write(data);
        out.close();
    }
}
