package org.yq.tool.yktools.config;

import lombok.Data;

@Data
public abstract class AbstractDBConfig {

    protected String url;
    protected String username;
    protected String password;
    protected int minPoolSize;
    protected int maxPoolSize;
    protected int maxLifetime;
    protected int borrowConnectionTimeout;
    protected int loginTimeout;
    protected int maintenanceInterval;
    protected int maxIdleTime;
    protected String testQuery;
}
