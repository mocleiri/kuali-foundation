package org.kuali.common.util.metainf.model;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;

public class MetaInfResourceXmlFilenameComparator implements Comparator<MetaInfResource> {

    private static final String XML_SUFFIX = ".xml";

    @Override
    public int compare(MetaInfResource one, MetaInfResource two) {
        String fileName1 = one.getLocation();
        String fileName2 = two.getLocation();

        String[] fileName1Paths = StringUtils.split(fileName1, "/");
        String[] fileName2Paths = StringUtils.split(fileName2, "/");

        for (int i = 0; i < fileName1Paths.length && i < fileName2Paths.length; i++) {
            String fileName1Path = fileName1Paths[i];
            String fileName2Path = fileName2Paths[i];

            if (isIngestable(fileName1Path, fileName2Path)) {
                if (isIngestable(fileName1Path) && isIngestable(fileName2Path)) {
                    return ObjectUtils.compare(fileName1Path, fileName2Path);
                } else if (isIngestable(fileName1Path)) {
                    return -1;
                } else if (isIngestable(fileName2Path)) {
                    return 1;
                }
            } else if (!StringUtils.equals(fileName1Path, fileName2Path)) {
                return ObjectUtils.compare(fileName1Path, fileName2Path);
            }
        }

        return 0;
    }

    private static boolean isIngestable(String fileName) {
        return StringUtils.endsWith(fileName.toLowerCase(), XML_SUFFIX);
    }

    private static boolean isIngestable(String... fileNames) {
        boolean ingestable = false;

        for (String fileName : fileNames) {
            ingestable |= isIngestable(fileName);
        }

        return ingestable;
    }
}
