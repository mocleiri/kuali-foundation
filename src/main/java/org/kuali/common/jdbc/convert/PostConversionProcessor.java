package org.kuali.common.jdbc.convert;

import java.util.List;

/**
 * This interface defines an API for processing files (copying, renaming, etc) after they have been converted by the DirectoryConverter
 *
 * @author andrewlubbers
 */
public interface PostConversionProcessor {

    public void process(List<ConversionResult> conversionResults);

}
