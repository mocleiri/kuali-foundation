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

package org.kuali.common.jalc.model.util;

import org.kuali.common.jalc.model.TypeSize;
import org.kuali.common.jalc.model.compare.ColumnDifference;
import org.kuali.common.jalc.model.compare.ForeignKeyDifference;
import org.kuali.common.jalc.model.compare.IndexDifference;
import org.kuali.common.jalc.model.compare.SchemaDifference;
import org.kuali.common.jalc.model.compare.SequenceDifference;
import org.kuali.common.jalc.model.compare.TableDifference;
import org.kuali.common.jalc.model.compare.UniqueConstraintDifference;
import org.kuali.common.jalc.model.compare.ViewDifference;

public class DifferenceUtils {
    public static final String LABEL_SEPARATOR = ": ";
    public static final String FOUND_IN_LABEL = "FOUND IN: ";
    public static final String NOT_FOUND_IN_LABEL = "NOT FOUND IN: ";
    public static final String DOT = ".";
    public static final String COMMA = ".";
    public static final String SPACE = " ";
    public static final String NOT_EQUAL_SEPARATOR = " != ";
    public static final String OPEN_PAREN = "(";
    public static final String CLOSE_PAREN = ")";

    static String buildDifferenceToken(String label, SchemaDifference difference) {
        StringBuilder sb = new StringBuilder();

        sb.append(label);
        sb.append(SPACE);
        sb.append(buildSchema1Token(difference));
        sb.append(NOT_EQUAL_SEPARATOR);
        sb.append(buildSchema2Token(difference));

        return sb.toString();
    }

    public static String buildSchema1Token(SchemaDifference difference) {
        StringBuilder sb = new StringBuilder();
        sb.append(difference.getSchema1().getName());
        sb.append(DOT);
        if (difference instanceof TableDifference) {
            sb.append(buildTable1DifferenceToken((TableDifference) difference));
        }

        if(difference instanceof ViewDifference) {

        }

        if(difference instanceof ForeignKeyDifference) {

        }

        if (difference instanceof SequenceDifference) {

        }

        return sb.toString();
    }

    public static String buildTable1DifferenceToken(TableDifference t) {
        StringBuilder sb = new StringBuilder();
        sb.append(t.getTable1().getName());

        if (t instanceof ColumnDifference) {
            ColumnDifference c = (ColumnDifference) t;
            sb.append(DOT);
            sb.append(c.getColumn1().getName());
            switch (c.getType()) {
                case COLUMN_DATA_TYPE: {
                    sb.append(parenWrapToken(c.getColumn1().getColumnDataType().toString()));
                    break;
                }
                case COLUMN_DATA_TYPE_SIZE: {
                    sb.append(parenWrapToken(buildTypeSizeToken(c.getColumn1().getTypeSize())));
                    break;
                }
            }
        }
        else if (t instanceof IndexDifference) {
            IndexDifference i = (IndexDifference) t;
            sb.append(DOT);
            sb.append(i.getIndex1().getName());
        }
        else if (t instanceof UniqueConstraintDifference) {
            UniqueConstraintDifference u = (UniqueConstraintDifference) t;
            sb.append(DOT);
            sb.append(u.getUnique1().getName());
        }

        return sb.toString();
    }

    public static String buildSchema2Token(SchemaDifference difference) {
        StringBuilder sb = new StringBuilder();
        sb.append(difference.getSchema2().getName());
        sb.append(DOT);
        if (difference instanceof TableDifference) {
            sb.append(buildTable2DifferenceToken((TableDifference) difference));
        }

        if(difference instanceof ViewDifference) {

        }

        if(difference instanceof ForeignKeyDifference) {

        }

        if (difference instanceof SequenceDifference) {

        }

        return sb.toString();
    }

    public static String buildTable2DifferenceToken(TableDifference t) {
        StringBuilder sb = new StringBuilder();
        sb.append(t.getTable2().getName());

        if (t instanceof ColumnDifference) {
            ColumnDifference c = (ColumnDifference) t;
            sb.append(DOT);
            sb.append(c.getColumn2().getName());
            switch (c.getType()) {
                case COLUMN_DATA_TYPE: {
                    sb.append(parenWrapToken(c.getColumn2().getColumnDataType().toString()));
                    break;
                }
                case COLUMN_DATA_TYPE_SIZE: {
                    sb.append(parenWrapToken(buildTypeSizeToken(c.getColumn2().getTypeSize())));
                    break;
                }
            }

        }
        else if (t instanceof IndexDifference) {
            IndexDifference i = (IndexDifference) t;
            sb.append(DOT);
            sb.append(i.getIndex2().getName());
        }
        else if (t instanceof UniqueConstraintDifference) {
            UniqueConstraintDifference u = (UniqueConstraintDifference) t;
            sb.append(DOT);
            sb.append(u.getUnique2().getName());
        }

        return sb.toString();
    }

    public static String buildMissingElementToken(TableDifference t, String foundToken, String notFoundToken) {
        StringBuilder sb = new StringBuilder();

        sb.append(t.getType().getLabel());
        sb.append(SPACE);
        sb.append(LABEL_SEPARATOR);
        sb.append(FOUND_IN_LABEL);
        sb.append(foundToken);
        sb.append(SPACE);
        sb.append(NOT_FOUND_IN_LABEL);
        sb.append(notFoundToken);

        return sb.toString();
    }

    public static String parenWrapToken(String s) {
        return SPACE + OPEN_PAREN + SPACE + s + SPACE + CLOSE_PAREN + SPACE;
    }

    public static String buildTypeSizeToken(TypeSize typeSize) {
        String typeSizeString = "";
        if (typeSize != null) {
            typeSizeString = typeSize.getSize().toString();
            if (typeSize.isScaleSet()) {
                typeSizeString += COMMA + typeSize.getScale();
            }
        }

        return typeSizeString;
    }
}
