package org.kuali.maven.plugins.krad.wro4j;

import ro.isdc.wro.manager.factory.ConfigurableWroManagerFactory;
import ro.isdc.wro.manager.factory.standalone.ConfigurableStandaloneContextAwareManagerFactory;
import ro.isdc.wro.model.factory.WroModelFactory;

/**
 * Created with IntelliJ IDEA.
 * User:  cpedersen
 * Date: 7/1/13
 * Time: 9:13 AM
 * To change this template use File | Settings | File Templates.
 */
public class KradWroManagerFactory extends ConfigurableStandaloneContextAwareManagerFactory {
    @Override
    protected WroModelFactory newModelFactory() {
        return new KradWroModelFactory();
    }
}