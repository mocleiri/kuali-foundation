package org.springframework.beans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MultipleCopyrightIgnoresFilter extends CommonIgnoresFilter {
    List<String> knownMultiCopyrightFiles = new ArrayList<String>();

    public MultipleCopyrightIgnoresFilter() {
        super();
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/ApplicationConstants.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/common.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/DocumentType.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/EDocLite.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/EDocLiteDef.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/Group.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/ResolverConfig.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/RoutingConfig.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/Rule.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/RuleAttribute.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/RuleDelegation.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/RuleTemplate.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/schema-for-xslt20.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/SearchingConfig.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/Style.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/User.xsd");
        knownMultiCopyrightFiles.add("/impl/src/main/resources/schema/WorkflowData.xsd");
        knownMultiCopyrightFiles.add("/src/site/docbook/IG/appendix_dbms.xml");
        knownMultiCopyrightFiles.add("/src/site/docbook/IG/intro.xml");
        knownMultiCopyrightFiles.add("/src/site/docbook/TRG/TRG_KEN.xml");
        knownMultiCopyrightFiles.add("/src/site/docbook/UG/UG_Global.xml");
    }

    @Override
    public boolean accept(File file) {
        if (isMatch(file.getAbsolutePath())) {
            return true;
        } else {
            return super.accept(file);
        }
    }

    protected boolean isMatch(String path) {
        for (String knownMultiCopyrightFile : knownMultiCopyrightFiles) {
            if (contains(path, knownMultiCopyrightFile)) {
                return true;
            }
        }
        return false;
    }
}
