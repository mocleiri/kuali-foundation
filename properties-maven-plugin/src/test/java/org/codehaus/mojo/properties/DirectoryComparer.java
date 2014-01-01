/**
 * Copyright 2009-2014 The Kuali Foundation
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class DirectoryComparer {

    @Test
    public void test() {
        try {
            File dir1 = new File("/Users/jeffcaddel/ws/OLE-2987/ole/build/war");
            // File dir1 = new File("/Users/jeffcaddel/ws/OLE-2987/ole/target/foo");
            File dir2 = new File("/Users/jeffcaddel/ws/OLE-2987/ole/target/ole-fs-0.8.0-h-SNAPSHOT");
            DD one = getDD(dir1);
            DD two = getDD(dir2);
            execute(one, two);
        } catch (Throwable t) {
            t.printStackTrace();
        }

    }

    protected DD getDD(File dir) {
        List<File> files = new ArrayList<File>(FileUtils.listFiles(dir, null, true));
        Map<String, File> map = new HashMap<String, File>();
        for (File file : files) {
            String key = getRelativePath(dir, file);
            map.put(key, file);
        }
        DD dd = new DD();
        dd.setDir(dir);
        dd.setFiles(files);
        dd.setFileMap(map);
        return dd;
    }

    protected void execute(DD one, DD two) {
        List<File> u1 = getUnmatchedFiles(one, two);
        List<File> u2 = getUnmatchedFiles(two, one);
        System.out.println(u1.size() + " " + u2.size());
        for (File u : u1) {
            System.out.println(u);
        }
        System.out.println();
        for (File u : u2) {
            System.out.println(u);
        }
    }

    protected List<File> getUnmatchedFiles(DD one, DD two) {
        List<File> unmatched = new ArrayList<File>();
        Map<String, File> fileMap = two.getFileMap();
        for (File file : one.getFiles()) {
            String relativePath = getRelativePath(one.getDir(), file);
            File match = fileMap.get(relativePath);
            if (match == null) {
                unmatched.add(file);
            }
        }
        return unmatched;
    }

    protected String getRelativePath(File dir, File file) {
        String dirPath = dir.getAbsolutePath();
        String path = file.getAbsolutePath();
        return path.replace(dirPath, "");
    }
}
