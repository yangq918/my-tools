package org.yq.tool.yktools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class
})
public class YkToolsApplication {

    public static void main(String[] args) {
        SpringApplication.run(YkToolsApplication.class, args);
    }

}
