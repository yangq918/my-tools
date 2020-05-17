package org.yq.tool.yktools.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(value = "mysql.datasource.dev")
public class DevDBConfig extends AbstractDBConfig {
}
