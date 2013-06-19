package org.kuali.maven.plugins.ecl.filter;

public class XMLRelatedFilter extends FileExtensionFilter {

    public XMLRelatedFilter() {
        super();
        extensions.add(".xml");
        extensions.add(".xsd");
        extensions.add(".xsl");
        extensions.add(".mxml");
    }

}
