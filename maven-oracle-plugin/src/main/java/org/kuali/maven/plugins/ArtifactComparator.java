package org.kuali.maven.plugins;

import java.io.IOException;
import java.util.Comparator;

import org.apache.maven.artifact.Artifact;

public class ArtifactComparator implements Comparator<Artifact> {

    @Override
    public int compare(Artifact one, Artifact two) {
        try {
            String path1 = one.getFile().getCanonicalPath();
            String path2 = two.getFile().getCanonicalPath();
            return path1.compareTo(path2);
        } catch (IOException e) {
            throw new RuntimeException("Unable to resolve canonical path", e);
        }
    }

}
