/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.core.db.torque.service;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author andrewlubbers
 */
public abstract class AbstractImpexReader implements ImpexReader {

    private MpxParser mpxParser;

    protected final static String OUTPUT_DATE_FORMAT = "yyyyMMddHHmmss";

    public MpxParser getMpxParser() {
        return mpxParser;
    }

    public void setMpxParser(MpxParser mpxParser) {
        this.mpxParser = mpxParser;
    }

    protected boolean batchLimitReached(int rows, int length, ImpexContext context) {
        if (rows > context.getRowCountInterval()) {
            return true;
        }
        else if (length > context.getDataSizeInterval()) {
            return true;
        }

        return false;
    }

    protected String readLineSkipHeader(BufferedReader reader) throws IOException {
        // First check to see if the reader is at the Header line.
        // If it is, skip that line
        String line = reader.readLine();
        if (ImpexUtils.isHeaderLine(line)) {
            line = reader.readLine();
        }

        return line;
    }
}
