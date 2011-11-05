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
        knownFiles.add("/scripts/jpaconversion/AnnotationHandler.groovy");
        knownFiles.add("/scripts/jpaconversion/archives/ojb2jpa.groovy");
        knownFiles.add("/scripts/jpaconversion/refactor/AnnotationHandler.groovy");
        knownFiles.add("/scripts/jpaconversion/AnnotationHandler.groovy");
        knownFiles.add("/impl/src/main/java/org/kuali/rice/kew/exception/WorkflowServiceErrorImpl.java");
        knownFiles
                .add("/kew/framework/src/main/java/org/kuali/rice/kew/framework/postprocessor/DocumentRouteLevelChange.java");
        knownFiles
                .add("/kew/framework/src/main/java/org/kuali/rice/kew/framework/postprocessor/DocumentRouteStatusChange.java");
        knownFiles.add("/krad/krad-web-framework/src/main/java/org/kuali/rice/krad/uif/view/View.java");
        knownFiles
                .add("/krad/krad-web-framework/src/main/resources/org/kuali/rice/krad/ApplicationResources.properties");
        knownFiles.add("notification.css");
        knownFiles.add("web/src/main/webapp/kr/css/kuali.css");
        knownFiles.add("/web/src/main/webapp/ken/scripts/jscalendar-1.0/skins/aqua/theme.css");
        knownFiles.add("/web/src/main/webapp/kr/scripts/jscalendar-1.0/skins/aqua/theme.css");
        knownFiles.add("/web/src/main/webapp/krad/css/global/base.css");
        knownFiles.add("/web/src/main/webapp/krad/css/global/fss-reset.css");
        knownFiles.add("/web/src/main/webapp/krad/css/global/jquery/jquery-ui-1.8.11.css");
        knownFiles.add("/web/src/main/webapp/krad/css/global/tabs.css");
        knownFiles.add("/web/src/main/webapp/krad/css/kns/jquery-ui-1.8.11.css");
        knownFiles.add("/web/src/main/webapp/rice-portal/css/portal.css");
        knownFiles.add("/web/src/main/webapp/kew/css/kuali.css");
        knownFiles.add("web/src/main/webapp/kew/scripts/cal2.js");
        knownFiles.add("web/src/main/webapp/kr/scripts/dhtml.js");
        knownFiles.add("web/src/main/webapp/krad/scripts/jquery/jquery-1.6.3.js");
        knownFiles.add("web/src/main/webapp/krad/scripts/jquery/jquery-ui-1.8.9.js");
        knownFiles.add("web/src/main/webapp/krad/scripts/jquery/jquery.ba-postmessage.js");
        knownFiles.add("web/src/main/webapp/krad/scripts/jquery/jquery.blockUI.js");
        knownFiles.add("web/src/main/webapp/krad/scripts/jquery/jquery.dirtyform.js");
        knownFiles.add("web/src/main/webapp/krad/scripts/jquery/jquery.easing-1.3.pack.js");
        knownFiles.add("web/src/main/webapp/krad/scripts/jquery/jquery.watermark.js");
        knownFiles.add("/LICENSE.txt");
        knownFiles.add("/NOTICE.txt");
        knownFiles.add("/web/src/main/webapp/acknowledgments.jsp");
        knownFiles.add("/web/src/main/webapp/kim/index.jsp");
        knownFiles.add("/web/src/main/webapp/ksb/WEB-INF/jsp/Footer.jsp");
        knownFiles.add("/web/src/main/webapp/ken/scripts/jscalendar-1.0/release-notes.html");
        knownFiles.add("/web/src/main/webapp/kr/scripts/jscalendar-1.0/release-notes.html");
    }

    @Override
    public boolean accept(File file) {
        if (isMatch(file.getAbsolutePath())) {
            return false;
        } else {
            return super.accept(file);
        }
    }

    protected boolean isMatch(String path) {
        for (String knownFile : knownFiles) {
            if (contains(path, knownFile)) {
                return true;
            }
        }
        return false;
    }
}
