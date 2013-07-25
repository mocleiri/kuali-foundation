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

package org.kuali.common.impex.model.util;

import org.kuali.common.impex.model.DataTypeSize;
import org.kuali.common.impex.model.compare.ColumnDifference;
import org.kuali.common.impex.model.compare.ForeignKeyDifference;
import org.kuali.common.impex.model.compare.IndexDifference;
import org.kuali.common.impex.model.compare.SchemaDifference;
import org.kuali.common.impex.model.compare.SequenceDifference;
import org.kuali.common.impex.model.compare.TableDifference;
import org.kuali.common.impex.model.compare.TableDifferenceType;
import org.kuali.common.impex.model.compare.UniqueConstraintDifference;
import org.kuali.common.impex.model.compare.ViewDifference;

public class DifferenceUtils {
	public static final String LABEL_SEPARATOR = ": ";
	public static final String FOUND_IN_LABEL = "FOUND IN: ";
	public static final String NOT_FOUND_IN_LABEL = "NOT FOUND IN: ";
	public static final String DOT = ".";
	public static final String COMMA = ".";
	public static final String SPACE = " ";
	public static final String NOT_EQUAL_SEPARATOR = " != ";
	public static final String OPEN_PAD_PAREN = " ( ";
	public static final String CLOSE_PAD_PAREN = " ) ";

	public static String buildDifferenceToken(String label, SchemaDifference difference) {
		StringBuilder sb = new StringBuilder();

		sb.append(label);
		sb.append(SPACE);
		sb.append(buildSchema1Token(difference));
		sb.append(NOT_EQUAL_SEPARATOR);
		sb.append(buildSchema2Token(difference));

		return sb.toString();
	}

    /**
     * Build a token to label the first "side" of a difference.
     *
     * This method will append applicable labels from instances of subclasses of TableDifference as well.
     *
     * @param t the TableDifference containing table data
     * @return a String representation of the first side of the given table data
     */
	public static String buildTable1DifferenceToken(TableDifference t) {
		StringBuilder sb = new StringBuilder();
		sb.append(t.getTable1().getName());

		if (t instanceof ColumnDifference) {
			ColumnDifference c = (ColumnDifference) t;
			sb.append(DOT);
			sb.append(c.getColumn1().getName());
			if (TableDifferenceType.COLUMN_DATA_TYPE.equals(c.getType())) {
				sb.append(parenWrapToken(c.getColumn1().getType().toString()));
			} else if (TableDifferenceType.COLUMN_DATA_TYPE_SIZE.equals(c.getType())) {
				sb.append(parenWrapToken(buildTypeSizeToken(c.getColumn1().getSize())));
			}
		} else if (t instanceof IndexDifference) {
			IndexDifference i = (IndexDifference) t;
			sb.append(DOT);
			sb.append(i.getIndex1().getName());
		} else if (t instanceof UniqueConstraintDifference) {
			UniqueConstraintDifference u = (UniqueConstraintDifference) t;
			sb.append(DOT);
			sb.append(u.getUnique1().getName());
		}

		return sb.toString();
	}

    /**
     * Build a token to label the first "side" of a schema difference.
     *
     * @param difference the SchemaDifference containing difference data
     * @return a String representation of the first side of the given difference data
     */
	public static String buildSchema1Token(SchemaDifference difference) {
		StringBuilder sb = new StringBuilder();
		sb.append(difference.getSchema1().getName());
		sb.append(DOT);
		if (difference instanceof TableDifference) {
			sb.append(buildTable1DifferenceToken((TableDifference) difference));
		}

		if (difference instanceof ViewDifference) {
			sb.append(((ViewDifference) difference).getView1().getName());
		}

		if (difference instanceof ForeignKeyDifference) {
			sb.append(((ForeignKeyDifference) difference).getForeignKey1().getName());
		}

		if (difference instanceof SequenceDifference) {
			sb.append(((SequenceDifference) difference).getSequence1().getName());
		}

		return sb.toString();
	}

    /**
     * Build a token to label the second "side" of a schema difference.
     *
     * @param difference the SchemaDifference containing difference data
     * @return a String representation of the second side of the given difference data
     */
	public static String buildSchema2Token(SchemaDifference difference) {
		StringBuilder sb = new StringBuilder();
		sb.append(difference.getSchema2().getName());
		sb.append(DOT);
		if (difference instanceof TableDifference) {
			sb.append(buildTable2DifferenceToken((TableDifference) difference));
		}

		if (difference instanceof ViewDifference) {
			sb.append(((ViewDifference) difference).getView2().getName());
		}

		if (difference instanceof ForeignKeyDifference) {
			sb.append(((ForeignKeyDifference) difference).getForeignKey2().getName());
		}

		if (difference instanceof SequenceDifference) {
			sb.append(((SequenceDifference) difference).getSequence2().getName());
		}

		return sb.toString();
	}

    /**
     * Build a token to label the second "side" of a difference.
     *
     * This method will append applicable labels from instances of subclasses of TableDifference as well.
     *
     * @param t the TableDifference containing table data
     * @return a String representation of the second side of the given table data
     */
	public static String buildTable2DifferenceToken(TableDifference t) {
		StringBuilder sb = new StringBuilder();
		sb.append(t.getTable2().getName());

		if (t instanceof ColumnDifference) {
			ColumnDifference c = (ColumnDifference) t;
			sb.append(DOT);
			sb.append(c.getColumn2().getName());
			if (TableDifferenceType.COLUMN_DATA_TYPE.equals(c.getType())) {
				sb.append(parenWrapToken(c.getColumn2().getType().toString()));
			} else if (TableDifferenceType.COLUMN_DATA_TYPE_SIZE.equals(c.getType())) {
				sb.append(parenWrapToken(buildTypeSizeToken(c.getColumn2().getSize())));
			}
		} else if (t instanceof IndexDifference) {
			IndexDifference i = (IndexDifference) t;
			sb.append(DOT);
			sb.append(i.getIndex2().getName());
		} else if (t instanceof UniqueConstraintDifference) {
			UniqueConstraintDifference u = (UniqueConstraintDifference) t;
			sb.append(DOT);
			sb.append(u.getUnique2().getName());
		}

		return sb.toString();
	}

    /**
     * Build a token representing a missing schema element
     *
     * @param typeLabel label for the type of difference
     * @param foundToken token labeling the found element
     * @param notFoundToken token labeling the missing element
     * @return a token describing with the following format;
     *
     * [typeLabel] : FOUND IN : [foundToken] NOT FOUND IN: [notFoundToken]
     *
     */
	public static String buildMissingElementToken(String typeLabel, String foundToken, String notFoundToken) {
		StringBuilder sb = new StringBuilder();

		sb.append(typeLabel);
		sb.append(SPACE);
		sb.append(LABEL_SEPARATOR);
		sb.append(FOUND_IN_LABEL);
		sb.append(foundToken);
		sb.append(SPACE);
		sb.append(NOT_FOUND_IN_LABEL);
		sb.append(notFoundToken);

		return sb.toString();
	}

    /**
     * Wraps a token in space padded parenthesis
     *
     * @param s input String
     * @return the input surrounded by space-padded parentheses
     */
	public static String parenWrapToken(String s) {
		return OPEN_PAD_PAREN + s + CLOSE_PAD_PAREN;
	}

	public static String buildTypeSizeToken(DataTypeSize typeSize) {
        StringBuilder typeSizeString = new StringBuilder();
        if (typeSize != null) {
            typeSizeString.append(typeSize.getValue().toString());
            if (typeSize.isScaleSet()) {
                typeSizeString.append(COMMA).append(typeSize.getScale());
            }
        }

        return typeSizeString.toString();
    }
}
