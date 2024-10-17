package org.app.breeze;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "org.app.breeze.repository")
public class BreezeApplication {
    public static void main(String[] args) {
        SpringApplication.run(BreezeApplication.class, args);
    }
}
