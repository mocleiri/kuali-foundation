package org.springframework.beans;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

public class LicenseHeaderTest {

    public static void main(String[] args) {
        new LicenseHeaderTest().exec(args);
    }

    protected void exec(String[] args) {
        try {
            long beg = System.currentTimeMillis();
            String dir = "c:/eclipse/sts/2.6.1/ide/ws/rice-trunk";
            List<File> xmlFiles = getFiles(new File(dir));
            System.out.println(xmlFiles.size());
            List<File> problemFiles = getProblemFiles(xmlFiles);
            String[] invalidHeaders = getInvalidHeaders();
            System.out.println(problemFiles.size());
            for (int i = 0; i < problemFiles.size(); i++) {
                File problemFile = problemFiles.get(i);
                System.out.println(problemFile.getAbsolutePath());
                reWrite(problemFile, invalidHeaders);
            }
            long end = System.currentTimeMillis();
            long elapsed = end - beg;
            double seconds = elapsed / 1000D;
            System.out.println(seconds);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    protected void reWrite(File file, String[] invalidHeaders) throws IOException {
        InputStream in = new FileInputStream(file);
        String oldContent = IOUtils.toString(in);
        in.close();
        String newContent = getGoodContent(oldContent, invalidHeaders);
        OutputStream out = new FileOutputStream(file);
        IOUtils.write(newContent.getBytes(), out);
        out.close();
    }

    protected String getGoodContent(String content, String[] invalidHeaders) {
        for (String invalidHeader : invalidHeaders) {
            content = content.replace(invalidHeader, "");
        }
        return content;

    }

    protected String[] getInvalidHeaders() throws IOException {
        String baseDir = "C:/eclipse/sts/2.6.1/ide/ws/kuali-spring-util/src/test/resources";
        String[] filenames = { "invalid1.txt", "invalid2.txt", "invalid3.txt", "invalid4.txt", "invalid5.txt" };
        String[] invalidHeaders = new String[filenames.length];
        int count = 0;
        for (String filename : filenames) {
            String fullFileName = baseDir + "/" + filename;
            String content = IOUtils.toString(new FileInputStream(fullFileName));
            invalidHeaders[count++] = content;
        }
        return invalidHeaders;
    }

    protected List<File> getProblemFiles(List<File> files) throws IOException {
        List<File> problemFiles = new ArrayList<File>();
        for (File file : files) {
            List<String> lines = IOUtils.readLines(new FileInputStream(file));

            int prologIndex = getPrologIndex(lines);
            if (prologIndex != -1 && prologIndex != 0) {
                // problemFiles.add(file);
                continue;
            }

            boolean copyRightIssue = isMultipleCopyRights(lines);
            if (copyRightIssue) {
                problemFiles.add(file);
                continue;
            }
        }
        return problemFiles;
    }

    protected boolean isMultipleCopyRights(List<String> strings) {
        int count = 0;
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            String lowerCase = s.toLowerCase();
            int pos = lowerCase.indexOf("copyright");
            if (pos != -1) {
                count++;
            }
        }
        return count > 1;
    }

    protected int getPrologIndex(List<String> strings) {
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            if (isProlog(s)) {
                return i;
            }
        }
        return -1;
    }

    protected boolean isProlog(String s) {
        String lowerCase = s.toLowerCase();
        int pos1 = lowerCase.indexOf("<?xml");
        int pos2 = lowerCase.indexOf("?>");
        boolean flag1 = pos1 != -1 && pos2 != -1;
        boolean flag2 = pos2 > pos1;
        return flag1 && flag2;
    }

    protected boolean isSkip(File file) {
        String name = file.getAbsolutePath();
        if (name.contains(".svn")) {
            return true;
        }
        if (name.contains("config\\ide")) {
            return true;
        }
        if (name.contains("db\\impex")) {
            return true;
        }
        return false;
    }

    protected boolean isAdd(File file) {
        String name = file.getAbsolutePath();
        if (name.contains(".x")) {
            return true;
        } else {
            return false;
        }
    }

    protected List<File> getFiles(File dir) {
        File[] contents = dir.listFiles();
        List<File> files = new ArrayList<File>();
        for (File file : contents) {
            if (isSkip(file)) {
                continue;
            }
            if (file.isDirectory()) {
                files.addAll(getFiles(file));
            } else {
                if (isAdd(file)) {
                    files.add(file);
                }
            }
        }
        return files;
    }
}
