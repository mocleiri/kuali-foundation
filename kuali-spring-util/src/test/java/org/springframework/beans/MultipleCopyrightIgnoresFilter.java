package org.springframework.beans;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MultipleCopyrightIgnoresFilter extends CommonIgnoresFilter {
    List<String> knownFiles = new ArrayList<String>();

    public MultipleCopyrightIgnoresFilter() {
        super();
        knownFiles.add("/impl/src/main/resources/schema/ApplicationConstants.xsd");
        knownFiles.add("/impl/src/main/resources/schema/common.xsd");
        knownFiles.add("/impl/src/main/resources/schema/DocumentType.xsd");
        knownFiles.add("/impl/src/main/resources/schema/EDocLite.xsd");
        knownFiles.add("/impl/src/main/resources/schema/EDocLiteDef.xsd");
        knownFiles.add("/impl/src/main/resources/schema/Group.xsd");
        knownFiles.add("/impl/src/main/resources/schema/ResolverConfig.xsd");
        knownFiles.add("/impl/src/main/resources/schema/RoutingConfig.xsd");
        knownFiles.add("/impl/src/main/resources/schema/Rule.xsd");
        knownFiles.add("/impl/src/main/resources/schema/RuleAttribute.xsd");
        knownFiles.add("/impl/src/main/resources/schema/RuleDelegation.xsd");
        knownFiles.add("/impl/src/main/resources/schema/RuleTemplate.xsd");
        knownFiles.add("/impl/src/main/resources/schema/schema-for-xslt20.xsd");
        knownFiles.add("/impl/src/main/resources/schema/SearchingConfig.xsd");
        knownFiles.add("/impl/src/main/resources/schema/Style.xsd");
        knownFiles.add("/impl/src/main/resources/schema/User.xsd");
        knownFiles.add("/impl/src/main/resources/schema/WorkflowData.xsd");
        knownFiles.add("/src/site/docbook/IG/appendix_dbms.xml");
        knownFiles.add("/src/site/docbook/IG/intro.xml");
        knownFiles.add("/src/site/docbook/TRG/TRG_KEN.xml");
        knownFiles.add("/src/site/docbook/UG/UG_Global.xml");
        knownFiles.add("/impl/src/main/java/org/kuali/rice/kew/exception/WorkflowServiceErrorImpl.java");
        knownFiles
                .add("/kew/framework/src/main/java/org/kuali/rice/kew/framework/postprocessor/DocumentRouteLevelChange.java");
        knownFiles
                .add("/kew/framework/src/main/java/org/kuali/rice/kew/framework/postprocessor/DocumentRouteStatusChange.java");
        knownFiles.add("/krad/krad-web-framework/src/main/java/org/kuali/rice/krad/uif/view/View.java");
        knownFiles
                .add("/krad/krad-web-framework/src/main/resources/org/kuali/rice/krad/ApplicationResources.properties");

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
        for (String knownMultiCopyrightFile : knownFiles) {
            if (contains(path, knownMultiCopyrightFile)) {
                return true;
            }
        }
        return false;
    }
}
