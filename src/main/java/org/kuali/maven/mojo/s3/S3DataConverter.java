package org.kuali.maven.mojo.s3;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;

/**
 * Convert information from an S3 bucket into pojo's
 */
public class S3DataConverter {
    HtmlUtils html = new HtmlUtils();
    NumberFormat nf = getNumberFormatInstance();
    S3BucketContext context;
    String browseKey;

    public S3DataConverter() {
        this(null);
    }

    public S3DataConverter(final S3BucketContext context) {
        super();
        this.context = context;
    }

    /**
     * Return a NumberFormat that does not using grouping and always displays one fraction digit. This is used to
     * display the size of S3 objects in kilobytes
     */
    protected NumberFormat getNumberFormatInstance() {
        NumberFormat nf = NumberFormat.getInstance();
        nf.setMaximumFractionDigits(1);
        nf.setMinimumFractionDigits(1);
        nf.setGroupingUsed(false);
        return nf;
    }

    /**
     * Convert "foo/bar/css/" into "foo/bar/css"<br>
     * Convert "foo/bar/css" into "foo/bar"<br>
     */
    protected String getTrimmedPrefix(final String prefix, final String delimiter) {
        int pos = prefix.lastIndexOf(delimiter);
        if (pos == -1) {
            return prefix;
        }
        return prefix.substring(0, pos);
    }

    /**
     * Convert each DisplayRow object in the list to a String[] and add the String[] to the list of data
     */
    protected void addDisplayRows(final List<DisplayRow> displayRows, final List<String[]> data) {
        for (DisplayRow displayRow : displayRows) {
            addDisplayRow(displayRow, data);
        }
    }

    /**
     * Convert a DisplayRow object to a String[]
     */
    protected void addDisplayRow(final DisplayRow displayRow, final List<String[]> data) {
        if (displayRow == null) {
            return;
        }
        String[] row = new String[4];
        row[0] = displayRow.getImage();
        row[1] = displayRow.getAhref();
        row[2] = displayRow.getLastModified();
        row[3] = displayRow.getSize();
        data.add(row);
    }

    /**
     * Trim the prefix off of the text we display for this object.<br>
     * Display "style.css" instead of "css/style.css"
     */
    protected String getShow(final String key, final String prefix) {
        if (prefix == null) {
            return key;
        }
        int index = prefix.length();
        String s = key.substring(index);
        return s;
    }

    /**
     * Convert a commonPrefix into a DisplayRow object for the UI
     */
    protected DisplayRow getDisplayRow(final String commonPrefix, final String prefix, final String delimiter) {

        // Create some UI friendly strings
        String image = html.getImage(context.getDirectoryImage());
        String show = getShow(commonPrefix, prefix);
        String dest = delimiter + commonPrefix;
        String ahref = html.getHref(dest, show);
        String date = "-";
        String size = "-";

        // Store them in an object
        DisplayRow displayRow = new DisplayRow();
        displayRow.setImage(image);
        displayRow.setAhref(ahref);
        displayRow.setLastModified(date);
        displayRow.setSize(size);
        displayRow.setShow(show);
        return displayRow;
    }

    protected List<DisplayRow> getDirectoryDisplayRows(final ObjectListing objectListing, final String prefix,
            final String delimiter) {
        List<DisplayRow> displayRows = new ArrayList<DisplayRow>();
        for (String commonPrefix : objectListing.getCommonPrefixes()) {
            DisplayRow displayRow = getDisplayRow(commonPrefix, prefix, delimiter);
            if (displayRow == null) {
                continue;
            }
            displayRows.add(displayRow);
        }
        return displayRows;
    }

    /**
     * Convert the ObjectListing into a List of String arrays. Each array in the list represents one row in the html
     * table we will be generating
     */
    public List<String[]> getData(final ObjectListing objectListing, final String prefix, final String delimiter) {
        DisplayRow upOneDirectory = getUpOneDirectoryDisplayRow(prefix, delimiter);
        List<DisplayRow> objectDisplayRows = getObjectDisplayRows(objectListing, prefix, delimiter);
        List<DisplayRow> directoryDisplayRows = getDirectoryDisplayRows(objectListing, prefix, delimiter);
        Comparator<DisplayRow> c = new DisplayRowComparator();
        Collections.sort(directoryDisplayRows, c);
        List<String[]> data = new ArrayList<String[]>();
        addDisplayRow(upOneDirectory, data);
        addDisplayRows(directoryDisplayRows, data);
        addDisplayRows(objectDisplayRows, data);
        return data;
    }

    protected boolean isDirectory(S3ObjectSummary summary, List<String> commonPrefixes, String prefix, String delimiter) {
        String key = summary.getKey();
        if (key.equals(prefix)) {
            return true;
        }
        for (String commonPrefix : commonPrefixes) {
            if (key.equals(commonPrefix)) {
                return true;
            }
            String trimmedPrefix = getTrimmedPrefix(commonPrefix, delimiter);
            if (key.equals(trimmedPrefix)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Convert an S3ObjectSummary into a DisplayRow object for the UI
     */
    protected DisplayRow getDisplayRow(final S3ObjectSummary summary, final String prefix, final String delimiter) {
        String key = summary.getKey();

        // Create some UI friendly strings
        String image = html.getImage(context.getFileImage());
        String show = getShow(key, prefix);
        String dest = delimiter + key;
        String ahref = html.getHref(dest, show);
        String date = context.getLastModifiedDateFormatter().format(summary.getLastModified());
        String size = nf.format((summary.getSize() / 1024D)) + " KiB";

        // Store them in an object
        DisplayRow displayRow = new DisplayRow();
        displayRow.setShow(show);
        displayRow.setImage(image);
        displayRow.setAhref(ahref);
        displayRow.setLastModified(date);
        displayRow.setSize(size);
        return displayRow;
    }

    protected List<DisplayRow> getObjectDisplayRows(final ObjectListing objectListing, final String prefix,
            final String delimiter) {
        List<DisplayRow> displayRows = new ArrayList<DisplayRow>();
        for (S3ObjectSummary summary : objectListing.getObjectSummaries()) {
            if (isDirectory(summary, objectListing.getCommonPrefixes(), prefix, delimiter)) {
                continue;
            }
            DisplayRow displayRow = getDisplayRow(summary, prefix, delimiter);
            if (displayRow == null) {
                continue;
            }
            displayRows.add(displayRow);
        }
        return displayRows;
    }

    /**
     * Convert a commonPrefix into a DisplayRow object for the UI
     */
    protected DisplayRow getUpOneDirectoryDisplayRow(final String prefix, final String delimiter) {
        if (StringUtils.isEmpty(prefix)) {
            return null;
        }

        // Create some UI friendly strings
        String image = "";
        String show = ".." + delimiter;
        String dest = getUpOneDirectoryDest(prefix, delimiter);
        String ahref = html.getHref(dest, show);
        String date = "";
        String size = "";

        // Store them in an object
        DisplayRow displayRow = new DisplayRow();
        displayRow.setImage(image);
        displayRow.setAhref(ahref);
        displayRow.setLastModified(date);
        displayRow.setSize(size);
        return displayRow;
    }

    /**
     * If prefix is "foo/" and delimiter is "/" return "/"<br>
     * If prefix is "foo/bar/" and delimiter is "/" return "foo/"
     */
    protected String getUpOneDirectoryDest(String prefix, final String delimiter) {
        if (prefix.endsWith(delimiter)) {
            prefix = prefix.substring(0, prefix.length() - 1);
        }
        int pos = prefix.lastIndexOf(delimiter);
        if (pos == -1) {
            return delimiter + getBrowseKey();
        } else {
            return delimiter + prefix.substring(0, pos + 1);
        }
    }

    public void setContext(final S3BucketContext context) {
        this.context = context;
    }

    public S3BucketContext getContext() {
        return context;
    }

    public String getBrowseKey() {
        return browseKey;
    }

    public void setBrowseKey(final String browseHtml) {
        this.browseKey = browseHtml;
    }

}
