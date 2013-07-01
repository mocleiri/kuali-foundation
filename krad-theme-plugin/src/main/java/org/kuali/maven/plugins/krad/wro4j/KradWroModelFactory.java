package org.kuali.maven.plugins.krad.wro4j;

import ro.isdc.wro.model.WroModel;
import ro.isdc.wro.model.factory.WroModelFactory;
import ro.isdc.wro.model.group.Group;
import ro.isdc.wro.model.resource.Resource;
import ro.isdc.wro.model.resource.ResourceType;

/**
 * Created with IntelliJ IDEA.
 * User:  cpedersen
 * Date: 7/1/13
 * Time: 8:37 AM
 * To change this template use File | Settings | File Templates.
 */
public class KradWroModelFactory  implements WroModelFactory {
    public WroModel create() {
        WroModel themeModel = new WroModel();
        Group group = new Group("kboot");
        group.addResource(Resource.create("/kboot/*.js", ResourceType.JS));
        group.addResource(Resource.create("/kboot/*.css", ResourceType.CSS));
        group.addResource(Resource.create("/kboot/scripts/*.js", ResourceType.JS));
        group.addResource(Resource.create("/kboot/stylesheets/*.css", ResourceType.CSS));

        group = new Group("kboota");
        group.addResource(Resource.create("/kboota/*.js", ResourceType.JS));
        group.addResource(Resource.create("/kboota/*.css", ResourceType.CSS));
        group.addResource(Resource.create("/kboota/scripts/*.js", ResourceType.JS));
        group.addResource(Resource.create("/kboota/stylesheets/*.css", ResourceType.CSS));

        group = new Group("kbootab");
        group.addResource(Resource.create("/kbootab/*.js", ResourceType.JS));
        group.addResource(Resource.create("/kbootab/*.css", ResourceType.CSS));
        group.addResource(Resource.create("/kbootab/scripts/*.js", ResourceType.JS));
        group.addResource(Resource.create("/kbootab/stylesheets/*.css", ResourceType.CSS));

        themeModel.addGroup(group);

        return themeModel;
    }

    public void destroy() {
        //do some clean-up if required.
    }
}