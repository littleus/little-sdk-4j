package org.littleus.sdk.config.dynamic;

import org.littleus.sdk.config.dynamic.configuration.H2DataBaseRegister;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import(H2DataBaseRegister.class)
@SpringBootApplication
public class TestApplication {
    public static void main(String[] args) {
        SpringApplication.run(TestApplication.class, args);
    }
}
