package org.yq.tool.yktools.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(value = "mysql.datasource.online")
public class OnlineDBConfig extends AbstractDBConfig {
}
