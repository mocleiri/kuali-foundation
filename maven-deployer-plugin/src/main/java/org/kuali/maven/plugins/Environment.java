package org.kuali.maven.plugins;

public class Environment {
    int id;
    String name;
    String publicUrl;
    String tomcatInstance;
    String configFile;
    String warFile;
    String deployServer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublicUrl() {
        return publicUrl;
    }

    public void setPublicUrl(String publicUrl) {
        this.publicUrl = publicUrl;
    }

    public String getTomcatInstance() {
        return tomcatInstance;
    }

    public void setTomcatInstance(String tomcatInstance) {
        this.tomcatInstance = tomcatInstance;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public String getWarFile() {
        return warFile;
    }

    public void setWarFile(String warFile) {
        this.warFile = warFile;
    }

    public String getDeployServer() {
        return deployServer;
    }

    public void setDeployServer(String deployServer) {
        this.deployServer = deployServer;
    }

}
