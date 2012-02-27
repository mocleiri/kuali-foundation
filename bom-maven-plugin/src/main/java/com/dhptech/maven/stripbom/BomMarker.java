package com.dhptech.maven.stripbom;

import java.io.File;

public class BomMarker {
    File file;
    int skipBytes;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getSkipBytes() {
        return skipBytes;
    }

    public void setSkipBytes(int skipBytes) {
        this.skipBytes = skipBytes;
    }
}
