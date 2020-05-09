package org.yq.tool.yktools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;

@SpringBootApplication
@EnableJdbcRepositories
public class YkToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YkToolsApplication.class, args);
    }

}
