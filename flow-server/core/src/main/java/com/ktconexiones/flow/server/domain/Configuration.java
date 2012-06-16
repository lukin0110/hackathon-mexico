package com.ktconexiones.flow.server.domain;

public class Configuration
{
    private String uploadPath;
    private String openApiServer, openApiAppKey;
    private String root, staticRoot;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }

    public String getOpenApiServer() {
        return openApiServer;
    }

    public void setOpenApiServer(String openApiServer) {
        this.openApiServer = openApiServer;
    }

    public String getOpenApiAppKey() {
        return openApiAppKey;
    }

    public void setOpenApiAppKey(String openApiAppKey) {
        this.openApiAppKey = openApiAppKey;
    }

    public String getRoot() {
        return root;
    }

    public void setRoot(String root) {
        this.root = root;
    }

    public String getStaticRoot() {
        return staticRoot;
    }

    public void setStaticRoot(String staticRoot) {
        this.staticRoot = staticRoot;
    }

    @Override
    public String toString() {
        return "[uploadPath=" + uploadPath + ", openApiServer=" + openApiServer + ", openApiAppKey=" + openApiAppKey + ", root=" + root + ", staticRoot=" + staticRoot+ "]";
    }
}

